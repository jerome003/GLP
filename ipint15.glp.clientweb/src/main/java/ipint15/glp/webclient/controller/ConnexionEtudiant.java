package ipint15.glp.webclient.controller;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;

@Controller
@SessionAttributes
public class ConnexionEtudiant {
	@Inject
	protected AncienEtudiantCatalogRemote etudiantBean;
	
	@RequestMapping(value = "/connexionEtudiant", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "connexion");
		if (request.getUserPrincipal() != null) {
			AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
			Map attributes = principal.getAttributes();
			Iterator attributeNames = attributes.keySet().iterator();
//			String mail = (String) attributes.get("mail");
//			String delims = "@";
//			String part = mail.split(delims)[1];
//			String delims2 = "\\.";
//			String part2 = part.split(delims2)[0];
//			if (!part2.equals("etudiant")) {
//				sessionObj.invalidate();
//				return "home";
			if(attributes.get("nip")==null){
				System.out.println("Connection non etudiant");
			} else {
				System.out.println("etudiant");
//				etudiantBean.createEtudiant("michel", "pedro", "pedro@hotmail.fr");
//				try {
//					if (etudiantBean.getEtudiantByMail(mail) != null) {
//						System.out.println("etudiant trouvé !!!");
//						return "connexionEtudiant";
//					} else {
//						System.out.println("Etudiant pas inscrit !!!!");
//						return "connexionEtudiant";
//					}
//				} catch (NullPointerException e) {
//				}
			}
		}
		return "connexionEtudiant";
	}
}
