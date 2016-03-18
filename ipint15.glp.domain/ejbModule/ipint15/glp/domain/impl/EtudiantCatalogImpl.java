package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class EtudiantCatalogImpl implements EtudiantCatalogRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	public EtudiantCatalogImpl() {
	}
	
	private Etudiant getEtudiantByMail2(String mail) {
		try {
			Query q = em.createQuery("select o from Etudiant o WHERE o.mail = :mail");
			q.setParameter("mail", mail);
			Etudiant e = (Etudiant) q.getSingleResult();
			return e;
		}
		catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public EtudiantDTO createEtudiant(String nom, String prenom, String mail,GroupeDTO groupe) {
		Etudiant e = new Etudiant();
		e.setNom(nom);
		e.setPrenom(prenom);
		e.setMail(mail);
		
		Groupe g = getGroupeById(groupe.getId());
		e.setGroupe(g);
		g.getEtudiants().add(e);
		em.persist(e);
		em.merge(g);
		EtudiantDTO dto = e.toEtudiantDTO();
		return dto;
	}

	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}
	
	@Override
	public EtudiantDTO getEtudiantById(int id) {
		Etudiant e = em.find(Etudiant.class, id);
		EtudiantDTO dto = e.toEtudiantDTO();
		return dto;
	}

	public EtudiantDTO getEtudiantByMail(String mail) {
		try {
			Query q = em.createQuery("select o from Etudiant o WHERE o.mail = :mail");
			q.setParameter("mail", mail);
			Etudiant e = (Etudiant) q.getSingleResult();
			EtudiantDTO dto = e.toEtudiantDTO();
			return dto;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<GroupeDTO> getLesGroupes(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail2(eDTO.getMail());

		List<Groupe> mesGroupes = e.getGroupes();
		
		List<GroupeDTO> mesGroupesDTO = new ArrayList<GroupeDTO>();
		mesGroupesDTO = ce.MappingEtuLesGroupes(e, mesGroupes);
		
		return mesGroupesDTO;
		
	}

	public boolean isMailExists(String mail) {

		Query q = em.createQuery("select o from Etudiant o WHERE o.mail = :mail");
		q.setParameter("mail", mail);
		Etudiant e;
		try {
			e = (Etudiant) q.getSingleResult();
		} catch (NoResultException e1) {
			return false;
		}
		return true;
	}

	@Override
	public void addGroupeInLesGroupesNonInstitEtudiant(EtudiantDTO eDTO, GroupeDTO groupeDTO) {
		Etudiant e = em.find(Etudiant.class, eDTO.getId());
		Groupe grp = em.find(Groupe.class, groupeDTO.getId());
		e.getGroupes().add(grp);
		grp.getEtudiants().add(e);
		em.persist(e);
		em.persist(grp);
	}

	@Override
	public void removeGroupeInLesGroupes(EtudiantDTO eDTO, GroupeDTO groupeDTO) {
		Etudiant e = em.find(Etudiant.class, eDTO.getId());
		Groupe grp = em.find(Groupe.class, groupeDTO.getId());
		e.getGroupes().remove(grp);
		grp.getEtudiants().remove(e);
		em.persist(e);
		em.persist(grp);
	}
}
