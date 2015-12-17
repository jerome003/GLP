package ipint15.glp.webclient.controller;

import java.util.List;
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

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.ConnexionCommand;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes("etudiant")
public class ConnexionController {
	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "connexion");
		return new ModelAndView("connexion", "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/connexionProfil", method = RequestMethod.POST)
	public String connexion(@Valid @ModelAttribute("command") ConnexionCommand etudiant, BindingResult result,
			HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
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
			EtudiantDTO etu = etudiantBean.getEtudiant(etudiant.getEmail());
			sessionObj = request.getSession();
			sessionObj.setAttribute("etudiant", etu);
			System.out.println("connexion controller"+etu.getId());
			List<ExperienceDTO> listExpPro = etudiantBean.getExperiences(etu);
			request.getSession().setAttribute("listExpPro", listExpPro);
			List<CompetenceDTO> listCompetence = etudiantBean.getCompetences(etu);
			request.getSession().setAttribute("listCompetence", listCompetence);
			List<EcoleDTO> listEcole = etudiantBean.getEcoles(etu);
			request.getSession().setAttribute("listEcole", listEcole);
			List<HobbieDTO> listLoisir = etudiantBean.getHobbies(etu);
			request.getSession().setAttribute("listLoisir", listLoisir);
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
	 */
	@RequestMapping(value = "/deconnection", method = RequestMethod.GET)
	public String deconnection(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("etudiant", null);
		request.setAttribute("deco", "deco");
		return "home";
	}
}
