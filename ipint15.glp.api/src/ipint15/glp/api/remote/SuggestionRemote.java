package ipint15.glp.api.remote;

import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;


@Remote
public interface SuggestionRemote {
	
	public List<AncienEtudiantDTO> genereSuggestionEtu();

	public List<GroupeDTO> genereSuggestionGroupe();

}
