package ipint15.glp.api.remote;

import java.util.List;

import javax.ejb.Remote;


import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.GroupeDTO;

@Remote
public interface EnseignantCatalogRemote {
	
	/**
	 * Creation d'un enseignant.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	EnseignantDTO createEnseignant (String nom, String prenom, String mail);
	
	EnseignantDTO getEnseignantById(int id);
	
	EnseignantDTO getEnseignantByMail(String mail);
	
	void addGroupeToEnseignant (int idEnseignant, int idGroupe);
	
	void addGroupeAnimeToEnseignant (int idEnseignant, int idGroupe);
	
	public List<GroupeDTO> getLesGroupes(EnseignantDTO eDTO);

}
