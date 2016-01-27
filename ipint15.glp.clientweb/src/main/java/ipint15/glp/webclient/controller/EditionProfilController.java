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
			String numTelephone, String facebook, String twitter, String viadeo, String linkedin, String attentes) {
		System.out.println(idEtu + posteActu + villeActu + nomEntreprise + mail + numTelephone);
		etudiantBean.updateEtudiant(idEtu, posteActu, villeActu, nomEntreprise, numTelephone, facebook, twitter, viadeo,
				linkedin, attentes);
		return new ModelAndView("redirect:profil/" + idEtu, "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/saveExpPro", method = RequestMethod.POST)
	public ModelAndView saveExpPro(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {

		System.out.println(liste + " " + email);
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteExpProList(etudiantDTO);
		if(liste == null || liste.length() < 9){
			return null;
		}
		else{

			String tabExp[] = liste.split("%");
			//TODO ajouter une exception, si le le tableau est vide on fait rien --> vérif avec le if !!
			for (int i = 0; i < tabExp.length; i++) {
				String tabExpTmp[] = tabExp[i].split("\\|");
				etudiantBean.addExperience(etudiantDTO, tabExpTmp[0].replaceAll("|", ""), tabExpTmp[1].replaceAll("|", ""),
						tabExpTmp[2].replaceAll("|", ""), tabExpTmp[3].replaceAll("|", ""), tabExpTmp[4].replaceAll("|", ""), tabExpTmp[5].replaceAll("|", ""), tabExpTmp[6].replaceAll("|", ""), tabExpTmp[7].replaceAll("|", ""));
			}
			return mapCompetencesEtudiant(etudiantDTO, request);
		}

	}

	@RequestMapping(value = "/saveCompetence", method = RequestMethod.POST)
	public ModelAndView saveCompetence(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteCompetenceList(etudiantDTO);
		String tabExp[] = liste.split("%");
		for (int i = 0; i < tabExp.length; i++) {
			String tabExpTmp[] = tabExp[i].split("\\|");
			System.out.println(tabExpTmp);
			etudiantBean.addCompetence(etudiantDTO, tabExpTmp[0], Integer.parseInt(tabExpTmp[1]));
		}
		return mapCompetencesEtudiant(etudiantDTO, request);
	}




	/**
	 * Permet de sauvegarder une formation ajouté par l'utilisateur 
	 * @param email
	 * @param liste
	 * @param request
	 * @return
	 */




	@RequestMapping(value = "/saveFormation", method = RequestMethod.POST)
	public ModelAndView saveFormation(@RequestParam("mail") String email, @RequestParam("maListe") String liste,
			HttpServletRequest request) {
		System.out.println(liste + " " + email);
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(email);
		etudiantBean.deleteFormationList(etudiantDTO);
		if(liste == null || liste.length() < 7){
			return null;
		}
		else{

			String tabEcole[] = liste.split("%");
			for (int i = 0; i < tabEcole.length; i++) {
				String tabEcoleTmp[] = tabEcole[i].split("\\|");
				etudiantBean.addEcole(etudiantDTO, tabEcoleTmp[0].replaceAll("|", ""),tabEcoleTmp[1].replaceAll("|", ""),tabEcoleTmp[2].replaceAll("|", ""),tabEcoleTmp[3].replaceAll("|", ""),tabEcoleTmp[4].replaceAll("|", ""),tabEcoleTmp[5].replaceAll("|", ""),tabEcoleTmp[6].replaceAll("|", ""));
			}
			return mapCompetencesEtudiant(etudiantDTO, request);
		}
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
}