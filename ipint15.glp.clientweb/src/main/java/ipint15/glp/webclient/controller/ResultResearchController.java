package ipint15.glp.webclient.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.api.remote.RechercheRemote;

@Controller
@SessionAttributes
public class ResultResearchController {

	@Inject
	protected EtudiantCatalogRemote etudiantBean;
	@Inject
	protected RechercheRemote rechercheBean;
	
	@RequestMapping(value = "/research", method = RequestMethod.POST)
	public ModelAndView recherche(@Valid HttpServletRequest request) {
		System.out.println("POST");
		String recherche = request.getParameter("recherche");
		List<EtudiantDTO> listeResultat = rechercheBean.rechercherEtudiant(recherche);
		ModelAndView model = new ModelAndView();
		model.addObject("recherche",request.getParameter("recherche"));
		model.addObject("liste",listeResultat);
		model.setViewName("resultResearch");
		return model ;
	}

}
