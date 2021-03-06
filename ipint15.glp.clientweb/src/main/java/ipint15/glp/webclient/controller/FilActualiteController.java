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
import ipint15.glp.api.remote.SuggestionRemote;

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
	@Inject
	protected SuggestionRemote suggestionBean;

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

				// Partie sur la suggestion
				List<AncienEtudiantDTO> listeAnciensSug = suggestionBean.genereSuggestionEtu(etu.getId(),true);
				model.addAttribute("listeAnciensSug", listeAnciensSug);
				List<GroupeDTO> listeGroupesSug = suggestionBean.genereSuggestionGroupe(etu.getId(),true);
				model.addAttribute("listeGroupesSug", listeGroupesSug);

				return new ModelAndView("fil-actualite", "command", new PublicationDTO());
			}
			
			if (sessionObj.getAttribute("type").equals("etudiant")) {
				sessionObj.setAttribute("section", "actualite");
				model.addAttribute("myInjectedBean", publicationBean);
				EtudiantDTO etudiant = (EtudiantDTO) sessionObj.getAttribute("etudiant");

				List<GroupeDTO> listeGroupes = new ArrayList<GroupeDTO>();

				listeGroupes = etuBean.getLesGroupes(etudiant);
				listeGroupes.add(etudiant.getGroupe());
				model.addAttribute("listeGroupes", listeGroupes);
				
				// Partie sur la suggestion
				List<AncienEtudiantDTO> listeAnciensSug = suggestionBean.genereSuggestionEtu(etudiant.getId(),false);
				model.addAttribute("listeAnciensSug", listeAnciensSug);
				List<GroupeDTO> listeGroupesSug = suggestionBean.genereSuggestionGroupe(etudiant.getId(),false);
				model.addAttribute("listeGroupesSug", listeGroupesSug);

				return new ModelAndView("fil-actualite", "command", new PublicationDTO());
			}

			if (sessionObj.getAttribute("type").equals("prof")) {
				sessionObj.setAttribute("section", "actualite");
				model.addAttribute("myInjectedBean", publicationBean);
				EnseignantDTO en = (EnseignantDTO) sessionObj.getAttribute("etudiant");
				
				List<GroupeDTO> listeGroupes = new ArrayList<GroupeDTO>();
				
				listeGroupes = enseignantBean.getLesGroupes(en);
			
				model.addAttribute("listeGroupes", listeGroupes);
				
				// Partie sur la suggestion
				List<AncienEtudiantDTO> listeAnciensSug = suggestionBean.genereSuggestionEtu(en.getId(),false);
				model.addAttribute("listeAnciensSug", listeAnciensSug);
				List<GroupeDTO> listeGroupesSug = suggestionBean.genereSuggestionGroupe(en.getId(),false);
				model.addAttribute("listeGroupesSug", listeGroupesSug);
				
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

		HttpSession sessionObj = request.getSession();
		ModelAndView modelView = new ModelAndView();
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
			//List<PublicationDTO> myPublications = publicationBean.getAllPublications(null, -1);
			modelView = new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
		}

		if (sessionObj.getAttribute("type").equals("etudiant")) {
			EtudiantDTO eDTO = (EtudiantDTO) sessionObj.getAttribute("etudiant");

			if (publication.getGroupeDTO().getId() == -1) {
				publicationBean.addPublicationEtudiant(eDTO, publication.getTitre(), publication.getMessage(),
						new Date(), true, null);
			} else {
				publicationBean.addPublicationEtudiant(eDTO, publication.getTitre(), publication.getMessage(),
						new Date(), true, publication.getGroupeDTO());
			}
			//List<PublicationDTO> myPublications = publicationBean.getAllPublicationsEtudiant(null, -1);
			modelView = new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
		}

		if (sessionObj.getAttribute("type").equals("prof")) {
			EnseignantDTO eDTO = (EnseignantDTO) sessionObj.getAttribute("etudiant");

			if (publication.getGroupeDTO().getId() == -1) {
				publicationBean.addPublicationEnseignant(eDTO, publication.getTitre(), publication.getMessage(),
						new Date(), true, null);
			} else {
				publicationBean.addPublicationEnseignant(eDTO, publication.getTitre(), publication.getMessage(),
						new Date(), true, publication.getGroupeDTO());
			}
			//List<PublicationDTO> myPublications = publicationBean.getAllPublicationsEnseignant(null, -1);
			modelView = new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
		}
		
		return modelView;
	}

	@RequestMapping(value = "/myPublication", method = RequestMethod.GET)
	public ModelAndView myPublication(HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		ModelAndView modelView = new ModelAndView();
		sessionObj.setAttribute("choixPublication", "mesPublications");
		if (sessionObj.getAttribute("type").equals("ancien")) {
			modelView = new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
		}
		if (sessionObj.getAttribute("type").equals("etudiant")) {
			modelView = new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
		}
		if (sessionObj.getAttribute("type").equals("prof")) {
			modelView = new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
		}
		return modelView;
	}

	@RequestMapping(value = "/allPublication", method = RequestMethod.GET)
	public ModelAndView allPublication(HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien")) {
				sessionObj.setAttribute("choixPublication", "lesPublications");
				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			}
			if (sessionObj.getAttribute("type").equals("etudiant")) {
				sessionObj.setAttribute("choixPublication", "lesPublications");
				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			}
			if (sessionObj.getAttribute("type").equals("prof")) {
				sessionObj.setAttribute("choixPublication", "lesPublications");
				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			}

			return new ModelAndView("errorAccesRole");

		} catch (NullPointerException e) {
			return new ModelAndView("errorAccesRole");
		}

	}

	@RequestMapping(value = "/getPublication", method = RequestMethod.GET)
	public ModelAndView getPublication(HttpServletRequest request, int idGroupe, boolean myPublications) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien")) {
				if (myPublications) {
					sessionObj.setAttribute("choixPublication", "mesPublications");
				} else {
					sessionObj.setAttribute("choixPublication", "lesPublications");
				}
				sessionObj.setAttribute("idGroupe", idGroupe);
				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			}
			if (sessionObj.getAttribute("type").equals("etudiant")) {
				if (myPublications) {
					sessionObj.setAttribute("choixPublication", "mesPublications");
				} else {
					sessionObj.setAttribute("choixPublication", "lesPublications");
				}
				sessionObj.setAttribute("idGroupe", idGroupe);
				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			}
			if (sessionObj.getAttribute("type").equals("prof")) {
				if (myPublications) {
					sessionObj.setAttribute("choixPublication", "mesPublications");
				} else {
					sessionObj.setAttribute("choixPublication", "lesPublications");
				}
				sessionObj.setAttribute("idGroupe", idGroupe);
				return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
			}

			return new ModelAndView("errorAccesRole");

		} catch (NullPointerException e) {
			return new ModelAndView("errorAccesRole");
		}

	}
}
