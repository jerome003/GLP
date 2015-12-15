package ipint15.glp.webclient.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.api.remote.RechercheRemote;

@Controller
@SessionAttributes
public class InscriptionController {

	@Inject
	protected EtudiantCatalogRemote etudiantBean;
	@Inject
	protected RechercheRemote rechercheBean;

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "inscription");
		return new ModelAndView("inscription", "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/addEtudiant", method = RequestMethod.POST)

	public String addEtudiant(@Valid @ModelAttribute("command") EtudiantDTO etudiant, BindingResult result) {
		
		if (result.hasErrors()) {
			return "inscription";
			}
		
		EtudiantDTO eDTO = etudiantBean.createEtudiant(etudiant.getPrenom(), etudiant.getNom(), etudiant.getCivilite(), etudiant.getEmail(),
				etudiant.getPassword(), etudiant.getNaissance());
		List<EtudiantDTO> myPersons = etudiantBean.listEtudiant();
		Iterator it = myPersons.iterator();
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
		
		return "inscriptionOK";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

	}

}
