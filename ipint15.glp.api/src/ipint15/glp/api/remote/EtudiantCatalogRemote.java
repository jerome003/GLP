package ipint15.glp.api.remote;

import javax.ejb.Remote;

import ipint15.glp.api.dto.EtudiantDTO;

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
	EtudiantDTO createEtudiant (String nom, String prenom, String mail);
	
	EtudiantDTO getEtudiantById(int id);
	
	EtudiantDTO getEtudiantByMail(String mail);

}
