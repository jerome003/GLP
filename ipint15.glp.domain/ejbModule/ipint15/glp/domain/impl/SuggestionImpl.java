package ipint15.glp.domain.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.SuggestionRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.util.Conversion;



@Stateless
public class SuggestionImpl implements SuggestionRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;


	private List<AncienEtudiantDTO> etuByGroupeInstitu(int idEtu) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", idEtu);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();
		
		Groupe g = e.getGroupe();
	
		List<AncienEtudiant> etus = g.getAncienEtudiants();
		List<AncienEtudiantDTO> etusDTO = new ArrayList<AncienEtudiantDTO>();
		List<AncienEtudiantDTO> result = new ArrayList<AncienEtudiantDTO>();

		for (AncienEtudiant ae : etus) {
			EtudiantProfil ep = e.getProfil();
			AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			etusDTO.add(eDTO);
		}
		for (int i = 0; i<3; i++) {
			int tirage;
			int size = etusDTO.size()-1;
			if (size>=0) {
				Random r = new Random();
				if (size != 0) {
					tirage = r.nextInt(size - 0) + 0;
				}else{
					tirage = 0;
				}
				result.add(etusDTO.get(tirage));
				etusDTO.remove(tirage);
			}
		}
		return result;
	}
	
	private List<AncienEtudiantDTO> randomEtu() {
		List<AncienEtudiant> ps = em.createQuery("select o from AncienEtudiant o").getResultList();
		List<AncienEtudiantDTO> psDTO = new ArrayList<AncienEtudiantDTO>();
		List<AncienEtudiantDTO> result = new ArrayList<AncienEtudiantDTO>();

		for (AncienEtudiant e : ps) {
			EtudiantProfil ep = e.getProfil();
			AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			psDTO.add(eDTO);
		}
		for (int i = 0; i<3; i++) {
			int tirage;
			int size = psDTO.size()-1;
			if (size>=0) {
				Random r = new Random();
				if (size != 0) {
					tirage = r.nextInt(size - 0) + 0;
				}else{
					tirage = 0;
				}
				result.add(psDTO.get(tirage));
				psDTO.remove(tirage);
			}
		}
		return result;
	}

	private List<GroupeDTO> randomGroupe() {
		List<Groupe> ps = em.createQuery("select o from Groupe o").getResultList();
		List<GroupeDTO> psDTO = new ArrayList<GroupeDTO>();
		List<GroupeDTO> result = new ArrayList<GroupeDTO>();

		for (Groupe g : ps) {
			psDTO.add(g.toGroupeDTO());
		}

		for (int i = 0; i<3; i++) {
			int tirage;
			int size = psDTO.size()-1;
			if (size>=0) {
				Random r = new Random();
				if (size != 0) {
					tirage = r.nextInt(size - 0) + 0;
				}else{
					tirage = 0;
				}
				result.add(psDTO.get(tirage));
				psDTO.remove(tirage);
			}
		}
		return result;
	}

	@Override
	public List<AncienEtudiantDTO> genereSuggestionEtu(int idEtu) {
		Random r = new Random();
		int tirage = r.nextInt(1 - 0) + 0;
		if (tirage == 0) {
			return randomEtu();
		}else {
			return etuByGroupeInstitu(idEtu);
		}
	}
	@Override
	public List<GroupeDTO> genereSuggestionGroupe(int idEtu) {
		return randomGroupe();
	}


}
