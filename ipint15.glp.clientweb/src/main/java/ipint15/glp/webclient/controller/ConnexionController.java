package ipint15.glp.webclient.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jasig.cas.client.validation.Assertion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.ConnexionCommand;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;

@Controller
@SessionAttributes
public class ConnexionController {
	@Inject
	protected AncienEtudiantCatalogRemote etudiantBean;
    public static final String ATTR_CAS = "_const_cas_assertion_";
	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "connexion");
		return new ModelAndView("connexion", "command", new AncienEtudiantDTO());
	}

	@RequestMapping(value = "/connexionProfil", method = RequestMethod.POST)
	public String connexion(@Valid @ModelAttribute("command") ConnexionCommand etudiant, BindingResult result,
			HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		/*if (etudiant.getEmail().equals("admin@admin") && etudiant.getPassword().equals("admin")) {
			sessionObj.setAttribute("section", "connexion");
			return "redirect:admin";
		}*/ 
		sessionObj.setAttribute("section", "actualite");
		if (result.hasErrors()) {
			return "connexion";
		}
		if (!etudiantBean.isMailExists(etudiant.getEmail())) {
			result.rejectValue("email", null, "Cette adresse mail n'existe pas");
			return "connexion";
		}
		if (!etudiantBean.isPasswordIsGood(etudiant.getEmail(), etudiant.getPassword())) {
			result.rejectValue("password", null, "Ce n'est pas le bon mot de passe");
			return "connexion";
		}

		if (etudiantBean.connexion(etudiant.getEmail(), etudiant.getPassword())) {
			AncienEtudiantDTO etu = etudiantBean.getEtudiant(etudiant.getEmail());
			HttpSession session = request.getSession();
			session.setAttribute("etudiant", etu);
			List<ExperienceDTO> listExpPro = etudiantBean.getExperiences(etu);
			request.getSession().setAttribute("listExpPro", listExpPro);
			List<CompetenceDTO> listCompetence = etudiantBean.getCompetences(etu);
			request.getSession().setAttribute("listCompetence", listCompetence);
			List<EcoleDTO> listEcole = etudiantBean.getEcoles(etu);
			request.getSession().setAttribute("listEcole", listEcole);
			List<HobbieDTO> listLoisir = etudiantBean.getHobbies(etu);
			request.getSession().setAttribute("listLoisir", listLoisir);
			request.getSession().setAttribute("type","ancien");
		}

		return "redirect:fil-actualite";

	}

	/**
	 * Deconnection d'un utilisateur.
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/deconnection", method = RequestMethod.GET)
	public String deconnection(Locale locale, Model model, HttpServletRequest request) throws ServletException {
		Assertion assertion = (Assertion) request.getSession().getAttribute(ATTR_CAS);
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("etudiant", null);
		sessionObj.setAttribute("profil", null);
		request.setAttribute("deco", "deco");
		sessionObj.removeAttribute("etudiant");
		sessionObj.setAttribute("type", "");
		request.logout();
		if(assertion != null){
			request.getSession().setAttribute(ATTR_CAS, null);
//			System.out.println(request.getServletContext().getInitParameter("urlCasLogout")+ request.getServletContext().getInitParameter("urlSite"));
			return "redirect:" + request.getServletContext().getInitParameter("urlCasLogout") + request.getServletContext().getInitParameter("urlSite");
		}
		return "home";
	}
}
