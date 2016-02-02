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
	EnseignantDTO createEtudiant (String nom, String prenom, String mail);
	
	EnseignantDTO getEtudiantById(int id);
	
	EnseignantDTO getEtudiantByMail(String mail);

}
