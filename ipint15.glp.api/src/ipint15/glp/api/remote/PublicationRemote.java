package ipint15.glp.api.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.PublicationDTO;

@Remote
public interface PublicationRemote {

	public List<PublicationDTO> getAllPublications(AncienEtudiantDTO eDTO, int idGroupe);

	public List<PublicationDTO> getAllGroupPublications(int idGroupe, int idUtilisateur, String typeCompte);

	public List<PublicationDTO> getMyPublications(AncienEtudiantDTO eDTO, int idGroupe);

	public void addPublication(AncienEtudiantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe);
	
	public List<PublicationDTO> getAllPublicationsEtudiant(EtudiantDTO eDTO, int idGroupe);
	
	public List<PublicationDTO> getMyPublicationsEtudiant(EtudiantDTO eDTO, int idGroupe);

	public void addPublicationEtudiant(EtudiantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe);
	
	public List<PublicationDTO> getAllPublicationsEnseignant(EnseignantDTO eDTO, int idGroupe);
	
	public List<PublicationDTO> getMyPublicationsEnseignant(EnseignantDTO eDTO, int idGroupe);

	public void addPublicationEnseignant(EnseignantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe);

}
