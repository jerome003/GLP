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
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.remote.AdministrationRemote;


@Controller
public class ConnexionModerateurController {
	@Inject
	protected AdministrationRemote administrationBean;


	@RequestMapping(value = "/connexionModerateur", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "connexion");
		return new ModelAndView("connexionModerateur", "command", new ModerateurDTO());
	}

	@RequestMapping(value = "/doConnexionModerateur", method = RequestMethod.POST)
	public String connexion(@Valid @ModelAttribute("command") ConnexionCommand moderateur, BindingResult result,
			HttpServletRequest request) {
		
		HttpSession sessionObj = request.getSession();
		
		sessionObj.setAttribute("section", "accueilmoderateur");
		
		if (result.hasErrors()) {
			return "connexionModerateur";
		}
		if (!administrationBean.isMailExistsForModerateur(moderateur.getEmail())) {
			result.rejectValue("email", null, "Cette adresse mail n'existe pas");
			return "connexionModerateur";
		}

		if (!administrationBean.isPasswordIsGoodForModerateur(moderateur.getEmail(), moderateur.getPassword())) {
			result.rejectValue("password", null, "Ce n'est pas le bon mot de passe");
			return "connexionModerateur";
		}
		
		if (administrationBean.connexionModerateur(moderateur.getEmail(), moderateur.getPassword())){
			ModerateurDTO modo = administrationBean.getModerateur(moderateur.getEmail());
			HttpSession session = request.getSession();
			session.setAttribute("user", modo);

		}

		return "redirect:moderateur";

	}

	/**
	 * Deconnection d'un utilisateur.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deconnectionModerateur", method = RequestMethod.GET)
	public String deconnection(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("user", null);
		request.setAttribute("deco", "deco");
		sessionObj.removeAttribute("user");
		return "redirect:connexionModerateur";
	}
}

