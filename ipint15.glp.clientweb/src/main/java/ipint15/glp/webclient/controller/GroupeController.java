package ipint15.glp.webclient.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
public class GroupeController {

	@Inject
	protected GroupeRemote groupeBean;
	@Inject
	protected PublicationRemote publicationBean;
	@Inject
	protected AncienEtudiantCatalogRemote ancienEtudiantBean;
	@Inject
	protected EtudiantCatalogRemote etudiantBean;
	@Inject
	protected EnseignantCatalogRemote enseignantBean;


	@RequestMapping(value = "/groupe/{id}", method = RequestMethod.GET)
	public ModelAndView groupeConsult(HttpServletRequest request, @PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") ) {
				int id = Integer.parseInt(pathVariables.get("id"));

				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");

				int idMembre = eDTO.getId();

				if (groupeBean.peutRejoindreGroupeAncien(id, eDTO) == true) {

					sessionObj.setAttribute("peutRejoindreGroupe", true);

				} else {

					sessionObj.setAttribute("peutRejoindreGroupe", false);
				}

				if (groupeBean.membreExistInListGroupe(id, idMembre)) {
					sessionObj.setAttribute("peutPublier", true);
				} else {
					sessionObj.setAttribute("peutPublier", false);
				}
				if (groupeBean.peutQuitterGroupeAncien(id, eDTO) == true) {
					sessionObj.setAttribute("peutQuitterGroupe", true);
				} else {
					sessionObj.setAttribute("peutQuitterGroupe", false);
				}
				ModelAndView model = new ModelAndView("groupe", "command", new PublicationDTO());

				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(id);
				List<PublicationDTO> listPublications = publicationBean.getAllGroupPublications(id ,idMembre, "ancien");
				groupeDTO.setListPublications(listPublications);
				// TODO
				// model.setViewName("groupe");
				sessionObj.setAttribute("groupe", groupeDTO);
				return model;
			} 
			if (sessionObj.getAttribute("type").equals("etudiant") ) {
				int id = Integer.parseInt(pathVariables.get("id"));

				EtudiantDTO eDTO = (EtudiantDTO) sessionObj.getAttribute("etudiant");
				int idMembre = eDTO.getId();

//
//				if (groupeBean.peutRejoindreGroupe(id, idMembre) == true) {
//
//					sessionObj.setAttribute("peutRejoindreGroupe", true);
//
//				} else {
//
//					sessionObj.setAttribute("peutRejoindreGroupe", false);
//				}
//
				if (groupeBean.membreEtudiantExistInListGroupe(id, idMembre)) {

					sessionObj.setAttribute("peutPublier", true);

				} else {
					sessionObj.setAttribute("peutPublier", false);

				}
//				if (groupeBean.peutQuitterGroupe(id, idMembre) == true) {
//					sessionObj.setAttribute("peutQuitterGroupe", true);
//				} else {
//					sessionObj.setAttribute("peutQuitterGroupe", false);
//				}
				ModelAndView model = new ModelAndView("groupe", "command", new PublicationDTO());

				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(id);
				List<PublicationDTO> listPublications = publicationBean.getAllGroupPublications(id, idMembre, "etudiant");
				groupeDTO.setListPublications(listPublications);
				// TODO
				// model.setViewName("groupe");
				sessionObj.setAttribute("groupe", groupeDTO);
				return model;
			}
			if (sessionObj.getAttribute("type").equals("prof") ) {
				int id = Integer.parseInt(pathVariables.get("id"));

				EnseignantDTO eDTO = (EnseignantDTO) sessionObj.getAttribute("etudiant");
				int idMembre = eDTO.getId();
//
//				if (groupeBean.peutRejoindreGroupe(id, idMembre) == true) {
//
//					sessionObj.setAttribute("peutRejoindreGroupe", true);
//
//				} else {
//
//					sessionObj.setAttribute("peutRejoindreGroupe", false);
//				}
//
				if (groupeBean.membreEnseignantExistInListGroupe(id, idMembre)) {
					sessionObj.setAttribute("peutPublier", true);

				} else {
					sessionObj.setAttribute("peutPublier", false);
				}
//				if (groupeBean.peutQuitterGroupe(id, idMembre) == true) {
//					sessionObj.setAttribute("peutQuitterGroupe", true);
//				} else {
//					sessionObj.setAttribute("peutQuitterGroupe", false);
//				}
				ModelAndView model = new ModelAndView("groupe", "command", new PublicationDTO());

				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(id);
				List<PublicationDTO> listPublications = publicationBean.getAllGroupPublications(id, idMembre,"prof");
				groupeDTO.setListPublications(listPublications);
				// TODO
				// model.setViewName("groupe");
				sessionObj.setAttribute("groupe", groupeDTO);
				return model;
			}
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;

		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}
	}

	// ecrire une foction qui permet de rejoindre un groupe !

	@RequestMapping(value = "/rejoindreGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView rejoindreGroupe(HttpServletRequest request, @PathVariable String id) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien")) {
				int idp = Integer.parseInt(id);
				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
				ancienEtudiantBean.addGroupeInLesGroupesNonInstitEtudiant(eDTO, groupeDTO);

				return new ModelAndView("redirect:/groupe/" + id);
			}
			//			if (sessionObj.getAttribute("type").equals("etudiant") ) {
			//				int idp = Integer.parseInt(id);
			//				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
			//				EtudiantDTO eDTO = (EtudiantDTO) sessionObj.getAttribute("etudiant");
			//				etudiantBean.addGroupeInLesGroupesNonInstitEtudiant(eDTO, groupeDTO);
			//
			//				return new ModelAndView("redirect:/groupe/" + id);
			//			}
			//			if (sessionObj.getAttribute("type").equals("prof")) {
			//				int idp = Integer.parseInt(id);
			//				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
			//				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
			//				ancienEtudiantBean.addGroupeInLesGroupesNonInstitEtudiant(eDTO, groupeDTO);
			//
			//				return new ModelAndView("redirect:/groupe/" + id);
			//			}
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;

		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}

	}




	@RequestMapping(value = "/nonInstitGroupe", method = RequestMethod.GET)
	public ModelAndView homeNonInstitGroupes(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {

			if (sessionObj.getAttribute("type").equals("ancien")) {
				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
				List<GroupeDTO> listeResultat = groupeBean.getAllMesGroupesNonInstitutionnel(eDTO);
				ModelAndView modelView = new ModelAndView("nonInstitGroupe", "command", new GroupeDTO());
				modelView.addObject("liste", listeResultat);
				model.addAttribute("myInjectedBean", groupeBean);
				return modelView;
			}
			else{
				ModelAndView modelView = new ModelAndView("errorAccesRole");
				return modelView;
			}
		} catch (NullPointerException e) {
			ModelAndView modelView = new ModelAndView("errorAccesRole");
			return modelView;
		}
	}




	@RequestMapping(value = "/saveGroupeNonInstit", method = RequestMethod.POST)
	public ModelAndView saveGroupeNonInstit(HttpServletRequest request,String nameGroupe, String descriptionGroupe) {		
		ModelAndView modelView;	
		GroupeDTO gDTO = groupeBean.createGroupe(nameGroupe, descriptionGroupe, false);
		HttpSession sessionObj = request.getSession();
		AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");		
		ancienEtudiantBean.addAnimateurToGroupe(eDTO, gDTO);	
		ancienEtudiantBean.addGroupeInLesGroupesNonInstitEtudiant(eDTO, gDTO);		
		List<GroupeDTO> listeResultat = groupeBean.getAllMesGroupesNonInstitutionnel(eDTO);
		modelView = new ModelAndView("redirect:/nonInstitGroupe", "command", new GroupeDTO());
		modelView.addObject("liste", listeResultat);
		modelView.addObject("creation", "ok");
		return modelView;

	}


	@RequestMapping(value = "/removeGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView removeGroup(Locale locale, Model model, HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
		ModelAndView modelView ;
		modelView = new ModelAndView("redirect:/nonInstitGroupe");

		int idgroupe = Integer.parseInt(pathVariables.get("id"));
		if (groupeBean.removeGroupeNonInstit(idgroupe, eDTO.getId())) {

			List<GroupeDTO> listeResultat = groupeBean.getAllGroupe();
			modelView = new ModelAndView("redirect:/nonInstitGroupe", "command", new GroupeDTO());
			modelView.addObject("liste", listeResultat);

			modelView.addObject("delete", "ok");
			return modelView;
		}

		modelView.addObject("delete", "ko");
		return modelView;
	}




	@RequestMapping(value = "/quitterGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView quitterGroupe(HttpServletRequest request, @PathVariable String id) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien")) {
				int idp = Integer.parseInt(id);
				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
				ancienEtudiantBean.removeGroupeInLesGroupes(eDTO, groupeDTO);

				return new ModelAndView("redirect:/groupe/" + id);
			} 
			//			if (sessionObj.getAttribute("type").equals("etudiant") ) {
			//				int idp = Integer.parseInt(id);
			//				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
			//				EtudiantDTO eDTO = (EtudiantDTO) sessionObj.getAttribute("etudiant");
			//				etudiantBean.removeGroupeInLesGroupes(eDTO, groupeDTO);
			//
			//				return new ModelAndView("redirect:/groupe/" + id);
			//			} 
			//			if (sessionObj.getAttribute("type").equals("prof") ) {
			//				int idp = Integer.parseInt(id);
			//				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
			//				EnseignantDTO eDTO = (EnseignantDTO) sessionObj.getAttribute("etudiant");
			//				enseignantBean.removeGroupeInLesGroupes(eDTO, groupeDTO);
			//
			//				return new ModelAndView("redirect:/groupe/" + id);
			//			} 
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;

		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}

	}


	/*En cours de modification*/

	@RequestMapping(value = "*/addPublicationGroupe", method = RequestMethod.POST)
	public ModelAndView addPublication(@ModelAttribute("command") PublicationDTO publication, BindingResult result,
			HttpServletRequest request) {
		//Ajouter le lien dans groupe --> grp.setPublication(getPublication.add(publication))
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien")) {
				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
				if (publication.getGroupeDTO().getId() == -1) {
					publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(),
							true, null);
				} else {

					publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(),
							true, publication.getGroupeDTO());
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
			return new ModelAndView("redirect:" + publication.getGroupeDTO().getId(), "command",
					new PublicationDTO());

		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}


	}
}
