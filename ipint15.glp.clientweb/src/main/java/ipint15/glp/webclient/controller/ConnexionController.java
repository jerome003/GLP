package ipint15.glp.webclient.controller;

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
		// return "inscription";
	}

	@RequestMapping(value = "/connexionProfil", method = RequestMethod.POST)
	public String connexion(@ModelAttribute("command") EtudiantDTO etudiant, BindingResult result) {
		if (etudiantBean.connexion(etudiant.getEmail(), etudiant.getPassword())) {
			return "profil";
		} else {
			return "connexion";
		}
	}

	@RequestMapping(value = "/deconnection", method = RequestMethod.POST)
	public String deconnection(@ModelAttribute("command") EtudiantDTO etudiant, BindingResult result) {
		// TODO JBG
		return "connexion";
	}
}
