package ipint15.glp.api.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.PublicationDTO;

@Remote
public interface PublicationRemote {
	
	List<PublicationDTO> getPublications();
	
	List<PublicationDTO> getPublications(AncienEtudiantDTO eDTO);

	void addPublication(AncienEtudiantDTO eDTO, String titre, String message, Date date);

}
