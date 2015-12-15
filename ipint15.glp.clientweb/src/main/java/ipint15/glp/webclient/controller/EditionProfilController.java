package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes
public class EditionProfilController {

	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/editionProfil", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "profil");

		return "editionProfil";
	}

	@RequestMapping(value = "/modifyProfil", method = RequestMethod.POST)

	public String modifyProfil(@Valid @ModelAttribute("command") EtudiantDTO etudiant, BindingResult result) {
		// TODO
		return "editionProfil";
	}
}
