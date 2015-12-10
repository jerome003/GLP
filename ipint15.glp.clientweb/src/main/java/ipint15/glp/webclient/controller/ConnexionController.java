package ipint15.glp.webclient.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes
public class ConnexionController {
	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {

		return new ModelAndView("connexion", "command", new EtudiantDTO());
		
		//return "inscription";
	}

	@RequestMapping(value = "/connexionProfil", method = RequestMethod.POST)
	public String connexion(@ModelAttribute("command") EtudiantDTO etudiant, BindingResult result) {
		if(etudiantBean.connexion(etudiant.getEmail(), etudiant.getPassword())){
			// pour le test je renvoie vers la page inscription, il faudra renvoyer vers la page de profil lorsqu'elle sera implentee.
			return "inscription";
		} else {
			return "connexion";
		}
	}
}
