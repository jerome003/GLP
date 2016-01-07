package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class adminController {
	
	@RequestMapping(value = "/admin/groupes", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
	HttpSession sessionObj = request.getSession();
	sessionObj.setAttribute("section", "groupes");
	return "adminGroupe";
	}

}
