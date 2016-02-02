package ipint15.glp.domain.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class EtudiantCatalogImpl implements EtudiantCatalogRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	public EtudiantCatalogImpl() {
	}

	@Override
	public EtudiantDTO createEtudiant(String nom, String prenom, String mail) {
		Etudiant e = new Etudiant();
		e.setNom(nom);
		e.setPrenom(prenom);
		e.setMail(mail);

		em.persist(e);

		EtudiantDTO dto = e.toEtudiantDTO();

		return dto;
	}

	@Override
	public EtudiantDTO getEtudiantById(int id) {
		Etudiant e = em.find(Etudiant.class, id);
		EtudiantDTO dto = e.toEtudiantDTO();
		return dto;
	}

	@Override
	public EtudiantDTO getEtudiantByMail(String mail) {
		Query q = em.createQuery("select o from Etudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		Etudiant e = (Etudiant) q.getSingleResult();
		EtudiantDTO dto = e.toEtudiantDTO();
		return dto;
	}

}
