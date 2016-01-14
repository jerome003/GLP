package ipint15.glp.domain.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Ecole;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Publication;
import ipint15.glp.domain.entities.Experience;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Hobbie;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class EtudiantCatalogImpl implements EtudiantCatalogRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	public EtudiantCatalogImpl() {
	}

	private Etudiant getEtudiantByMail(String mail) {

		Query q = em.createQuery("select o from Etudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		Etudiant e = (Etudiant) q.getSingleResult();

		return e;
	}

	private Etudiant getEtudiantById(int id) {
		Query q = em.createQuery("select o from Etudiant o WHERE o.id = :id");
		q.setParameter("id", id);
		Etudiant e = (Etudiant) q.getSingleResult();
		return e;
	}

	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}

	@Override
	public EtudiantDTO createEtudiant(String firstname, String lastname, Civilite civilite, String email,
			String numTelephone, String password, Date naissance, String posteActu, String villeActu,
			String nomEntreprise, String diplome, int anneeDiplome, GroupeDTO groupe) {
		// Création de l'étudiant
		Etudiant e = new Etudiant();
		e.setPrenom(firstname);
		e.setNom(lastname);
		e.setCivilite(civilite);
		e.setEmail(email);
		e.setNumTelephone(numTelephone);
		e.setPassword("password");
		e.setNaissance(naissance);

		e.setPosteActu(posteActu);
		e.setVilleActu(villeActu);
		e.setNomEntreprise(nomEntreprise);

		e.setDiplome(diplome);
		e.setAnneeDiplome(anneeDiplome);

		Groupe p = getGroupeById(groupe.getId());

		e.setGroupe(p);
		p.getEtudiants().add(e);


		// Création du profil de l'étudiant
		EtudiantProfil ep = new EtudiantProfil();

		// Mappage entre l'étudiant et son profil
		ep.setEtudiant(e);
		e.setProfil(ep);

		// Persistance de l'étudiant et du profil en BDD
		em.persist(ep);
		em.persist(e);
		em.merge(p);

		// Mapping EtudiantDTO et ProfilDTO pour retourner un etudiantDTO à la
		// couche présentation
		EtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
		return eDTO;

	}

	@Override
	public EtudiantDTO getEtudiant(String email) {
		Etudiant e = getEtudiantByMail(email);

		if (e != null) {
			EtudiantProfil ep = e.getProfil();
			EtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			return eDTO;
		}

		// a remplacer par le renvoie d'une exception lorsqu'aucun email ne
		// correspond à celui en parametre
		return null;
	}

	@Override
	public EtudiantDTO getEtudiant(int id) {
		Etudiant e = getEtudiantById(id);
		if (e != null) {
			EtudiantProfil ep = e.getProfil();
			EtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			return eDTO;
		}
		// a remplacer par le renvoie d'une exception lorsqu'aucun id ne
		// correspond à celui en parametre
		return null;

	}

	@Override
	public List<EtudiantDTO> listEtudiant() {
		List<Etudiant> ps = em.createQuery("select o from Etudiant o").getResultList();
		List<EtudiantDTO> psDTO = new ArrayList<EtudiantDTO>();

		for (Etudiant e : ps) {
			EtudiantProfil ep = e.getProfil();
			EtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			psDTO.add(eDTO);
		}
		return psDTO;
	}

	@Override
	public boolean connexion(String email, String password) {

		Etudiant e = getEtudiantByMail(email);
		if (e != null && (e.getPassword().equals(password))) {
			System.out.println("connexion etablie");
			return true;
		}

		System.out.println("connexion refusee");
		return false;
	}

	@Override
	public void addCompetence(EtudiantDTO eDTO, String competence) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Competence c = new Competence();
		c.setLibelle(competence);
		EtudiantProfil ep = e.getProfil();
		ep.getMesCompetences().add(c);
		c.setProfil(ep);
		em.persist(c);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public List<CompetenceDTO> getCompetences(EtudiantDTO eDTO) {

		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Competence> mesCompetences = e.getProfil().getMesCompetences();
		List<CompetenceDTO> mesCompetencesDTO = new ArrayList<CompetenceDTO>();

		for (Competence c : mesCompetences) {
			CompetenceDTO competenceDTO = ce.MappingProfilCompetence(e.getProfil(), c);
			mesCompetencesDTO.add(competenceDTO);
		}

		return mesCompetencesDTO;

	}

	@Override
	public void addExperience(EtudiantDTO eDTO, String experience, String entreprise, String duree, String anneeDebut) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Experience exp = new Experience();
		exp.setLibelle(experience);
		EtudiantProfil ep = e.getProfil();
		ep.getMesExperiences().add(exp);
		exp.setProfil(ep);
		exp.setLibelle(experience);
		exp.setDuree(duree);
		exp.setEntreprise(entreprise);
		exp.setAnneeDebut(anneeDebut);
		em.persist(exp);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public List<ExperienceDTO> getExperiences(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Experience> mesExperiences = e.getProfil().getMesExperiences();
		List<ExperienceDTO> mesExperiencesDTO = new ArrayList<ExperienceDTO>();

		for (Experience exp : mesExperiences) {
			ExperienceDTO experienceDTO = ce.MappingProfilExperience(e.getProfil(), exp);
			mesExperiencesDTO.add(experienceDTO);
		}
		return mesExperiencesDTO;
	}

	@Override
	public GroupeDTO getGroupe(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		Groupe monGroupe = e.getGroupe();
		GroupeDTO monGroupeDTO = ce.MappingEtudiantGroupe(e, monGroupe);
		return monGroupeDTO;
	}

	@Override
	public void setGroupe(EtudiantDTO eDTO, GroupeDTO gDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		Groupe grp = getGroupeById(gDTO.getId());

		e.setGroupe(grp);
		grp.getEtudiants().add(e);

		em.merge(grp);
		em.merge(e);

	}

	@Override
	public void addHobbie(EtudiantDTO eDTO, String hobbie) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Hobbie h = new Hobbie();
		h.setLibelle(hobbie);
		EtudiantProfil ep = e.getProfil();
		ep.getMesHobbies().add(h);
		h.setProfil(ep);
		em.persist(h);
		em.merge(ep);
		em.merge(e);
	}

	@Override
	public List<HobbieDTO> getHobbies(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Hobbie> mesHobbies = e.getProfil().getMesHobbies();
		List<HobbieDTO> mesHobbiesDTO = new ArrayList<HobbieDTO>();

		for (Hobbie h : mesHobbies) {
			HobbieDTO hobbieDTO = ce.MappingProfilHobbie(e.getProfil(), h);
			mesHobbiesDTO.add(hobbieDTO);
		}
		return mesHobbiesDTO;
	}

	@Override
	public void addEcole(EtudiantDTO eDTO, String ecole) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Ecole formation = new Ecole();
		formation.setLibelle(ecole);
		EtudiantProfil ep = e.getProfil();
		ep.getMesEcoles().add(formation);
		formation.setProfil(ep);
		em.persist(formation);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public List<EcoleDTO> getEcoles(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Ecole> mesEcoles = e.getProfil().getMesEcoles();
		List<EcoleDTO> mesEcolesDTO = new ArrayList<EcoleDTO>();

		for (Ecole formation : mesEcoles) {
			EcoleDTO ecoleDTO = ce.MappingProfilEcole(e.getProfil(), formation);
			mesEcolesDTO.add(ecoleDTO);
		}
		return mesEcolesDTO;
	}

	@Override
	public List<PublicationDTO> getPublications(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Publication> mesPublications = e.getProfil().getMesPublications();
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication c : mesPublications) {
			PublicationDTO cDTO = ce.MappingProfilPublication(e.getProfil(), c);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;

	}

	@Override
	public List<PublicationDTO> getPublications() {
		List<Publication> mesPublications = em.createQuery("select p from Publication p order by p.date desc")
				.getResultList();
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication p : mesPublications) {
			System.out.println("Profil :" + p.getProfil());
			PublicationDTO cDTO = ce.MappingProfilPublication(p.getProfil(), p);
			System.out.println("Profil DTO :" + cDTO.getProfil());
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;

	}

	@Override
	public void addPublication(EtudiantDTO eDTO, String titre, String message, Date date) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Publication c = new Publication();
		c.setTitre(titre);
		c.setMessage(message);
		c.setDate(date);
		EtudiantProfil ep = e.getProfil();
		ep.getMesPublications().add(c);
		c.setProfil(ep);
		em.persist(c);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public boolean isMailExists(String mail) {

		Query q = em.createQuery("select o from Etudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		Etudiant e;
		try {
			e = (Etudiant) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPasswordIsGood(String mail, String password) {
		Query q = em.createQuery("select o from Etudiant o WHERE o.email = :email and o.password = :password ");
		q.setParameter("email", mail);
		q.setParameter("password", password);
		Etudiant e;
		try {
			e = (Etudiant) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteCompetenceList(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());

		List<Competence> mesCompetences = e.getProfil().getMesCompetences();
		System.out.println("isEmpty : " + mesCompetences.isEmpty());
		System.out.println(mesCompetences);
		e.getProfil().setMesCompetences(new ArrayList<Competence>());
		em.persist(e);
		mesCompetences = e.getProfil().getMesCompetences();
		System.out.println(mesCompetences);

	}

	@Override
	public void deleteExpProList(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// List<Experience> mesExperiences = e.getProfil().getMesExperiences();
		e.getProfil().setMesExperiences(new ArrayList<Experience>());
		em.persist(e);
	}

	@Override
	public void deleteFormationList(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// List<Ecole> mesFormations = e.getProfil().getMesEcoles();
		e.getProfil().setMesEcoles(new ArrayList<Ecole>());
		em.persist(e);
	}

	@Override
	public void deleteLoisirList(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// List<Hobbie> mesLoisirs = e.getProfil().getMesHobbies();
		e.getProfil().setMesHobbies(new ArrayList<Hobbie>());
		em.persist(e);
	}

	@Override
	public void updateEtudiant(int id, String posteActu, String villeActu, String nomEntreprise, String numTelephone ,String facebook, String twitter, String viadeo, String linkedin) {

		Etudiant e = getEtudiantById(id);
		e.setPosteActu(posteActu);
		e.setVilleActu(villeActu);
		e.setNomEntreprise(nomEntreprise);
		e.setNumTelephone(numTelephone);
		if (valideLien(facebook, "facebook.com")) 
			e.setFacebook(facebook);
		if(valideLien(twitter, "twitter.com"))
			e.setTwitter(twitter);
		if(valideLien(viadeo, "viadeo.com"))
			e.setViadeo(viadeo);
		if (valideLien(linkedin, "linkedin.com"))
			e.setLinkedin(linkedin);
		em.persist(e);	
	}


	@Override
	public boolean valideLien(String lien, String site) {
		if (lien.contains(site) && (lien.contains("http://") || lien.contains("https://")))
			return true;
		return false;
	}

}