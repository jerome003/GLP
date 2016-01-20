package ipint15.glp.api.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;

@Remote
public interface AdministrationRemote {
	
	public AdminDTO createAdmin(String email, String mdp);
	
	public String generatePassword();	
	
	public ModerateurDTO getModerateurDTOById(int id);

	ModerateurDTO createModerateur(String prenom, String nom, String email, String password, GroupeDTO groupe);

}
