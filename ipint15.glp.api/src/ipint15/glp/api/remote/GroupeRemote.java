package ipint15.glp.api.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;

@Remote
public interface GroupeRemote {
	
	//CRUD
	public GroupeDTO createGroupe(String name);
	
	public void editGroupe(int id, String newName);
	
	public void removeGroupe(int id);

	public List<GroupeDTO> getAllGroupe();

	public GroupeDTO getGroupeDTOById(int id);

	int getGroupeSize(int id);

	

}
