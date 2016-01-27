package ipint15.glp.webclient.controller;

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

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes
public class ProfilController {
	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public ModelAndView home(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "prenom", required = false) String prenom, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("consultation", false);
		sessionObj.setAttribute("section", "profil");
		model.addAttribute("myInjectedBean", etudiantBean);
		return new ModelAndView("profil", "command", new EtudiantDTO());

	}

	// permet de renvoyer la page de profil de la personne ayant l'id choisi
	// dans l'url /profil/{id}
	@RequestMapping(value = "/profil/{id}", method = RequestMethod.GET)
	public ModelAndView profilConsult(HttpServletRequest request, @PathVariable Map<String, String> pathVariables) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("ancien")) {
		int id = Integer.parseInt(pathVariables.get("id"));
		EtudiantDTO etu = etudiantBean.getEtudiant(id);
		etu.getProfil().setMesCompetences(etudiantBean.getCompetences(etu));
		etu.getProfil().setMesEcoles(etudiantBean.getEcoles(etu));
		etu.getProfil().setMesExperiences(etudiantBean.getExperiences(etu));
		etu.getProfil().setMesHobbies(etudiantBean.getHobbies(etu));
		EtudiantDTO etudiant = (EtudiantDTO) sessionObj.getAttribute("etudiant");
		ModelAndView model = new ModelAndView();
		if (etudiant.getId() == id) {
			sessionObj.setAttribute("consultation", false);
		} else {
			sessionObj.setAttribute("consultation", true);
		}
		sessionObj.setAttribute("profil", etu);
		model.setViewName("profil");
		return model;
			}else{
				ModelAndView model = new ModelAndView("errorAccesRole");
				return model;
			}
			} catch (NullPointerException e){
				ModelAndView model = new ModelAndView("errorAccesRole");
				return model;
			}
	}
}
