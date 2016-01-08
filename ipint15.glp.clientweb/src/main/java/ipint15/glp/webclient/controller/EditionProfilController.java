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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes
public class EditionProfilController {

	@Inject
	protected EtudiantCatalogRemote etudiantBean;

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
		sessionObj.setAttribute("section", "profil");
		model.addAttribute("myInjectedBean", etudiantBean);
		return "editionProfil";
	}

	@RequestMapping(value = "/modifyProfil", method = RequestMethod.POST)

	public String modifyProfil(@Valid @ModelAttribute("command") EtudiantDTO etudiant, BindingResult result) {
		// TODO
		return "editionProfil";
	}

	/**
	 * Permet d'enregistrer les nouvelles données correspondant à un étudiant
	 * 
	 * @param posteActu
	 * @param lieu
	 * @param entreprise
	 * @param mail
	 * @return
	 */
	@RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
	public ModelAndView saveProfil(int idEtu, String posteActu, String villeActu, String nomEntreprise, String mail,
			String numTelephone) {
		System.out.println(idEtu + posteActu + villeActu + nomEntreprise + mail + numTelephone);
		etudiantBean.updateEtudiant(idEtu, posteActu, villeActu, nomEntreprise, numTelephone);
		return new ModelAndView("redirect:profil/" + idEtu, "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/saveExpPro", method = RequestMethod.POST)
	public ModelAndView saveExpPro(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		System.out.println(liste + " " + email);
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteExpProList(etudiantDTO);
		String tabExp[] = liste.split("%");
		for (int i = 0; i < tabExp.length; i++) {
			etudiantBean.addExperience(etudiantDTO, tabExp[i]);
		}

		return mapCompetencesEtudiant(etudiantDTO, request);
	}

	@RequestMapping(value = "/saveCompetence", method = RequestMethod.POST)
	public ModelAndView saveCompetence(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteCompetenceList(etudiantDTO);
		String tabExp[] = liste.split("%");
		for (int i = 0; i < tabExp.length; i++) {
			etudiantBean.addCompetence(etudiantDTO, tabExp[i]);
		}
		return mapCompetencesEtudiant(etudiantDTO, request);
	}

	@RequestMapping(value = "/saveFormation", method = RequestMethod.POST)
	public ModelAndView saveFormation(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteFormationList(etudiantDTO);
		String tabExp[] = liste.split("%");
		for (int i = 0; i < tabExp.length; i++) {
			etudiantBean.addEcole(etudiantDTO, tabExp[i]);
		}
		return mapCompetencesEtudiant(etudiantDTO, request);
	}

	@RequestMapping(value = "/saveLoisir", method = RequestMethod.POST)
	public ModelAndView saveLoisir(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteLoisirList(etudiantDTO);
		String tabExp[] = liste.split("%");
		for (int i = 0; i < tabExp.length; i++) {
			etudiantBean.addHobbie(etudiantDTO, tabExp[i]);
		}
		return mapCompetencesEtudiant(etudiantDTO, request);
	}

	public ModelAndView mapCompetencesEtudiant(EtudiantDTO etudiantDTO, HttpServletRequest request) {
		List<HobbieDTO> listLoisir = etudiantBean.getHobbies(etudiantDTO);
		List<EcoleDTO> listEcole = etudiantBean.getEcoles(etudiantDTO);
		List<ExperienceDTO> listExpPro = etudiantBean.getExperiences(etudiantDTO);
		List<CompetenceDTO> listCompetence = etudiantBean.getCompetences(etudiantDTO);
		etudiantDTO.getProfil().setMesCompetences(listCompetence);
		etudiantDTO.getProfil().setMesEcoles(listEcole);
		etudiantDTO.getProfil().setMesExperiences(listExpPro);
		etudiantDTO.getProfil().setMesHobbies(listLoisir);
		request.getSession().setAttribute("etudiant", etudiantDTO);
		return new ModelAndView("redirect:profil/" + etudiantDTO.getId(), "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView test(int id) {
		System.out.println("OK !");
		return new ModelAndView("redirect:profil/" + id, "command", new EtudiantDTO());
	}

}
