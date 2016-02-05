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
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Ecole;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Publication;
import ipint15.glp.domain.entities.Experience;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Hobbie;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class AncienEtudiantCatalogImpl implements AncienEtudiantCatalogRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	public AncienEtudiantCatalogImpl() {
	}

	private AncienEtudiant getEtudiantByMail(String mail) {

		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();

		return e;
	}

	private AncienEtudiant getEtudiantById(int id) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", id);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();
		return e;
	}

	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}

	@Override
	public AncienEtudiantDTO createEtudiant(String firstname, String lastname, Civilite civilite, String email,
			String numTelephone, String password, Date naissance,String statut, String posteActu, String villeActu,
			String nomEntreprise, String diplome, int anneeDiplome, GroupeDTO groupe) {
		// Création de l'étudiant
		AncienEtudiant e = new AncienEtudiant();
		e.setPrenom(firstname);
		e.setNom(lastname);
		e.setCivilite(civilite);
		e.setEmail(email);
		e.setValidation(false);
		e.setNumTelephone(numTelephone);
		e.setPassword(password);
		e.setNaissance(naissance);
		e.setStatut(statut);
		e.setPosteActu(posteActu);
		e.setVilleActu(villeActu);
		e.setNomEntreprise(nomEntreprise);

		e.setDiplome(diplome);
		e.setAnneeDiplome(anneeDiplome);

		Groupe p = getGroupeById(groupe.getId());

		e.setGroupe(p);
		p.getAncienEtudiants().add(e);

		// Création du profil de l'étudiant
		EtudiantProfil ep = new EtudiantProfil();

		// Mappage entre l'étudiant et son profil
		ep.setEtudiant(e);
		e.setProfil(ep);

		// Persistance de l'étudiant et du profil en BDD
		em.persist(ep);
		em.persist(e);
		em.merge(p);

		p = getGroupeById(groupe.getId());

		// Mapping EtudiantDTO et ProfilDTO pour retourner un etudiantDTO à la
		// couche présentation
		AncienEtudiantDTO eDTO = ce.MappingEtudiantProfilGroupe(e, ep, p);
		return eDTO;

	}

	@Override
	public AncienEtudiantDTO getEtudiant(String email) {
		AncienEtudiant e = getEtudiantByMail(email);

		if (e != null) {
			EtudiantProfil ep = e.getProfil();
			AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			return eDTO;
		}

		// a remplacer par le renvoie d'une exception lorsqu'aucun email ne
		// correspond à celui en parametre
		return null;
	}

	@Override
	public AncienEtudiantDTO getEtudiant(int id) {
		AncienEtudiant e = getEtudiantById(id);
		if (e != null) {
			EtudiantProfil ep = e.getProfil();
			AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			return eDTO;
		}
		// a remplacer par le renvoie d'une exception lorsqu'aucun id ne
		// correspond à celui en parametre
		return null;

	}

	@Override
	public List<AncienEtudiantDTO> listEtudiant() {
		List<AncienEtudiant> ps = em.createQuery("select o from AncienEtudiant o").getResultList();
		List<AncienEtudiantDTO> psDTO = new ArrayList<AncienEtudiantDTO>();

		for (AncienEtudiant e : ps) {
			EtudiantProfil ep = e.getProfil();
			AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			psDTO.add(eDTO);
		}
		return psDTO;
	}

	@Override
	public boolean connexion(String email, String password) {

		AncienEtudiant e = getEtudiantByMail(email);
		if (e != null && (e.getPassword().equals(password))) {
			System.out.println("connexion etablie");
			return true;
		}

		System.out.println("connexion refusee");
		return false;
	}

	@Override
	public void addCompetence(AncienEtudiantDTO eDTO, String competence, int niveau) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Competence c = new Competence();
		c.setLibelle(competence);
		c.setNiveau(niveau);
		EtudiantProfil ep = e.getProfil();
		ep.getMesCompetences().add(c);
		c.setProfil(ep);
		em.persist(c);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public List<CompetenceDTO> getCompetences(AncienEtudiantDTO eDTO) {

		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
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
	public void addExperience(AncienEtudiantDTO eDTO, String experience, String entreprise, String ville, String region,
			String pays, String debut, String fin, String description) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Experience exp = new Experience();
		exp.setLibelle(experience);
		EtudiantProfil ep = e.getProfil();
		ep.getMesExperiences().add(exp);
		exp.setProfil(ep);
		exp.setLibelle(experience);
		exp.setEntreprise(entreprise);
		exp.setVille(ville);
		exp.setPays(pays);
		exp.setRegion(region);
		exp.setDebut(debut);
		exp.setFin(fin);
		exp.setDescription(description);
		em.persist(exp);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public List<ExperienceDTO> getExperiences(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
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
	public GroupeDTO getGroupe(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		Groupe monGroupe = e.getGroupe();
		GroupeDTO monGroupeDTO = ce.MappingEtudiantGroupe(e, monGroupe);
		return monGroupeDTO;
	}

	@Override
	public void setGroupe(AncienEtudiantDTO eDTO, GroupeDTO gDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		Groupe grp = getGroupeById(gDTO.getId());

		e.setGroupe(grp);
		grp.getAncienEtudiants().add(e);

		em.merge(grp);
		em.merge(e);

	}

	@Override
	public void addHobbie(AncienEtudiantDTO eDTO, String hobbie) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
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
	public List<HobbieDTO> getHobbies(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Hobbie> mesHobbies = e.getProfil().getMesHobbies();
		List<HobbieDTO> mesHobbiesDTO = new ArrayList<HobbieDTO>();

		for (Hobbie h : mesHobbies) {
			HobbieDTO hobbieDTO = ce.MappingProfilHobbie(e.getProfil(), h);
			mesHobbiesDTO.add(hobbieDTO);
		}
		return mesHobbiesDTO;
	}

	///////////////////////////////// faire .....................
	@Override
	public void addEcole(AncienEtudiantDTO eDTO, String libelle, String etablissement, String debut, String fin, String ville,
			String region, String pays) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Ecole formation = new Ecole();
		formation.setLibelle(libelle);
		EtudiantProfil ep = e.getProfil();
		ep.getMesEcoles().add(formation);
		formation.setProfil(ep);
		formation.setLibelle(libelle);
		formation.setEtablissement(etablissement);
		formation.setDebut(debut);
		formation.setFin(fin);
		formation.setVille(ville);
		formation.setRegion(region);
		formation.setPays(pays);
		em.persist(formation);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public List<EcoleDTO> getEcoles(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
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
	public List<PublicationDTO> getPublications(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
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
	public void addPublication(AncienEtudiantDTO eDTO, String titre, String message, Date date) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
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

		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		AncienEtudiant e;
		try {
			e = (AncienEtudiant) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPasswordIsGood(String mail, String password) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.email = :email and o.password = :password ");
		q.setParameter("email", mail);
		q.setParameter("password", password);
		AncienEtudiant e;
		try {
			e = (AncienEtudiant) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public void deleteCompetenceList(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		List<Competence> mesCompetences = e.getProfil().getMesCompetences();
		for (Competence competence : mesCompetences) {
			em.remove(competence);
		}
		e.getProfil().setMesCompetences(new ArrayList<Competence>());
		em.persist(e);
		mesCompetences = e.getProfil().getMesCompetences();
	}

	@Override
	public void deleteExpProList(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		List<Experience> mesExperiences = e.getProfil().getMesExperiences();
		for (Experience experience : mesExperiences) {
			em.remove(experience);
		}
		e.getProfil().setMesExperiences(new ArrayList<Experience>());
		em.persist(e);
	}

	@Override
	public void deleteFormationList(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		List<Ecole> mesFormations = e.getProfil().getMesEcoles();
		for (Ecole ecole : mesFormations) {
			em.remove(ecole);
		}
		e.getProfil().setMesEcoles(new ArrayList<Ecole>());
		em.persist(e);
	}

	@Override
	public void deleteLoisirList(AncienEtudiantDTO eDTO) {
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		List<Hobbie> mesLoisirs = e.getProfil().getMesHobbies();
		for (Hobbie hobbie : mesLoisirs) {
			em.remove(hobbie);
		}
		e.getProfil().setMesHobbies(new ArrayList<Hobbie>());
		em.persist(e);
	}

	@Override
	public void updateEtudiant(int id, String statut, String posteActu, String villeActu, String nomEntreprise, String numTelephone,
			String facebook, String twitter, String viadeo, String linkedin, String attentes) {

		AncienEtudiant e = getEtudiantById(id);
		e.setStatut(statut);
		e.setPosteActu(posteActu);
		e.setVilleActu(villeActu);
		e.setNomEntreprise(nomEntreprise);
		e.setNumTelephone(numTelephone);
		e.setAttentes(attentes);
		if (valideLien(facebook, "facebook.com"))
			e.setFacebook(facebook);
		if (valideLien(twitter, "twitter.com"))
			e.setTwitter(twitter);
		if (valideLien(viadeo, "viadeo.com"))
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