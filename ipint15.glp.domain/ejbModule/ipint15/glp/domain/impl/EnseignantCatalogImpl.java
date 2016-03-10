package ipint15.glp.domain.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EnseignantCatalogRemote;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class EnseignantCatalogImpl implements EnseignantCatalogRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	@Override
	public EnseignantDTO createEnseignant(String nom, String prenom, String mail) {
		Enseignant e = new Enseignant();
		e.setNom(nom);
		e.setMail(mail);
		e.setPrenom(prenom);
		em.persist(e);
		return e.toEnseignantDTO();
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

}
