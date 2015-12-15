package ipint15.glp.webclient.controller;

import javax.inject.Inject;

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
public class resultResearch {

	@Inject
	protected EtudiantCatalogRemote etudiantBean;
	
	@RequestMapping(value = "/resultResearch", method = RequestMethod.GET)
	public ModelAndView home(
		@RequestParam(value= "name",required=false)String name,
		@RequestParam(value="prenom",required=false)String prenom,Model model){
		model.addAttribute("name",name);
		model.addAttribute("prenom",prenom);
		return new ModelAndView("resultResearch", "command", new EtudiantDTO());
		
	}
}