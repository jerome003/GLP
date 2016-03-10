package ipint15.glp.webclient.controller;


import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;

@Controller
@SessionAttributes
public class ConnexionEtudiantController {
	@Inject
	protected EtudiantCatalogRemote etudiantBean;
	@Inject
	protected GroupeRemote groupeBean;

	@ModelAttribute("groupeList")
	public List<GroupeDTO> getGroupe() {
		return groupeBean.getAllGroupeInstitutionnel();
	}

	public static final String ATTR_CAS = "_const_cas_assertion_";

	@RequestMapping(value = "/connexionEtudiant", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "connexion");

		if (request.getUserPrincipal() != null) {
			AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
			Map attributes = principal.getAttributes();
			Iterator attributeNames = attributes.keySet().iterator();
			String mail = (String) attributes.get("mail");
			String nom = (String) attributes.get("name");
			String delims = " ";
			String[] tokens = nom.split(delims);
			sessionObj.setAttribute("nom", tokens[0]);
			sessionObj.setAttribute("prenom", tokens[1]);
			sessionObj.setAttribute("mail", mail);
			EtudiantDTO etu = etudiantBean.getEtudiantByMail(mail);
			if (etudiantBean.getEtudiantByMail(mail) != null) {
				System.out.println("etudiant trouvé !!!");
				request.getSession().setAttribute("type","etudiant");
				sessionObj.setAttribute("etudiant", etu);
				return new ModelAndView("redirect:fil-actualite-etudiant", "command", new PublicationDTO());
			} else {
				System.out.println("Etudiant pas inscrit !!!!");
			}
		}
		sessionObj.setAttribute("section", "inscription");
		return new ModelAndView("connexionEtudiant", "command", new EtudiantDTO());
	}

	// Methode d'ajout d'un etudiant
	@RequestMapping(value = "/addNewEtudiant", method = RequestMethod.POST)
	public String addNewEtudiant(@Valid @ModelAttribute("command") EtudiantDTO etudiant, BindingResult result,
			HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		GroupeDTO newGroupe = groupeBean.getGroupeDTOById(etudiant.getGroupe().getId());
		EtudiantDTO etu = etudiantBean.createEtudiant((String) sessionObj.getAttribute("nom"), (String) sessionObj.getAttribute("prenom"),
				(String) sessionObj.getAttribute("mail"), newGroupe);
		request.getSession().setAttribute("type","etudiant");
		sessionObj.setAttribute("etudiant", etu);
		return "inscriptionEtudiantOK";
	}

}
