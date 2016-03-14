package ipint15.glp.domain.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.SuggestionRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.util.Conversion;



@Stateless
public class SuggestionImpl implements SuggestionRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;
	
	
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
			Random r = new Random();
			int tirage = r.nextInt(psDTO.size() - 0) + 0;
			result.add(psDTO.get(tirage));
		}
		return result;
	}
	
	@Override
	public List<AncienEtudiantDTO> genereSuggestionEtu(int idEtu) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<GroupeDTO> genereSuggestionGroupe(int idEtu) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
