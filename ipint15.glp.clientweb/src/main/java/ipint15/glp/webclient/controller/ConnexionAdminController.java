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

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.ConnexionCommand;
import ipint15.glp.api.remote.AdministrationRemote;


@Controller
public class ConnexionAdminController {
	@Inject
	protected AdministrationRemote adminBean;


	@RequestMapping(value = "/connexionAdmin", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "connexionAdmin");
		if (!adminBean.isThereAnAdmin()){
			adminBean.createAdmin("admin@admin.fr", "password");
		}
		return new ModelAndView("connexionAdmin", "command", new AdminDTO());
	}

	@RequestMapping(value = "/connexionAdministration", method = RequestMethod.POST)
	public String connexion(@Valid @ModelAttribute("command") ConnexionCommand admin, BindingResult result,
			HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		
		sessionObj.setAttribute("section", "accueilgroupes");
		if (result.hasErrors()) {
			return "connexionAdmin";
		}
		if (!adminBean.isMailExists(admin.getEmail())) {
			result.rejectValue("email", null, "Cette adresse mail n'existe pas");
			return "connexionAdmin";
		}

		if (!adminBean.isPasswordIsGood(admin.getEmail(), admin.getPassword())) {
			result.rejectValue("password", null, "Ce n'est pas le bon mot de passe");
			return "connexionAdmin";
		}
		
		if (adminBean.connexion(admin.getEmail(), admin.getPassword())){
			sessionObj = request.getSession();

		}

		return "redirect:admin";

	}

	/**
	 * Deconnection d'un utilisateur.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deconnectionAdmin", method = RequestMethod.GET)
	public String deconnection(Locale locale, Model model, HttpServletRequest request) {
		System.out.println("IN");
		return "redirect:connexionAdmin";
	}
}
