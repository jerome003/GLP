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


	private List<Groupe> plusDe3(List<Groupe> groupes) {
		List<Groupe> result = new ArrayList<Groupe>();

		for (Groupe g : groupes) {
			if (g.getAncienEtudiants().size() >=3) {
				result.add(g);
			}
		}
		return result;
	}

	private List<AncienEtudiantDTO> etuByGroupeInstitu(int idEtu) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", idEtu);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();

		Groupe g = e.getGroupe();

		List<AncienEtudiant> etus = g.getAncienEtudiants();
		List<AncienEtudiantDTO> etusDTO = new ArrayList<AncienEtudiantDTO>();
		List<AncienEtudiantDTO> result = new ArrayList<AncienEtudiantDTO>();

		for (AncienEtudiant ae : etus) {
			EtudiantProfil ep = ae.getProfil();
			AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(ae, ep);
			etusDTO.add(eDTO);
		}
		for (int i = 0; i<3; i++) {
			int tirage;
			int size = etusDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
				result.add(etusDTO.get(tirage));
				etusDTO.remove(tirage);
			}
		}
		return result;
	}

	private List<AncienEtudiantDTO> etuByGroupeNonInstitu(int idEtu) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", idEtu);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();

		List<Groupe> groupes = plusDe3(e.getLesGroupes());
		Groupe g;

		if (groupes.isEmpty()) {
			return randomEtu();
		}else {
			if (groupes.size() == 1) {
				g = groupes.get(0);
			}else {
				Random r = new Random();
				int tirage = r.nextInt(groupes.size());
				g = groupes.get(tirage);
			}

			List<AncienEtudiant> etus = g.getAncienEtudiants();
			List<AncienEtudiantDTO> etusDTO = new ArrayList<AncienEtudiantDTO>();
			List<AncienEtudiantDTO> result = new ArrayList<AncienEtudiantDTO>();

			for (AncienEtudiant ae : etus) {
				EtudiantProfil ep = ae.getProfil();
				AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(ae, ep);
				etusDTO.add(eDTO);
			}
			for (int i = 0; i<3; i++) {
				int tirage;
				int size = etusDTO.size();
				if (size>0) {
					Random r = new Random();
					tirage = r.nextInt(size);
					result.add(etusDTO.get(tirage));
					etusDTO.remove(tirage);
				}
			}
			return result;
		}
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
			int size = psDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
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
			int size = psDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
				result.add(psDTO.get(tirage));
				psDTO.remove(tirage);
			}
		}
		return result;
	}

	@Override
	public List<AncienEtudiantDTO> genereSuggestionEtu(int idEtu) {
		Random r = new Random();
		int tirage = r.nextInt(2);
		tirage = 2;
		if (tirage == 0) {
			return randomEtu();
		}else if (tirage == 1){
			return etuByGroupeInstitu(idEtu);
		}else {
			return etuByGroupeNonInstitu(idEtu);
		}
	}
	@Override
	public List<GroupeDTO> genereSuggestionGroupe(int idEtu) {
		return randomGroupe();
	}


}
