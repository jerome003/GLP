package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	return new ModelAndView("adminGroupe", "command", new GroupeDTO());
	}
	

	@RequestMapping(value = "/admin/saveGroupe", method = RequestMethod.POST)
	public ModelAndView saveGroupe(@Valid @ModelAttribute("command") GroupeDTO groupe, BindingResult result) {
		System.out.println("Mon groupe est :" + groupe.getName());
		groupeBean.createGroupe(groupe.getName());
		System.out.println(groupeBean.getAllGroupe());
		return new ModelAndView("adminGroupe", "command", new GroupeDTO());
	}

}
