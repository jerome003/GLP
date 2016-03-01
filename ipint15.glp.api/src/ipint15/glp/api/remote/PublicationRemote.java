package ipint15.glp.api.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.PublicationDTO;

@Remote
public interface PublicationRemote {

	List<PublicationDTO> getAllPublications(AncienEtudiantDTO eDTO, int idGroupe);

	List<PublicationDTO> getAllGroupPublications(int idGroupe);

	List<PublicationDTO> getMyPublications(AncienEtudiantDTO eDTO, int idGroupe);

	void addPublication(AncienEtudiantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe);

}
