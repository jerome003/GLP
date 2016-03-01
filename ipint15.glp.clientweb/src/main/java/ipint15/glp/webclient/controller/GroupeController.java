package ipint15.glp.webclient.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.api.remote.PublicationRemote;

@Controller
@SessionAttributes
public class GroupeController {

	@Inject
	protected GroupeRemote groupeBean;
	@Inject
	protected PublicationRemote publicationBean;

	@RequestMapping(value = "/groupe/{id}", method = RequestMethod.GET)
	public ModelAndView groupeConsult(HttpServletRequest request, @PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("enseignant")) {
				ModelAndView model = new ModelAndView("groupe" , "command", new PublicationDTO());
				int id = Integer.parseInt(pathVariables.get("id"));
				GroupeDTO groupeDTO = groupeBean.getGroupeDTOByIdWithMemberList(id);
				List<PublicationDTO> listPublications = publicationBean.getAllGroupPublications(id);
				groupeDTO.setListPublications(listPublications);
				// TODO
				//model.setViewName("groupe");
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
	
	@RequestMapping(value = "*/addPublicationGroupe", method = RequestMethod.POST)
	public ModelAndView addPublication(@ModelAttribute("command") PublicationDTO publication, BindingResult result,
			HttpServletRequest request) {
		System.out.println(publication);
		System.out.println("grp : " + publication.getGroupeDTO());
		HttpSession sessionObj = request.getSession();
		AncienEtudiantDTO eDTO = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");

		if (publication.getGroupeDTO().getId() == -1) {
			publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
					null);
		} else {
			publicationBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date(), true,
					publication.getGroupeDTO());
		}
		List<PublicationDTO> myPublications = publicationBean.getAllPublications(null, -1);

		return new ModelAndView("redirect:"+publication.getGroupeDTO().getId(), "command", new PublicationDTO());
	}
}
