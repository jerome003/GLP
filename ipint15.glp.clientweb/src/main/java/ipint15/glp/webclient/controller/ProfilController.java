package ipint15.glp.webclient.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		sessionObj.setAttribute("section", "profil");
		model.addAttribute("name",name);
		model.addAttribute("prenom",prenom);
		model.addAttribute("myInjectedBean", etudiantBean );
		return new ModelAndView("profil", "command", new EtudiantDTO());
		
	}
}
