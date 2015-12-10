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



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	protected EtudiantCatalogRemote etudiantbean;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	if( etudiantbean == null ) {
	logger.info("The bean is not injected !.");
	return "home";
	}
	// Injection works !
	model.addAttribute("myInjectedBean", etudiantbean );
	return "success";
	}
	
}
