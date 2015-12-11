package ipint15.glp.domain.impl;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.omg.Messaging.SyncScopeHelper;

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
			Calendar naissance) {
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
	public EtudiantDTO getEtudiant(String email){
		List<Etudiant> ps = em.createQuery("select o from Etudiant o").getResultList();
		for(Etudiant p : ps){
			if(p.getEmail().equals(email)){
				EtudiantDTO pDTO = new EtudiantDTO();
				pDTO.setId(p.getId());
				pDTO.setPrenom(p.getPrenom());
				pDTO.setNom(p.getNom());
				pDTO.setEmail(p.getEmail());
				pDTO.setPassword(p.getPassword());
				pDTO.setNaissance(p.getNaissance());
				return pDTO;
			}
		}
		// a remplacer par le renvoie d'une exception lorsqu'aucun email ne correspond à celui en parametre
		return null;
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