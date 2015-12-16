package ipint15.glp.webclient.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;

@Controller
@SessionAttributes
public class EditionProfilController {

	@Inject
	protected EtudiantCatalogRemote etudiantBean;

	@RequestMapping(value = "/editionProfil", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		HttpSession sessionObj = request.getSession();
		sessionObj.setAttribute("section", "profil");
		model.addAttribute("myInjectedBean", etudiantBean);
		return "editionProfil";
	}

	@RequestMapping(value = "/modifyProfil", method = RequestMethod.POST)

	public String modifyProfil(@Valid @ModelAttribute("command") EtudiantDTO etudiant, BindingResult result) {
		// TODO
		return "editionProfil";
	}

	@RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
	public ModelAndView saveProfil(String posteActu, String lieu, String entreprise, String mail) {
		System.out.println(posteActu + lieu + entreprise + mail);
		System.out.println(etudiantBean.getEtudiant(mail).getId());
		// TODO persister quand les champs existeront en base
		return new ModelAndView("redirect:editionProfil", "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/saveExpPro", method = RequestMethod.POST)
	public ModelAndView saveExpPro(String mail, String expPro1, String expPro2, String expPro3, String expPro4,
			String expPro5) {
		System.out.println(mail + expPro1 + expPro2 + expPro3 + expPro4 + expPro5);
		// TODO a finir
		return new ModelAndView("redirect:editionProfil", "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/saveCompetence", method = RequestMethod.POST)
	public ModelAndView saveCompetence(String mail, String comp1, String comp2, String comp3, String comp4,
			String comp5) {
		System.out.println(mail + comp1 + comp2 + comp3 + comp4 + comp5);
		EtudiantDTO etudiantDTO = etudiantBean.getEtudiant(mail);
		etudiantBean.addCompetence(etudiantDTO, comp1);
		etudiantBean.addCompetence(etudiantDTO, comp2);
		etudiantBean.addCompetence(etudiantDTO, comp3);
		etudiantBean.addCompetence(etudiantDTO, comp4);
		etudiantBean.addCompetence(etudiantDTO, comp5);
		return new ModelAndView("redirect:editionProfil", "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/saveFormation", method = RequestMethod.POST)
	public ModelAndView saveFormation(String mail, String formation1, String formation2, String formation3,
			String formation4, String formation5) {
		System.out.println(mail + formation1 + formation2 + formation3 + formation4 + formation5);
		// TODO a finir
		return new ModelAndView("redirect:editionProfil", "command", new EtudiantDTO());
	}

	@RequestMapping(value = "/saveLoisir", method = RequestMethod.POST)
	public ModelAndView saveLoisir(String mail, String loisir1, String loisir2, String loisir3, String loisir4,
			String loisir5) {
		System.out.println(mail + loisir1 + loisir2 + loisir3 + loisir4 + loisir5);
		// TODO a finir
		return new ModelAndView("redirect:editionProfil", "command", new EtudiantDTO());
	}

}
