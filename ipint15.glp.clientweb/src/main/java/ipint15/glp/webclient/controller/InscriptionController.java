package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class InscriptionController {
	

	
	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
	return "inscription";
	}

}
