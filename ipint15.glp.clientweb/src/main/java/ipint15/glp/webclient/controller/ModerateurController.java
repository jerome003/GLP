package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.api.remote.GroupeRemote;

@Controller
@SessionAttributes
public class ModerateurController {

	@Inject
	protected GroupeRemote groupeBean;

	@Inject
	protected AdministrationRemote administrationBean;

	@RequestMapping(value = "/moderateur", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		try {
			if (sessionObj.getAttribute("type").equals("moderateur")) {
				sessionObj.setAttribute("section", "accueilmoderateur");
				sessionObj.setAttribute("myInjectedBean", administrationBean);
				return "moderateur";
			} else {
				return "errorAccesRole";
			}
		} catch (NullPointerException e) {
			return "errorAccesRole";
		}
	}

}
