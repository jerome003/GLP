package ipint15.glp.api.remote;

import javax.ejb.Remote;

import ipint15.glp.api.dto.EnseignantDTO;

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

}
