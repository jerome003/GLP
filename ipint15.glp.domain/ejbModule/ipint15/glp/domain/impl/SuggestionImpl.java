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
			if (eDTO.getId() != e.getId()) {
				etusDTO.add(eDTO);
			}
		}
		while(result.size()!= 3) {
			int tirage;
			int size = etusDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
				result.add(etusDTO.get(tirage));
				etusDTO.remove(tirage);
			}else {
				List<AncienEtudiantDTO> random = randomEtu(idEtu);
				Random r = new Random();
				tirage = r.nextInt(random.size());
				if (!result.contains(random.get(tirage))) {
					result.add(random.get(tirage));
				}
			}
		}
		
		return result;
	}

	private List<AncienEtudiantDTO> etuByEntreprise(int idEtu) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", idEtu);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();

		String entreprise = e.getNomEntreprise().toLowerCase();
		
		List<AncienEtudiant> ps = em.createQuery("select o from AncienEtudiant o").getResultList();
		List<AncienEtudiantDTO> psDTO = new ArrayList<AncienEtudiantDTO>();
		List<AncienEtudiantDTO> result = new ArrayList<AncienEtudiantDTO>();

		for (AncienEtudiant ae : ps) {
			if (ae.getNomEntreprise().toLowerCase().equals(entreprise)) {
				EtudiantProfil ep = ae.getProfil();
				AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(ae, ep);
				if (eDTO.getId() != e.getId()) {
					psDTO.add(eDTO);
				}
			}
			
		}
		while(result.size()!= 3) {
			int tirage;
			int size = psDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
				result.add(psDTO.get(tirage));
				psDTO.remove(tirage);
			}else {
				List<AncienEtudiantDTO> random = randomEtu(idEtu);
				Random r = new Random();
				tirage = r.nextInt(random.size());
				if (!result.contains(random.get(tirage))) {
					result.add(random.get(tirage));
				}
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
			return randomEtu(idEtu);
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
				if (eDTO.getId() != e.getId()) {
					etusDTO.add(eDTO);
				}
			}
			while(result.size()!= 3) {
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

	private List<AncienEtudiantDTO> randomEtu(int idEtu) {
		
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", idEtu);
		AncienEtudiant etu = (AncienEtudiant) q.getSingleResult();
		
		List<AncienEtudiant> ps = em.createQuery("select o from AncienEtudiant o").getResultList();
		List<AncienEtudiantDTO> psDTO = new ArrayList<AncienEtudiantDTO>();
		List<AncienEtudiantDTO> result = new ArrayList<AncienEtudiantDTO>();

		for (AncienEtudiant e : ps) {
			EtudiantProfil ep = e.getProfil();
			AncienEtudiantDTO eDTO = ce.MappingEtudiantProfil(e, ep);
			if (eDTO.getId() != etu.getId()) {
				psDTO.add(eDTO);
			}
		}
		while(result.size()!= 3) {
			int tirage;
			int size = psDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
				result.add(psDTO.get(tirage));
				psDTO.remove(tirage);
			}else {
				break;
			}
		}
		return result;
	}
	
	private List<GroupeDTO> groupeByGroupeInstitu(int idEtu) {
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", idEtu);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();

		Groupe g = e.getGroupe();

		List<AncienEtudiant> etus = g.getAncienEtudiants();
		List<Groupe> groupes = new ArrayList<Groupe>();
		List<GroupeDTO> groupesDTO = new ArrayList<GroupeDTO>();
		List<GroupeDTO> result = new ArrayList<GroupeDTO>();
		

		for (AncienEtudiant etu : etus) {
			for (Groupe groupe : etu.getLesGroupes())
			if (!groupes.contains(groupe)) {
				groupes.add(groupe);
			}
		}
		
		for (Groupe groupe : groupes) {
			groupesDTO.add(groupe.toGroupeDTO());
		}
		
		while(result.size()!= 3) {
			int tirage;
			int size = groupesDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
				result.add(groupesDTO.get(tirage));
				groupesDTO.remove(tirage);
			}else {
				List<GroupeDTO> random = randomGroupe(idEtu);
				Random r = new Random();
				tirage = r.nextInt(random.size());
				if (!result.contains(random.get(tirage))) {
					result.add(random.get(tirage));
				}
			}
		}
		
		return result;
	}

	private List<GroupeDTO> randomGroupe(int idEtu) {
		
		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.id = :id");
		q.setParameter("id", idEtu);
		AncienEtudiant etu = (AncienEtudiant) q.getSingleResult();
		Groupe groupeEtu = etu.getGroupe();
		List<Groupe> groupesEtu = etu.getLesGroupes();
		
		
		List<Groupe> ps = em.createQuery("select o from Groupe o").getResultList();
		List<GroupeDTO> psDTO = new ArrayList<GroupeDTO>();
		List<GroupeDTO> result = new ArrayList<GroupeDTO>();

		for (Groupe g : ps) {
			if (g.getId() != groupeEtu.getId() && !groupesEtu.contains(g)) {
				psDTO.add(g.toGroupeDTO());
			}
		}

		while(result.size()!= 3) {
			int tirage;
			int size = psDTO.size();
			if (size>0) {
				Random r = new Random();
				tirage = r.nextInt(size);
				result.add(psDTO.get(tirage));
				psDTO.remove(tirage);
			}else {
				break;
			}
		}
		return result;
	}

	@Override
	public List<AncienEtudiantDTO> genereSuggestionEtu(int idEtu) {
		Random r = new Random();
		int tirage = r.nextInt(4);
		if (tirage == 0) {
			return randomEtu(idEtu);
		}else if (tirage == 1){
			return etuByGroupeInstitu(idEtu);
		}else if (tirage == 2){
			return etuByGroupeNonInstitu(idEtu);
		}else {
			return etuByEntreprise(idEtu);
		}
	}
	@Override
	public List<GroupeDTO> genereSuggestionGroupe(int idEtu) {
		Random r = new Random();
		int tirage = r.nextInt(2);
		tirage = 0;
		if (tirage == 0) {
			return groupeByGroupeInstitu(idEtu);
		}else {
			return randomGroupe(idEtu);
		}
	}


}
