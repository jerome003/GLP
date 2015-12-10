package ipint15.glp.domain.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.domain.entities.Etudiant;

@Stateless
public class EtudiantCatalogImpl implements EtudiantCatalogRemote {
	
	static int id;
	@PersistenceContext
	EntityManager em;
	
	public EtudiantCatalogImpl() {
		
	}

	

	@Override
	public EtudiantDTO createEtudiant(String firstname, String lastname, String email, String password,
			Date naissance) {
		Etudiant p = new Etudiant();
		p.setId(++id);
		p.setPrenom(firstname);
		p.setNom(lastname);
		p.setEmail(email);
		p.setPassword("password");
		p.setNaissance(naissance);
		em.persist(p);
		EtudiantDTO pDTO = new EtudiantDTO();
		pDTO.setId(id);
		pDTO.setPrenom(firstname);
		pDTO.setNom(lastname);
		pDTO.setEmail(email);
		pDTO.setPassword("password");
		pDTO.setNaissance(naissance);
		return pDTO;
	} 

	@Override
	public List<EtudiantDTO> listEtudiant() {
		List<Etudiant> ps = em.createQuery("select o from Etudiant o").getResultList();
		List<EtudiantDTO> psDTO = new ArrayList<EtudiantDTO>();
		for (Etudiant p : ps) {
			EtudiantDTO pDTO = new EtudiantDTO();
			pDTO.setId(p.getId());
			pDTO.setPrenom(p.getPrenom());
			pDTO.setNom(p.getNom());
			pDTO.setEmail(p.getEmail());
			pDTO.setPassword(p.getPassword());
			pDTO.setNaissance(p.getNaissance());
			psDTO.add(pDTO);
		}
		return psDTO;
	}
	
	public String toString() {
		return "Success MB !";
	}



}