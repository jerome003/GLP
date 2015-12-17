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
	public ModelAndView home(
		@RequestParam(value= "name",required=false)String name,
		@RequestParam(value="prenom",required=false)String prenom,Model model, HttpServletRequest request){
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("consultation", false);
		sessionObj.setAttribute("section", "profil");
		model.addAttribute("name",name);
		model.addAttribute("prenom",prenom);
		model.addAttribute("myInjectedBean", etudiantBean );
		return new ModelAndView("profil", "command", new EtudiantDTO());
		
	}
	
	//permet de renvoyer la page de profil de la personne ayant l'id choisi dans l'url /profil/{id}
	@RequestMapping(value="/profil/{id}",method = RequestMethod.GET)
	public ModelAndView profilConsult(HttpServletRequest request,
			@PathVariable Map<String, String> pathVariables) 
	{
		int id = Integer.parseInt(pathVariables.get("id"));
		EtudiantDTO etu = etudiantBean.getEtudiant(id);
		HttpSession sessionObj = request.getSession();
		EtudiantDTO etudiant = (EtudiantDTO) sessionObj.getAttribute("etudiant");
		if(etudiant.getId()==id){
			sessionObj.setAttribute("consultation", false);
		} else {
			sessionObj.setAttribute("consultation", true);
		}
		ModelAndView model = new ModelAndView();
		model.addObject("etudiant",etu);
		model.setViewName("profil");
		return model;
	}
}
