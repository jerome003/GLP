package ipint15.glp.api.remote;


import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.EtudiantDTO;


@Remote
public interface EtudiantCatalogRemote {
	public EtudiantDTO createEtudiant(String firstname, String
			lastname, Civilite civilite, String email, String password, Date naissance);
	
	public List<EtudiantDTO> listEtudiant();

	public boolean connexion(String email, String password);
	
	public EtudiantDTO getEtudiant(String email);
}
