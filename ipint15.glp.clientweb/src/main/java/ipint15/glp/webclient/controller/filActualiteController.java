package ipint15.glp.webclient.controller;

import java.util.Date;
import java.util.Iterator;
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

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes
public class filActualiteController {
	
	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/fil-actualite", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "actualite");
		model.addAttribute("myInjectedBean", etudiantBean );
	
		return new ModelAndView("fil-actualite", "command", new PublicationDTO());
	}
	
	@RequestMapping(value = "/addPublication", method = RequestMethod.POST)
	public ModelAndView addPublication(@ModelAttribute("command") PublicationDTO publication, BindingResult result, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		EtudiantDTO eDTO = (EtudiantDTO) sessionObj.getAttribute("etudiant");
		etudiantBean.addPublication(eDTO, publication.getTitre(), publication.getMessage(), new Date());
		List<PublicationDTO> myPublications = etudiantBean.getPublications();
		Iterator it = myPublications.iterator();
		while(it.hasNext()) {
			System.out.println("Iterator :" +it.next().toString());
		}
		
		/*
		 //Ajout d'une compétence pour notre étudiant
		etudiantBean.addCompetence(eDTO, "Football");
		
		// Affichage de la liste des Compétences
		List<CompetenceDTO> mesCompetences = etudiantBean.getCompetences(eDTO);
		it = mesCompetences.iterator();
		while(it.hasNext()) {
			System.out.println("Mes compétences :" +it.next().toString());
		}
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
		sessionObj.setAttribute("choixPublication", "lesPublications");
		
		return new ModelAndView("redirect:fil-actualite", "command", new PublicationDTO());
		
	}
}
