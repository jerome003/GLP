package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
public class InscriptionController {

	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "inscription";
	}

	@RequestMapping(value = "/addEtudiant", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("etudiant") EtudiantDTO etudiant, BindingResult result) {

		etudiantBean.createEtudiant(etudiant.getPrenom(), etudiant.getNom(), etudiant.getEmail(),
				etudiant.getPassword(), etudiant.getNaissance());
		
		
		etudiantBean.listEtudiant();

		return "redirect:contacts.html";
	}

}
