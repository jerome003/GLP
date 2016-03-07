package ipint15.glp.webclient.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.EnseignantCatalogRemote;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.api.remote.PublicationRemote;

@Controller
@SessionAttributes
public class FilActualiteController {

	@Inject
	protected AncienEtudiantCatalogRemote etudiantBean;
	@Inject
	protected EtudiantCatalogRemote etuBean;
	@Inject
	protected EnseignantCatalogRemote enseignantBean;
	@Inject
	protected PublicationRemote publicationBean;
	@Inject
	protected GroupeRemote groupeBean;

	@RequestMapping(value = "/fil-actualite", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		// TODO impacter les roles
		HttpSession sessionObj = request.getSession();
		try {


			if (sessionObj.getAttribute("type").equals("ancien")) {
				sessionObj.setAttribute("section", "actualite");
				model.addAttribute("myInjectedBean", publicationBean);
				AncienEtudiantDTO etu = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");

				List<GroupeDTO> listeGroupes = new ArrayList<GroupeDTO>();
				listeGroupes = etudiantBean.getLesGroupes(etu);
				listeGroupes.add(etu.getGroupe());
				model.addAttribute("listeGroupes", listeGroupes);
				return new ModelAndView("fil-actualite", "command", new PublicationDTO());
			} 

			if (sessionObj.getAttribute("type").equals("etudiant")) {
				sessionObj.setAttribute("section", "actualite");
				model.addAttribute("myInjectedBean", publicationBean);
				EtudiantDTO etu = (EtudiantDTO) sessionObj.getAttribute("etudiant");
				//				List<GroupeDTO> listeGroupes = groupeBean.getGroupesOfAncienByIdAncien(etu.getId());
				List<GroupeDTO> listeGroupes = new ArrayList<GroupeDTO>();
				listeGroupes = etuBean.getLesGroupes(etu);
//				listeGroupes.add(etu.getGroupe());
				model.addAttribute("listeGroupes", listeGroupes);
				return new ModelAndView("fil-actualite", "command", new PublicationDTO());
			}

			if (sessionObj.getAttribute("type").equals("prof")) {
				sessionObj.setAttribute("section", "actualite");
				model.addAttribute("myInjectedBean", publicationBean);
				EnseignantDTO en = (EnseignantDTO) sessionObj.getAttribute("etudiant");
				//				List<GroupeDTO> listeGroupes = groupeBean.getGroupesOfAncienByIdAncien(etu.getId());
				List<GroupeDTO> listeGroupes = new ArrayList<GroupeDTO>();
				listeGroupes = enseignantBean.getLesGroupes(en);
//				listeGroupes.add(etu.getGroupe());
				model.addAttribute("listeGroupes", listeGroupes);
				return new ModelAndView("fil-actualite", "command", new PublicationDTO());
			}
			
			ModelAndView modele = new ModelAndView("errorAccesRole");
			return modele;

		} catch (NullPointerException e) {
			ModelAndView modele = new ModelAndView("errorAccesRole");
			return modele;
		}
	}

	@RequestMapping(value = "/addPublication", method = RequestMethod.POST)
	public ModelAndView addPublication(@ModelAttribute("command") PublicationDTO publication, BindingResult result,
			HttpServletRequest request) {
		// TODO impacter les roles
		System.out.println(publication);
		System.out.println("grp : " + publication.getGroupeDTO());
		HttpSession sessionObj = request.getSession();
		if (sessionObj.getAttribute("type").equals("ancien")) {
			AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
			// publicationBean.addPublication(eDTO, publication.getTitre(),
			// publication.getMessage(), new Date(),
			// publication.isPublicationPublic());
			if (publication.getGroupeDTO().getId() == -1) {
				publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
						null);
			} else {
				publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
						publication.getGroupeDTO());
			}
			List<PublicationDTO> myPublications = publicationBean.getAllPublications(null, -1);
		}
		
		if (sessionObj.getAttribute("type").equals("etudiant")) {
			EtudiantDTO eDTO = (EtudiantDTO) sessionObj.getAttribute("etudiant");
			
			if (publication.getGroupeDTO().getId() == -1) {
				publicationBean.addPublicationEtudiant(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
						null);
			} else {
				publicationBean.addPublicationEtudiant(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
						publication.getGroupeDTO());
			}
			List<PublicationDTO> myPublications = publicationBean.getAllPublicationsEtudiant(null, -1);
		}
		
		if (sessionObj.getAttribute("type").equals("prof")) {
			EnseignantDTO eDTO = (EnseignantDTO) sessionObj.getAttribute("etudiant");
			
			if (publication.getGroupeDTO().getId() == -1) {
				publicationBean.addPublicationEnseignant(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
						null);
			} else {
				publicationBean.addPublicationEnseignant(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
						publication.getGroupeDTO());
			}
			List<PublicationDTO> myPublications = publicationBean.getAllPublicationsEnseignant(null, -1);
		}
		/*
		 * //Ajout d'une compétence pour notre étudiant
		 * etudiantBean.addCompetence(eDTO, "Football");
		 * 
		 * // Affichage de la liste des Compétences List<CompetenceDTO>
		 * mesCompetences = etudiantBean.getCompetences(eDTO); it =
		 * mesCompetences.iterator(); while(it.hasNext()) { System.out.println(
		 * "Mes compétences :" +it.next().toString()); }
		 */
		return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
	}

	@RequestMapping(value = "/myPublication", method = RequestMethod.GET)
	public ModelAndView myPublication(HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("choixPublication", "mesPublications");
		return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());

	}

	@RequestMapping(value = "/allPublication", method = RequestMethod.GET)
	public ModelAndView allPublication(HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("prof")
					|| sessionObj.getAttribute("type").equals("etudiant")) {
				sessionObj.setAttribute("choixPublication", "lesPublications");

				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			} else {
				return new ModelAndView("errorAccesRole");
			}
		} catch (NullPointerException e) {
			return new ModelAndView("errorAccesRole");
		}

	}

	@RequestMapping(value = "/getPublication", method = RequestMethod.GET)
	public ModelAndView getPublication(HttpServletRequest request, int idGroupe, boolean myPublications) {
		System.out.println("idGrp : " + idGroupe + " bool : " + myPublications);
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("prof")
					|| sessionObj.getAttribute("type").equals("etudiant")) {
				if (myPublications) {
					sessionObj.setAttribute("choixPublication", "mesPublications");
				} else {
					sessionObj.setAttribute("choixPublication", "lesPublications");
				}
				sessionObj.setAttribute("idGroupe", idGroupe);
				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			} else {
				return new ModelAndView("errorAccesRole");
			}
		} catch (NullPointerException e) {
			return new ModelAndView("errorAccesRole");
		}

	}
}
