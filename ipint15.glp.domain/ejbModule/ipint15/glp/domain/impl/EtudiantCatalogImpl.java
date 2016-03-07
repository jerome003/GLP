package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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

		Query q = em.createQuery("select o from Etudiant o WHERE o.mail = :mail");
		q.setParameter("mail", mail);
	 	Etudiant e = (Etudiant) q.getSingleResult();

		return e;
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

	@Override
	public List<GroupeDTO> getLesGroupes(EtudiantDTO eDTO) {
		Etudiant e = getEtudiantByMail2(eDTO.getMail());
		// TODO gérer le cas si e = null
		List<Groupe> mesGroupes = e.getGroupes();
		
		List<GroupeDTO> mesGroupesDTO = new ArrayList<>();
		mesGroupesDTO = ce.MappingEtuLesGroupes(e, mesGroupes);
		
		return mesGroupesDTO;
	}

}
