package ipint15.glp.api.remote;



import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;

@Remote
public interface AdministrationRemote {
	
	public AdminDTO createAdmin(String email, String mdp);
	
	public String generatePassword(int i);	
	
	public List<ModerateurDTO> getAllModerateur();
	
	public ModerateurDTO getModerateurDTOById(int id);
	
	public boolean isMailExists(String mail);

	public boolean isPasswordIsGood(String mail, String password);
	
	public boolean connexion(String email, String password);
	
	public boolean isThereAnAdmin();

	public ModerateurDTO createModerateur(String prenom, String nom, String email, String password);
	
	public ModerateurDTO addGroupetoModo(int id, GroupeDTO groupe);


}
