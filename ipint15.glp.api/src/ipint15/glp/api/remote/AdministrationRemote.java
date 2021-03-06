package ipint15.glp.api.remote;



import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
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
	
	public void sendMailEtudiantOK(AncienEtudiantDTO etu);
	
	public void sendMailEtudiantKO(AncienEtudiantDTO etu, String motif);
	
	public void sendMailNewEtudiant(AncienEtudiantDTO etu);

	boolean connexionModerateur(String email, String password);

	public List<AncienEtudiantDTO> getEtudiantsNonInscritByIdGroupe(int id);

	void validationInscription(AncienEtudiantDTO etudiantDTO);

	void refusInscription(AncienEtudiantDTO etudiantDTO, int idGroupe, String motif);

	public boolean removeModerateur(int id);
	
	public List<ModerateurDTO> getModerateursDuGroupe (int id);
	
	public boolean isModerateurOfGroupe (int idModo, int idGroupe);

	public boolean removeModerateurFromGroupe(int idModo, int idGroupe);
	
	public void sendMailModoUnassign(ModerateurDTO modo, GroupeDTO groupe);

	public List<EnseignantDTO> getAnimateursDuGroupe(int id);

	public boolean isAnimateurOfGroupe(int anim, int id);

	public EnseignantDTO addGroupetoAnim(int anim, GroupeDTO groupe);

	public EnseignantDTO getEnseignantDTOById(int id);

	public boolean removeAnimateurFromGroupe(int idAnim, int idGroupe);

	public List<EnseignantDTO> getAllAnimateur();

	public boolean addGroupetoEnseign(int enseign, GroupeDTO groupe);

}
