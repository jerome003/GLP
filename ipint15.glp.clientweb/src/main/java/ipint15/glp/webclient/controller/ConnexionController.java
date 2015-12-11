package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.sessions.server.ServerSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.ws.runtime.dev.Session;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes("etudiant")
public class ConnexionController {
	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/connexion", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		return new ModelAndView("connexion", "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/connexionProfil", method = RequestMethod.POST)
	public String connexion(@ModelAttribute("command") EtudiantDTO etudiant, BindingResult result,
			HttpServletRequest request) {
		if (etudiantBean.connexion(etudiant.getEmail(), etudiant.getPassword())) {
			EtudiantDTO etu = etudiantBean.getEtudiant(etudiant.getEmail());
			HttpSession sessionObj = request.getSession();
			sessionObj.setAttribute("etudiant", etu);
			return "profil";
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/deconnection", method = RequestMethod.GET)
	public String deconnection(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("etudiant", null);
		request.setAttribute("deco", "deco");
		return "home";
	}
}
