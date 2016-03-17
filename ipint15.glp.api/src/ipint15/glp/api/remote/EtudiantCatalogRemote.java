package ipint15.glp.api.remote;

import java.util.List;

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
	
	public List<GroupeDTO> getLesGroupes(EtudiantDTO eDTO);

	boolean isMailExists(String mail);

}
