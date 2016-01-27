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

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.ConnexionCommand;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.api.remote.EtudiantCatalogRemote;


@Controller
public class ConnexionModerateurController {
	@Inject
	protected AdministrationRemote administrationBean;
	@Inject
	protected EtudiantCatalogRemote etudiantBean;


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

		if (administrationBean.connexionModerateur(moderateur.getEmail(), moderateur.getPassword())) {
			ModerateurDTO modo = administrationBean.getModerateur(moderateur.getEmail());
			System.out.println(modo.getGroupes());
			HttpSession session = request.getSession();
			session.setAttribute("user", modo);
			session.setAttribute("type", "moderateur");

		}

		return "redirect:moderateur";

	}

	@RequestMapping(value = "/moderateur/validationGroup/{id}", method = RequestMethod.GET)
	public ModelAndView removeGroup(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		
		if (sessionObj.getAttribute("type").equals("moderateur")) {
			sessionObj.setAttribute("section", "groupes");
			int id = Integer.parseInt(pathVariables.get("id"));
			sessionObj.setAttribute("idGroupe", id);
			List<EtudiantDTO> listeResultat = administrationBean.getEtudiantsNonInscritByIdGroupe(id);
			ModelAndView modelView = new ModelAndView("validationInscription");
			modelView.addObject("liste", listeResultat);
			return modelView;
		} else {
			ModelAndView modele = new ModelAndView("errorAccesRole");
			return modele;
		}
		
	}

	@RequestMapping(value = "/moderateur/validationGroup/{idGroupe}/etudiantOK/{idEtu}", method = RequestMethod.GET)
	public ModelAndView valideInscription(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {

		int idEtu = Integer.parseInt(pathVariables.get("idEtu"));
		EtudiantDTO etu = etudiantBean.getEtudiant(idEtu);
		administrationBean.validationInscription(etu);
		
		int idGroupe = Integer.parseInt(pathVariables.get("idGroupe"));
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("idGroupe", idGroupe);
		List<EtudiantDTO> listeResultat = administrationBean.getEtudiantsNonInscritByIdGroupe(idGroupe);
		// Modifier vue retourner pour que cela soit plus propre au niveau des urls.
		ModelAndView modelView = new ModelAndView("validationInscription");
		modelView.addObject("liste", listeResultat);

		return modelView;

	}

	@RequestMapping(value = "/moderateur/validationGroup/{idGroupe}/etudiantKO/{idEtu}", method = RequestMethod.GET)
	public ModelAndView refuseInscription(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {

		int idEtu = Integer.parseInt(pathVariables.get("idEtu"));
		System.out.println(idEtu);
		EtudiantDTO etu = etudiantBean.getEtudiant(idEtu);
		System.out.println(etu);
		int idGroupe = Integer.parseInt(pathVariables.get("idGroupe"));
		administrationBean.refusInscription(etu,idGroupe);
		

		List<EtudiantDTO> listeResultat = administrationBean.getEtudiantsNonInscritByIdGroupe(idGroupe);
		ModelAndView modelView = new ModelAndView("validationInscription");
		modelView.addObject("liste", listeResultat);

		return modelView;

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
		sessionObj.setAttribute("type", "");
		return "redirect:connexionModerateur";
	}
}
