package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;

import ipint15.glp.api.dto.GroupeDTO;

import ipint15.glp.api.remote.EnseignantCatalogRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class EnseignantCatalogImpl implements EnseignantCatalogRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	
	private Enseignant getEnseignantByMail2(String mail) {

		Query q = em.createQuery("select o from Enseignant o WHERE o.mail = :mail");
		q.setParameter("mail", mail);
	 	Enseignant e = (Enseignant) q.getSingleResult();

		return e;
	}
	
	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}
	
	@Override
	public EnseignantDTO createEnseignant(String nom, String prenom, String mail) {
		Enseignant e = new Enseignant();
		e.setNom(nom);
		e.setMail(mail);
		e.setPrenom(prenom);
		em.persist(e);
		EnseignantDTO dto = e.toEnseignantDTO();
		return dto;
	}

	@Override
	public EnseignantDTO getEnseignantById(int id) {
 		Enseignant e = em.find(Enseignant.class, id);
		EnseignantDTO dto = e.toEnseignantDTO();
 	for (Groupe g : e.getGroupesAnimes()) {
 		dto.addGroupeDTOAnime(g.toGroupeDTO());
 	}
 	return dto;
	}

	@Override
	public EnseignantDTO getEnseignantByMail(String mail) {
		try {
			Query q = em.createQuery("select o from Enseignant o WHERE o.mail = :mail");
			q.setParameter("mail", mail);
			Enseignant e = (Enseignant) q.getSingleResult();
			EnseignantDTO dto = e.toEnseignantDTO();
			return dto;
		} catch (NoResultException e){
			return null;
		}
	}

	@Override
	public void addGroupeToEnseignant(int idEnseignant, int idGroupe) {
		Enseignant e = em.find(Enseignant.class, idEnseignant);
		Groupe groupe = em.find(Groupe.class, idGroupe);
		e.getGroupes().add(groupe);
		em.persist(e);
	}

	@Override
	public void addGroupeAnimeToEnseignant(int idEnseignant, int idGroupe) {
		Enseignant e = em.find(Enseignant.class, idEnseignant);
		Groupe groupe = em.find(Groupe.class, idGroupe);
		e.getGroupesAnimes().add(groupe);
		em.persist(e);
	}

	@Override
	public List<GroupeDTO> getLesGroupes(EnseignantDTO eDTO) {
		System.out.println(eDTO == null);
		Enseignant e = getEnseignantByMail2(eDTO.getMail());
		// TODO gérer le cas si e = null
		List<Groupe> mesGroupes = e.getGroupes();
		
		List<GroupeDTO> mesGroupesDTO = new ArrayList<GroupeDTO>();
		mesGroupesDTO = ce.MappingEnseignantLesGroupes(e, mesGroupes);
		
		return mesGroupesDTO;
	}

	@Override
	public void setLesGroupe(EnseignantDTO eDTO, List<GroupeDTO> lesGroupe) {
		Enseignant ens = getEnseignantByMail2(eDTO.getMail());
				//getEtudiantByMail(eDTO.getEmail());
		List<Groupe> lesGroupesPrim = new ArrayList<>();
		for(GroupeDTO gd : lesGroupe){
			lesGroupesPrim.add(getGroupeById(gd.getId()));
		}
		ens.setGroupes(lesGroupesPrim);
		for(Groupe grp : lesGroupesPrim){
		grp.getEnseignant().add(ens);
		em.merge(grp);	
		}
		em.merge(ens); 
		
	}

	@Override
	public void removeGroupeInLesGroupes(EnseignantDTO eDTO, GroupeDTO gDTO) {
		Enseignant ens = getEnseignantByMail2(eDTO.getMail());
		Groupe grp = getGroupeById(gDTO.getId());
		List<Groupe> lesGroupes = ens.getGroupes();
		lesGroupes.remove(grp); 
		grp.getEnseignant().remove(ens);
		ens.setGroupes(lesGroupes);
		em.persist(grp);
		em.persist(ens);
		
	}

	@Override
	public void addGroupeInLesGroupesNonInstitProf(EnseignantDTO eDTO, GroupeDTO gDTO) {
		Enseignant ens = getEnseignantByMail2(eDTO.getMail());
		Groupe grp = getGroupeById(gDTO.getId());
		List<Groupe> lesGroupes = ens.getGroupes();
		lesGroupes.add(grp); 
		ens.setGroupes(lesGroupes);
		grp.getEnseignant().add(ens);			
		em.persist(grp);
		em.persist(ens);
		
	}

	@Override
	public void addAnimateurToGroupeNonInstitProf(EnseignantDTO eDTO, GroupeDTO gDTO) {
		Enseignant ens = getEnseignantByMail2(eDTO.getMail());
		Groupe g = getGroupeById(gDTO.getId());
		g.setAnimateurEnsGNonInstit(ens);
		em.persist(g);
		
	}

}
