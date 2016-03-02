package ipint15.glp.webclient.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.api.remote.GroupeRemote;

@Controller
@SessionAttributes
public class AdminController {

	@Inject
	protected GroupeRemote groupeBean;

	@Inject
	protected AdministrationRemote administrationBean;


	@ModelAttribute("moderateurList")
	public List<ModerateurDTO> getModerateur() {
		return administrationBean.getAllModerateur();
	}

	@RequestMapping(value = "/admin/groupes", method = RequestMethod.GET)
	public ModelAndView homeGroupes(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {

			if (sessionObj.getAttribute("type").equals("admin")) {
				sessionObj.setAttribute("section", "groupes");
				List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
				ModelAndView modelView = new ModelAndView("adminGroupe", "command", new GroupeDTO());
				modelView.addObject("liste", listeResultat);
				model.addAttribute("myInjectedBean", groupeBean);
				return modelView;
			} else {
				ModelAndView modelView = new ModelAndView("errorAccesRole");
				return modelView;
			}
		} catch (NullPointerException e) {
			ModelAndView modelView = new ModelAndView("errorAccesRole");
			return modelView;
		}
	}

	@RequestMapping(value = "/admin/moderateurs", method = RequestMethod.GET)
	public ModelAndView homeModerateurs(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("admin")) {
				sessionObj.setAttribute("section", "moderateurs");
				List<ModerateurDTO> listeResultat = administrationBean.getAllModerateur();
				ModelAndView modelView = new ModelAndView("adminModerateur", "command", new ModerateurDTO());
				modelView.addObject("listeModo", listeResultat);
				model.addAttribute("myInjectedBean", administrationBean);
				return modelView;
			} else {
				ModelAndView modelView = new ModelAndView("errorAccesRole");
				return modelView;
			}
		} catch (NullPointerException e) {
			ModelAndView modelView = new ModelAndView("errorAccesRole");
			return modelView;
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("admin")) {
				sessionObj.setAttribute("section", "accueilgroupes");
				return "admin";
			} else {
				return "errorAccesRole";
			}
		} catch (NullPointerException e) {
			return "errorAccesRole";
		}
	}

	@RequestMapping(value = "/admin/saveGroupe", method = RequestMethod.POST)
	public ModelAndView saveGroupe(String nameGroupe, String descriptionGroupe, int modo, boolean institutionnel) {

		ModelAndView modelView;
	
		GroupeDTO gDTO = groupeBean.createGroupe(nameGroupe, descriptionGroupe, institutionnel);
		ModerateurDTO mDTO = administrationBean.addGroupetoModo(modo, gDTO);
		administrationBean.sendMailModoAssign(mDTO, gDTO);
		List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
		modelView = new ModelAndView("redirect:groupes", "command", new GroupeDTO());
		modelView.addObject("liste", listeResultat);
		modelView.addObject("creation", "ok");
		return modelView;

	}

	@RequestMapping(value = "/admin/saveModerateur", method = RequestMethod.POST)
	public ModelAndView saveModerateur(@Valid @ModelAttribute("command") ModerateurDTO moderateur, BindingResult result, HttpServletRequest request) {

		ModelAndView modelView;

		if (result.hasErrors()) {
			modelView = new ModelAndView("adminModerateur");
			return modelView;
		}
		if (administrationBean.isMailExistsForModerateur(moderateur.getEmail())) {
			result.rejectValue("email", null, "Cette adresse existe déjà");
			List<ModerateurDTO> listeResultat = administrationBean.getAllModerateur();
			modelView = new ModelAndView("redirect:moderateurs", "command", new ModerateurDTO());
			modelView.addObject("liste", listeResultat);
			modelView.addObject("creation", "ko");
			return modelView;
		} else {

			administrationBean.createModerateur(moderateur.getPrenom(), moderateur.getNom(), moderateur.getEmail(),
					administrationBean.generatePassword(8));
			List<ModerateurDTO> listeResultat = administrationBean.getAllModerateur();
			modelView = new ModelAndView("redirect:moderateurs", "command", new ModerateurDTO());
			modelView.addObject("liste", listeResultat);
		}
		modelView.addObject("creation", "ok");
		return modelView;
	}

	@RequestMapping(value = "/admin/removeGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView removeGroup(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "groupes");
		
		ModelAndView modelView ;
		modelView = new ModelAndView("redirect:../groupes");

		int id = Integer.parseInt(pathVariables.get("id"));
		if (groupeBean.removeGroupe(id)) {
	
			List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
			modelView = new ModelAndView("redirect:../groupes", "command", new GroupeDTO());
			modelView.addObject("liste", listeResultat);
	
			modelView.addObject("delete", "ok");
			return modelView;
		}
		
		modelView.addObject("delete", "ko");
		return modelView;
	}
	
	@RequestMapping(value = "/admin/removeModerateur/{id}", method = RequestMethod.GET)
	public ModelAndView removeModerateur(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "moderateurs");
		
		ModelAndView modelView ;
		modelView = new ModelAndView("redirect:../moderateurs");

		int id = Integer.parseInt(pathVariables.get("id"));
		
		if (administrationBean.removeModerateur(id)){
			List<ModerateurDTO> listeResultat = administrationBean.getAllModerateur();
			modelView = new ModelAndView("redirect:../moderateurs", "command", new ModerateurDTO());
			modelView.addObject("liste", listeResultat);
			modelView.addObject("delete", "ok");
			return modelView;
		}
		modelView.addObject("delete", "ko");
		return modelView;
	}

	@RequestMapping(value = "/admin/editerGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView editerGroupe(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		
		try {
			if (sessionObj.getAttribute("type").equals("admin")) {
				sessionObj.setAttribute("section", "groupe");
				int id = Integer.parseInt(pathVariables.get("id"));
				GroupeDTO groupe = groupeBean.getGroupeDTOById(id);
				sessionObj.setAttribute("groupe", groupe);
				List<ModerateurDTO> listeResultat = administrationBean.getModerateursDuGroupe(id);
				ModelAndView modelView = new ModelAndView("editerGroupe", "command", new GroupeDTO());
				modelView.addObject("listeModo", listeResultat);
				model.addAttribute("myInjectedBean", administrationBean);
				return modelView;
			} else {
				ModelAndView modelView = new ModelAndView("errorAccesRole");
				return modelView;
			}	
		} catch (NullPointerException e) {
			ModelAndView modelView = new ModelAndView("errorAccesRole");
			return modelView;
		}
	}
	
	@RequestMapping(value = "admin/editerGroupe/editGroupe1/{id}", method = RequestMethod.POST)
	public ModelAndView saveEditGroupe(String descriptionGroupe,
			@PathVariable Map<String, String> pathVariables) {


		int id =(Integer)Integer.parseInt(pathVariables.get("id"));
		ModelAndView modelView = new ModelAndView("redirect:/admin/editerGroupe/"+id);
		
		GroupeDTO groupe = groupeBean.getGroupeDTOById(id);
		
		if( groupe.getDescription().equals(descriptionGroupe)) {
			modelView.addObject("edition1", "ko");
			return modelView;
		}
		else {
			
			groupeBean.editGroupe(id, descriptionGroupe);
			List<ModerateurDTO> listeResultat = administrationBean.getModerateursDuGroupe(id);
			modelView = new ModelAndView("redirect:/admin/editerGroupe/"+id, "command", new GroupeDTO());
			modelView.addObject("listeModo", listeResultat);
			modelView.addObject("edition1", "ok");
			return modelView;
		}
			
		
		
	}

	@RequestMapping(value = "admin/editerGroupe/editGroupe2/{id}", method = RequestMethod.POST)
	public ModelAndView saveEditGroupe2( int modo,
			@PathVariable Map<String, String> pathVariables) {


		int id =(Integer)Integer.parseInt(pathVariables.get("id"));
		ModelAndView modelView = new ModelAndView("redirect:/admin/editerGroupe/"+id);
		
		GroupeDTO groupe = groupeBean.getGroupeDTOById(id);
		
		if(administrationBean.isModerateurOfGroupe(modo, id)) {
			modelView.addObject("edition2", "ko");
			return modelView;
		}
		else {
				ModerateurDTO mDTO = administrationBean.addGroupetoModo(modo, groupe);
				administrationBean.sendMailModoAssign(mDTO, groupe);
				List<ModerateurDTO> listeResultat = administrationBean.getModerateursDuGroupe(id);
				modelView = new ModelAndView("redirect:/admin/editerGroupe/"+id, "command", new GroupeDTO());
				modelView.addObject("listeModo", listeResultat);
				modelView.addObject("edition2", "ok");
				return modelView;
			}
		
	}
	
	@RequestMapping(value = "/admin/editerGroupe/removeModerateurFromGroupe/Modo={id}/Groupe={id2}", method = RequestMethod.GET)
	public ModelAndView removeModerateurFromGroupe(Locale locale, Model model,
			@PathVariable Map<String, String> pathVariables) {
		
		
		ModelAndView modelView ;

		int idModo = Integer.parseInt(pathVariables.get("id"));
		int idGroupe = Integer.parseInt(pathVariables.get("id2"));
		ModerateurDTO mod = administrationBean.getModerateurDTOById(idModo);
		GroupeDTO groupe = groupeBean.getGroupeDTOById(idGroupe);
		modelView = new ModelAndView("redirect:/admin/editerGroupe/"+idGroupe);
		
		if (administrationBean.removeModerateurFromGroupe(idModo,idGroupe)){
			administrationBean.sendMailModoUnassign(mod, groupe);
			List<ModerateurDTO> listeResultat = administrationBean.getModerateursDuGroupe(idGroupe);
			modelView = new ModelAndView("redirect:/admin/editerGroupe/"+idGroupe, "command", new ModerateurDTO());
			modelView.addObject("listeModo", listeResultat);
			modelView.addObject("delete", "ok");
			return modelView;
		}
		modelView.addObject("delete","ko");
		return modelView;
	}


}
