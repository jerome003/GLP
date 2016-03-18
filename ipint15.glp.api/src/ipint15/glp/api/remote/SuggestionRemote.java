package ipint15.glp.api.remote;

import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;


@Remote
public interface SuggestionRemote {
	
	public List<AncienEtudiantDTO> genereSuggestionEtu(int idEtu,boolean ancien);

	public List<GroupeDTO> genereSuggestionGroupe(int idEtu,boolean ancien);

}
