package ipint15.glp.domain.impl;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.SuggestionRemote;
import ipint15.glp.domain.util.Conversion;



@Stateless
public class SuggestionImpl implements SuggestionRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;
	
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
