package ipint15.glp.domain.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EnseignantCatalogRemote;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class EnseignantCatalogImpl implements EnseignantCatalogRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	@Override
	public EnseignantDTO createEtudiant(String nom, String prenom, String mail) {
		Enseignant e = new Enseignant();
		e.setNom(nom);
		e.setMail(mail);
		e.setPrenom(prenom);
		em.persist(e);
		return e.toDTO();
	}

	@Override
	public EnseignantDTO getEtudiantById(int id) {
		Enseignant e = em.find(Enseignant.class, id);
		return e.toDTO();
	}

	@Override
	public EnseignantDTO getEtudiantByMail(String mail) {
		Query q = em.createQuery("select o from Enseignant o WHERE o.email = :email");
		q.setParameter("email", mail);
		Enseignant e = (Enseignant) q.getSingleResult();
		EnseignantDTO dto = e.toDTO();
		return dto;
	}

}
