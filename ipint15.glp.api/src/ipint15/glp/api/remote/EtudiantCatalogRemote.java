package ipint15.glp.api.remote;

import java.util.Calendar;
import java.util.List;
import javax.ejb.Remote;

import ipint15.glp.api.dto.EtudiantDTO;


@Remote
public interface EtudiantCatalogRemote {
	public EtudiantDTO createEtudiant(String firstname, String
			lastname, String email, String password, Calendar naissance);
	
	public List<EtudiantDTO> listEtudiant();

	public boolean connexion(String email, String password);
}
