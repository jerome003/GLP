package ipint15.glp.api.remote;

import javax.ejb.Remote;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;

@Remote
public interface EtudiantCatalogRemote {
	
	/**
	 * Creation d'un etudiant.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	EtudiantDTO createEtudiant (String nom, String prenom, String mail,GroupeDTO groupe);
	
	EtudiantDTO getEtudiantById(int id);
	
	EtudiantDTO getEtudiantByMail(String mail);

	boolean isMailExists(String mail);

}
