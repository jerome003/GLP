package ipint15.glp.domain.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.omg.Messaging.SyncScopeHelper;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.util.ConversionEtudiant;

@Stateless
public class EtudiantCatalogImpl implements EtudiantCatalogRemote {
	
	ConversionEtudiant ce = new ConversionEtudiant();
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
		return ce.toEtudiantDTO(p);
	} 

	@Override
	public List<EtudiantDTO> listEtudiant() {
		List<Etudiant> ps = em.createQuery("select o from Etudiant o").getResultList();
		List<EtudiantDTO> psDTO = new ArrayList<EtudiantDTO>();
		for (Etudiant p : ps) {
			psDTO.add(ce.toEtudiantDTO(p));
		}
		return psDTO;
	}
	
	
	@Override
	public boolean connexion(String email, String password){
		List<Etudiant> ps = em.createQuery("select o from Etudiant o").getResultList();
		System.out.println(email);
		System.out.println(password);
		for(Etudiant p: ps){
			if(p.getEmail().equals(email)&&(p.getPassword().equals(password))){
				System.out.println("connexion etablie");
				return true;
			}
		}
		System.out.println("connexion refusee");
		return false;
	}
	
	public String toString() {
		return "Success MB !";
	}



}