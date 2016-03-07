package ipint15.glp.webclient.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.RechercheRemote;

@Controller
@SessionAttributes
public class ResultResearchController {

	@Inject
	protected AncienEtudiantCatalogRemote etudiantBean;
	@Inject
	protected RechercheRemote rechercheBean;

	@RequestMapping(value = { "*/research", "/research" }, method = RequestMethod.POST)
	public ModelAndView recherche(@Valid HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("prof")) {
				String recherche = request.getParameter("recherche");
				List<AncienEtudiantDTO> listeResultat = rechercheBean.rechercherAncienEtudiant(recherche);
				List<GroupeDTO> listeResultatGroupe = rechercheBean.rechercherGroupe(recherche);
				List<EtudiantDTO> listeResultatEtudiant = rechercheBean.rechercherEtudiant(recherche);
				List<EnseignantDTO> listeResultatEnseignant = rechercheBean.rechercherEnseignant(recherche);
				ModelAndView model = new ModelAndView();
				model.addObject("recherche", request.getParameter("recherche"));
				model.addObject("liste", listeResultat);
				model.addObject("listeEtu", listeResultatEtudiant);
				model.addObject("listeEnseign", listeResultatEnseignant);
				model.addObject("listeGroupe", listeResultatGroupe);
				model.setViewName("resultResearch");
				return model;
			} else {
				return new ModelAndView("errorAccesRole");
			}
		} catch (NullPointerException e) {
			return new ModelAndView("errorAccesRole");
		}
	}

}
