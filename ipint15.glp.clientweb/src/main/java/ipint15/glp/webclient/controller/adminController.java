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

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;

@Controller
@SessionAttributes
public class adminController {
	
	@Inject
	protected GroupeRemote groupeBean;
	
	@RequestMapping(value = "/admin/groupes", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
	HttpSession sessionObj = request.getSession();
	sessionObj.setAttribute("section", "groupes");
	
	List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
	ModelAndView modelView = new ModelAndView("adminGroupe", "command", new GroupeDTO());
	modelView.addObject("liste",listeResultat);
	
	return modelView;
	}
	

	@RequestMapping(value = "/admin/saveGroupe", method = RequestMethod.POST)
	public ModelAndView saveGroupe(@Valid @ModelAttribute("command") GroupeDTO groupe, BindingResult result) {
		System.out.println("Mon groupe est :" + groupe.getName());
		groupeBean.createGroupe(groupe.getName());
		System.out.println(groupeBean.getAllGroupe());
		List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
		ModelAndView modelView = new ModelAndView("redirect:groupes", "command", new GroupeDTO());
		modelView.addObject("liste",listeResultat);
		
		return modelView;
	}

	@RequestMapping(value = "/admin/removeGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView removeGroup(Locale locale, Model model, HttpServletRequest request,@PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "groupes");
		
		int id = Integer.parseInt(pathVariables.get("id"));
		groupeBean.removeGroupe(id);
		
		List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
		ModelAndView modelView = new ModelAndView("redirect:../groupes", "command", new GroupeDTO());
		modelView.addObject("liste",listeResultat);
		
		return modelView;
		}
	
}
