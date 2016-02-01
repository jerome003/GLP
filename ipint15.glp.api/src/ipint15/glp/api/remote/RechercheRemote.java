package ipint15.glp.api.remote;

import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;

@Remote
public interface RechercheRemote {
	
	public List<AncienEtudiantDTO> rechercherEtudiant(String recherche);

	public List<GroupeDTO> rechercherGroupe(String recherche);

}
