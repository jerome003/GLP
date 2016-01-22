package ipint15.glp.api.remote;



import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;

@Remote
public interface AdministrationRemote {
	
	public AdminDTO createAdmin(String email, String mdp);
	
	public String generatePassword(int i);	
	
	public List<ModerateurDTO> getAllModerateur();
	
	public ModerateurDTO getModerateurDTOById(int id);
	
	public boolean isMailExistsForAdmin(String mail);

	public boolean isPasswordIsGoodForAdmin(String mail, String password);
	
	public boolean isMailExistsForModerateur(String mail);

	public boolean isPasswordIsGoodForModerateur(String mail, String password);
	
	public boolean connexionAdmin(String email, String password);
	
	public boolean isThereAnAdmin();

	public ModerateurDTO createModerateur(String prenom, String nom, String email, String password);
	
	public ModerateurDTO addGroupetoModo(int id, GroupeDTO groupe);
	
	public AdminDTO getAdmin(String email);
	
	public ModerateurDTO getModerateur(String email);

	public void sendMailModoAssign(ModerateurDTO modo, GroupeDTO groupe);
	
	public void sendMailEtudiantOK(EtudiantDTO etu);
	
	public void sendMailEtudiantKO(EtudiantDTO etu);

	boolean connexionModerateur(String email, String password);


}
