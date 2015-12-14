package ipint15.glp.api.remote;

import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.EtudiantDTO;

@Remote
public interface RechercheRemote {
	
	public List<EtudiantDTO> rechercherEtudiant(String recherche);

}
