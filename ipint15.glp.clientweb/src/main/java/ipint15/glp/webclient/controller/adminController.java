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
public class adminController {

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
	public ModelAndView saveGroupe(String nameGroupe, String descriptionGroupe, int modo) {

		ModelAndView modelView;

		if (nameGroupe.length() <= 0) {
			modelView = new ModelAndView("adminGroupe");
			return modelView;
		}
		if (descriptionGroupe.length() <= 0) {
			modelView = new ModelAndView("adminGroupe");
			return modelView;
		}
		GroupeDTO gDTO = groupeBean.createGroupe(nameGroupe, descriptionGroupe);
		ModerateurDTO mDTO = administrationBean.addGroupetoModo(modo, gDTO);
		administrationBean.sendMailModoAssign(mDTO, gDTO);
		List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
		modelView = new ModelAndView("redirect:groupes", "command", new GroupeDTO());
		modelView.addObject("liste", listeResultat);
		return modelView;
	}

	@RequestMapping(value = "/admin/saveModerateur", method = RequestMethod.POST)
	public ModelAndView saveGroupe(@Valid @ModelAttribute("command") ModerateurDTO moderateur, BindingResult result) {

		ModelAndView modelView;

		if (result.hasErrors()) {
			modelView = new ModelAndView("adminModerateur");
			return modelView;
		}
		if (administrationBean.isMailExistsForModerateur(moderateur.getEmail())) {
			result.rejectValue("email", null, "Cette adresse existe déjà");
			modelView = new ModelAndView("adminModerateur");
			return modelView;
		} else {

			administrationBean.createModerateur(moderateur.getPrenom(), moderateur.getNom(), moderateur.getEmail(),
					administrationBean.generatePassword(8));
			List<ModerateurDTO> listeResultat = administrationBean.getAllModerateur();
			modelView = new ModelAndView("redirect:moderateurs", "command", new ModerateurDTO());
			modelView.addObject("liste", listeResultat);
		}

		return modelView;
	}

	@RequestMapping(value = "/admin/removeGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView removeGroup(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "groupes");

		int id = Integer.parseInt(pathVariables.get("id"));
		groupeBean.removeGroupe(id);

		List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
		ModelAndView modelView = new ModelAndView("redirect:../groupes", "command", new GroupeDTO());
		modelView.addObject("liste", listeResultat);

		return modelView;
	}

}
