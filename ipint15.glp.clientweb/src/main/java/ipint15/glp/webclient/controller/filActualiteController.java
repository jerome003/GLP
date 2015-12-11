package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ipint15.glp.api.remote.EtudiantCatalogRemote;
@Controller
public class filActualiteController {
	
	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/fil-actualite", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
	return "fil-actualite";
	}
}
