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
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
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

	@RequestMapping(value = "/groupe/{id}", method = RequestMethod.GET)
	public ModelAndView groupeConsult(HttpServletRequest request, @PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("prof")) {
				int id = Integer.parseInt(pathVariables.get("id"));

				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
				int idMembre = eDTO.getId();

				if (groupeBean.peutRejoindreGroupe(id, idMembre) == true) {
				
					sessionObj.setAttribute("peutRejoindreGroupe", true);

				} else {
				
					sessionObj.setAttribute("peutRejoindreGroupe", false);
				}

				if (groupeBean.membreExistInListGroupe(id, idMembre)) {
					sessionObj.setAttribute("peutPublier", true);
				} else {
					sessionObj.setAttribute("peutPublier", false);
				}
				if (groupeBean.peutQuitterGroupe(id, idMembre) == true) {
					System.out.println("passe dans peut quitter groupe");
					sessionObj.setAttribute("peutQuitterGroupe", true);
				} else {
					System.out.println("peut quitter groupe passe pas ");
					sessionObj.setAttribute("peutQuitterGroupe", false);
				}
				ModelAndView model = new ModelAndView("groupe", "command", new PublicationDTO());

				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(id);
				List<PublicationDTO> listPublications = publicationBean.getAllGroupPublications(id);
				groupeDTO.setListPublications(listPublications);
				// TODO
				// model.setViewName("groupe");
				sessionObj.setAttribute("groupe", groupeDTO);
				return model;
			} else {
				ModelAndView model = new ModelAndView("errorAccesRole");
				return model;
			}
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
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("prof")) {
				int idp = Integer.parseInt(id);
				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
				ancienEtudiantBean.addGroupeInLesGroupesNonInstitEtudiant(eDTO, groupeDTO);

				return new ModelAndView("redirect:/groupe/" + id);
			} else {
				ModelAndView model = new ModelAndView("errorAccesRole");
				return model;
			}
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
		System.out.println("passe dans le save ");		
		ModelAndView modelView;	
		GroupeDTO gDTO = groupeBean.createGroupe(nameGroupe, descriptionGroupe, false);
		System.out.println("le groupe a été créé");
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
	

	@RequestMapping(value = "/quitterGroupe/{id}", method = RequestMethod.GET)
	public ModelAndView quitterGroupe(HttpServletRequest request, @PathVariable String id) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("prof")) {
				int idp = Integer.parseInt(id);
				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(idp);
				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
				ancienEtudiantBean.removeGroupeInLesGroupes(eDTO, groupeDTO);

				return new ModelAndView("redirect:/groupe/" + id);
			} else {
				ModelAndView model = new ModelAndView("errorAccesRole");
				return model;
			}
		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}

	}

	@RequestMapping(value = "*/addPublicationGroupe", method = RequestMethod.POST)
	public ModelAndView addPublication(@ModelAttribute("command") PublicationDTO publication, BindingResult result,
			HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("prof")) {
				System.out.println(publication);
				System.out.println("grp : " + publication.getGroupeDTO());
				AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");

				if (publication.getGroupeDTO().getId() == -1) {
					publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(),
							true, null);
				} else {
					publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(),
							true, publication.getGroupeDTO());
				}
				// List<PublicationDTO> myPublications =
				// publicationBean.getAllPublications(null, -1);

				return new ModelAndView("redirect:" + publication.getGroupeDTO().getId(), "command",
						new PublicationDTO());
			} else {
				ModelAndView model = new ModelAndView("errorAccesRole");
				return model;
			}
		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}

	}
}
