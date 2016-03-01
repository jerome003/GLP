package ipint15.glp.api.remote;

import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;

@Remote
public interface GroupeRemote {

	// CRUD
	public GroupeDTO createGroupe(String name, String description);

	public void editGroupe(int id, String newName, String description);

	public boolean removeGroupe(int id);

	public List<GroupeDTO> getAllGroupe();

	public GroupeDTO getGroupeDTOById(int id);

	public int getGroupeSize(int id);

	public void editGroupe(int id, String description);

	List<GroupeDTO> getGroupesOfAncienByIdAncien(int id);

	public GroupeDTO getGroupeDTOByIdWithMemberList(int id);

}
