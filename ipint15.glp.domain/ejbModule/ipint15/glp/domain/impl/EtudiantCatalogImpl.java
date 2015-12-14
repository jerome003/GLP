package ipint15.glp.domain.impl;



import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.domain.entities.Competence;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Publication;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class EtudiantCatalogImpl implements EtudiantCatalogRemote {
	
	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;
	
	public EtudiantCatalogImpl() {
		
	}

	@Override
	public EtudiantDTO createEtudiant(String firstname, String lastname, Civilite civilite, String email, String password,
			Date naissance) {
		
		// Création de l'étudiant
		Etudiant e = new Etudiant();
		e.setPrenom(firstname);
		e.setNom(lastname);
		e.setCivilite(civilite);
		e.setEmail(email);
		e.setPassword("password");
		e.setNaissance(naissance);
		
		//Création du profil de l'étudiant 
		EtudiantProfil ep = new EtudiantProfil();
		
		//Mappage entre l'étudiant et son profil
		ep.setEtudiant(e);
		e.setProfil(ep);
		
		// Persistance de l'étudiant et du profil en BDD
		em.persist(ep);
		em.persist(e);
		
		// Mapping EtudiantDTO et ProfilDTO pour retourner un etudiantDTO à la couche présentation
		EtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
		return eDTO;
		
	} 

	@Override
	public EtudiantDTO getEtudiant(String email){
		Etudiant e = getEtudiantByMail(email);
		
			if(e!=null){
				EtudiantProfil ep = e.getProfil();
				EtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
				return eDTO;
			}

		// a remplacer par le renvoie d'une exception lorsqu'aucun email ne correspond à celui en parametre
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
	public boolean connexion(String email, String password){
		Etudiant e = getEtudiantByMail(email);
			if(e!=null&&(e.getPassword().equals(password))){
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
		
		
		for (Competence c : mesCompetences){
			CompetenceDTO cDTO = ce.MappingProfilCompetence(e.getProfil(), c);
			mesCompetencesDTO.add(cDTO);
		}
		
		return mesCompetencesDTO;
		
	}
	
	private Etudiant getEtudiantByMail(String mail){
		Query q = em.createQuery("select o from Etudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		Etudiant e = (Etudiant)q.getSingleResult();
		return e;
	}
	
	@Override
	public List<PublicationDTO> getPublications(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Publication> mesPublications = e.getProfil().getMesPublications();
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication c : mesPublications){
			PublicationDTO cDTO = ce.MappingProfilPublication(e.getProfil(), c);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;
		
	}
	
	@Override
	public void addPublication(EtudiantDTO eDTO, String titre, String message) {
		Etudiant e = getEtudiantByMail(eDTO.getEmail());
		// TODO gérer cas si e = null
		Publication c = new Publication();
		c.setTitre(titre);
		c.setMessage(message);
		EtudiantProfil ep = e.getProfil();
		ep.getMesPublications().add(c);
		c.setProfil(ep);
		em.persist(c);
		em.merge(ep);
		em.merge(e);
	}
	
}