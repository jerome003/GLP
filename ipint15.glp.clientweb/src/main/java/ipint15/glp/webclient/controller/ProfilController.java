package ipint15.glp.webclient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.EnseignantCatalogRemote;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes
public class ProfilController {
	@Inject
	protected AncienEtudiantCatalogRemote ancienEtudiantBean;
	@Inject
	protected EtudiantCatalogRemote etudiantBean;
	@Inject
	protected EnseignantCatalogRemote enseignantBean;

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "prenom", required = false) String prenom, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("prof")) {
				sessionObj.setAttribute("consultation", false);
				sessionObj.setAttribute("section", "profil");
				model.addAttribute("myInjectedBean", ancienEtudiantBean);
				return new ModelAndView("profil", "command", new AncienEtudiantDTO());
			} else {
				return new ModelAndView("errorAccesRole");
			}
		} catch (NullPointerException e) {
			return new ModelAndView("errorAccesRole");
		}
	}



	@RequestMapping(value = "/profilEtudiant/{id}", method = RequestMethod.GET)
	public ModelAndView profilEtudiant(HttpServletRequest request, @PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("prof")
					|| sessionObj.getAttribute("type").equals("etudiant")) {
				ModelAndView model = new ModelAndView();
				sessionObj.setAttribute("section", "profil");
				int id = Integer.parseInt(pathVariables.get("id"));
				EtudiantDTO etudiantConsultation = etudiantBean.getEtudiantById(id);
				sessionObj.setAttribute("etudiantConsultation", etudiantConsultation);
				model.setViewName("profilEtudiant");
				return model;
			}
		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}
		ModelAndView model = new ModelAndView("errorAccesRole");
		return model;
	}

	@RequestMapping(value = "/profilEnseignant/{id}", method = RequestMethod.GET)
	public ModelAndView profilEnseignant(HttpServletRequest request, @PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("prof")
					|| sessionObj.getAttribute("type").equals("etudiant")) {
				ModelAndView model = new ModelAndView();
				sessionObj.setAttribute("section", "profil");
				int id = Integer.parseInt(pathVariables.get("id"));
				EnseignantDTO enseignantConsultation = enseignantBean.getEnseignantById(id);
				sessionObj.setAttribute("enseignantConsultation", enseignantConsultation);
				model.setViewName("profilEnseignant");
				return model;
			}
		} catch (NullPointerException e) {
			ModelAndView model = new ModelAndView("errorAccesRole");
			return model;
		}
		ModelAndView model = new ModelAndView("errorAccesRole");
		return model;
	}

	// permet de renvoyer la page de profil de la personne ayant l'id choisi
	// dans l'url /profil/{id}
	@RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
	public ModelAndView profilConsult(HttpServletRequest request,Model model, @PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien") || sessionObj.getAttribute("type").equals("etudiant")
					|| sessionObj.getAttribute("type").equals("prof")) {
				int id = Integer.parseInt(pathVariables.get("id"));
				AncienEtudiantDTO etu = ancienEtudiantBean.getEtudiant(id);
				etu.getProfil().setMesCompetences(ancienEtudiantBean.getCompetences(etu));
				etu.getProfil().setMesEcoles(ancienEtudiantBean.getEcoles(etu));
				etu.getProfil().setMesExperiences(ancienEtudiantBean.getExperiences(etu));
				etu.getProfil().setMesHobbies(ancienEtudiantBean.getHobbies(etu));
				List<GroupeDTO> listeGroupes = new ArrayList<GroupeDTO>();
				listeGroupes = ancienEtudiantBean.getLesGroupes(etu);
				listeGroupes.add(etu.getGroupe());
				model.addAttribute("listeGroupes", listeGroupes);
				ModelAndView modelAndView = new ModelAndView();
				if("ancien".equals(sessionObj.getAttribute("type"))){
					AncienEtudiantDTO etudiant = (AncienEtudiantDTO) sessionObj.getAttribute("etudiant");
					if (etudiant.getId() == id) {
						sessionObj.setAttribute("consultation", false);
					} else {
						sessionObj.setAttribute("consultation", true);
					}
				}else{
					sessionObj.setAttribute("consultation", true);
				}
				sessionObj.setAttribute("profil", etu);
				modelAndView.setViewName("profil");
				return modelAndView;
			} else {
				ModelAndView modelAndView = new ModelAndView("errorAccesRole");
				return modelAndView;
			}
		} catch (NullPointerException e) {
			ModelAndView modelAndView = new ModelAndView("errorAccesRole");
			return modelAndView;
		}
	}
}
