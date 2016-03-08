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
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.remote.AdministrationRemote;


@Controller
public class ConnexionAdminController {
	@Inject
	protected AdministrationRemote adminBean;


	@RequestMapping(value = "/connexionAdmin", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "connexion");
		// variable type permettant de verifier le role de la personne qui tente de se connecter a une page
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
		if (!adminBean.isMailExistsForAdmin(admin.getEmail())) {
			result.rejectValue("email", null, "Cette adresse mail n'existe pas");
			return "connexionAdmin";
		}

		if (!adminBean.isPasswordIsGoodForAdmin(admin.getEmail(), admin.getPassword())) {
			result.rejectValue("password", null, "Ce n'est pas le bon mot de passe");
			return "connexionAdmin";
		}
		
		if (adminBean.connexionAdmin(admin.getEmail(), admin.getPassword())){
			AdminDTO ad = adminBean.getAdmin(admin.getEmail());
			HttpSession session = request.getSession();
			session.setAttribute("user", ad);
			session.setAttribute("type", "admin");

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
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("user", null);
		request.setAttribute("deco", "deco");
		sessionObj.removeAttribute("user");
		sessionObj.setAttribute("type", "");
		return "home";
	}
}
