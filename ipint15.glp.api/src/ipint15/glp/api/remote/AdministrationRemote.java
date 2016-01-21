package ipint15.glp.api.remote;



import javax.ejb.Remote;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;

@Remote
public interface AdministrationRemote {
	
	public AdminDTO createAdmin(String email, String mdp);
	
	public String generatePassword();	
	
	public ModerateurDTO getModerateurDTOById(int id);

	public ModerateurDTO createModerateur(String prenom, String nom, String email, String password, GroupeDTO groupe);
	
	public boolean isMailExists(String mail);

	public boolean isPasswordIsGood(String mail, String password);
	
	public boolean connexion(String email, String password);
	
	public boolean isThereAnAdmin();

	

}
