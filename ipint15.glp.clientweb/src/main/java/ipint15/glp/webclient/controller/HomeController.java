package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	protected AncienEtudiantCatalogRemote etudiantbean;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "accueil");
		
	if( etudiantbean == null ) {
	logger.info("The bean is not injected !.");
	return "error";
	}
	// Injection works !
	model.addAttribute("myInjectedBean", etudiantbean );
	return "home";
	}
	
}
