package ipint15.glp.webclient.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;

@Controller
@SessionAttributes
public class EditionProfilController {

	@Inject
	protected AncienEtudiantCatalogRemote etudiantBean;

	/**
	 * Permet la redirection vers la page d'edition de profil
	 * 
	 * @param locale
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editionProfil", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien")) {
				sessionObj.setAttribute("section", "profil");
				model.addAttribute("myInjectedBean", etudiantBean);
				return "editionProfil";
			} else {
				return "errorAccesRole";
			}
		} catch (NullPointerException e) {
			return "errorAccesRole";
		}
	}

	@RequestMapping(value = "/modifyProfil", method = RequestMethod.POST)

	public String modifyProfil(@Valid @ModelAttribute("command") AncienEtudiantDTO etudiant, BindingResult result) {
		// TODO
		return "editionProfil";
	}


	@RequestMapping(value = "/saveProfil", method = RequestMethod.POST)
	public ModelAndView saveProfil(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int idEtu =  Integer.parseInt(request.getParameter("idEtu"));
		String numTelephone = request.getParameter("numTelephone");
		String nomEntreprise = request.getParameter("nomEntreprise");
		String posteActu = request.getParameter("posteActu");
		String villeActu = request.getParameter("villeActu");
		String facebook = request.getParameter("facebook");
		String twitter = request.getParameter("twitter");
		String viadeo = request.getParameter("viadeo");
		String linkedin = request.getParameter("linkedin");
		String attentes = request.getParameter("attentes");
		String statut = request.getParameter("statut");

		etudiantBean.updateEtudiant(idEtu, statut, posteActu, villeActu, nomEntreprise, numTelephone, facebook, twitter, viadeo,
				linkedin, attentes);

		return new ModelAndView("redirect:profil/" + idEtu, "command", new AncienEtudiantDTO());

	}


	@RequestMapping(value = "/saveExpPro", method = RequestMethod.POST)
	public ModelAndView saveExpPro(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		AncienEtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteExpProList(etudiantDTO);
		if (liste == null || liste.length() < 9) {
			return mapCompetencesEtudiant(etudiantDTO, request);
		} else {

			String tabExp[] = liste.split("%");
			// TODO ajouter une exception, si le le tableau est vide on fait
			// rien --> vérif avec le if !!
			for (int i = 0; i < tabExp.length; i++) {
				String tabExpTmp[] = tabExp[i].split("\\|");
				if (tabExpTmp.length == 8) {
					etudiantBean.addExperience(etudiantDTO, tabExpTmp[0].replaceAll("|", ""),
							tabExpTmp[1].replaceAll("|", ""), tabExpTmp[2].replaceAll("|", ""),
							tabExpTmp[3].replaceAll("|", ""), tabExpTmp[4].replaceAll("|", ""),
							tabExpTmp[5].replaceAll("|", ""), tabExpTmp[6].replaceAll("|", ""),
							tabExpTmp[7].replaceAll("|", ""));
				}
			}
			return mapCompetencesEtudiant(etudiantDTO, request);
		}

	}

	@RequestMapping(value = "/saveCompetence", method = RequestMethod.POST)
	public ModelAndView saveCompetence(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		AncienEtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteCompetenceList(etudiantDTO);
		String tabExp[] = liste.split("%");
		for (int i = 0; i < tabExp.length; i++) {
			String tabExpTmp[] = tabExp[i].split("\\|");
			if (tabExpTmp.length == 2) {
				etudiantBean.addCompetence(etudiantDTO, tabExpTmp[0], Integer.parseInt(tabExpTmp[1]));
			}
		}
		return mapCompetencesEtudiant(etudiantDTO, request);
	}

	/**
	 * Permet de sauvegarder une formation ajouté par l'utilisateur
	 * 
	 * @param email
	 * @param liste
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveFormation", method = RequestMethod.POST)
	public ModelAndView saveFormation(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		AncienEtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteFormationList(etudiantDTO);
		if (liste == null || liste.length() < 7) {
			return mapCompetencesEtudiant(etudiantDTO, request);
		} else {
			String tabEcole[] = liste.split("%");
			for (int i = 0; i < tabEcole.length; i++) {
				String tabEcoleTmp[] = tabEcole[i].split("\\|");
				if (tabEcoleTmp.length == 7) {
					etudiantBean.addEcole(etudiantDTO, tabEcoleTmp[0].replaceAll("|", ""),
							tabEcoleTmp[1].replaceAll("|", ""), tabEcoleTmp[2].replaceAll("|", ""),
							tabEcoleTmp[3].replaceAll("|", ""), tabEcoleTmp[4].replaceAll("|", ""),
							tabEcoleTmp[5].replaceAll("|", ""), tabEcoleTmp[6].replaceAll("|", ""));
				}
			}
			return mapCompetencesEtudiant(etudiantDTO, request);
		}
	}

	@RequestMapping(value = "/saveLoisir", method = RequestMethod.POST)
	public ModelAndView saveLoisir(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		AncienEtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteLoisirList(etudiantDTO);
		String tabExp[] = liste.split("%");
		for (int i = 0; i < tabExp.length; i++) {
			etudiantBean.addHobbie(etudiantDTO, tabExp[i]);
		}
		return mapCompetencesEtudiant(etudiantDTO, request);
	}

	public ModelAndView mapCompetencesEtudiant(AncienEtudiantDTO etudiantDTO, HttpServletRequest request) {
		List<HobbieDTO> listLoisir = etudiantBean.getHobbies(etudiantDTO);
		List<EcoleDTO> listEcole = etudiantBean.getEcoles(etudiantDTO);
		List<ExperienceDTO> listExpPro = etudiantBean.getExperiences(etudiantDTO);
		List<CompetenceDTO> listCompetence = etudiantBean.getCompetences(etudiantDTO);
		etudiantDTO.getProfil().setMesCompetences(listCompetence);
		etudiantDTO.getProfil().setMesEcoles(listEcole);
		etudiantDTO.getProfil().setMesExperiences(listExpPro);
		etudiantDTO.getProfil().setMesHobbies(listLoisir);
		request.getSession().setAttribute("etudiant", etudiantDTO);
		return new ModelAndView("redirect:profil/" + etudiantDTO.getId(), "command", new AncienEtudiantDTO());
	}
}