package ipint15.glp.api.remote;

import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;


@Remote
public interface GroupeRemote {

	// CRUD
	public GroupeDTO createGroupe(String name, String description/*, boolean isInstitutionnel*/);
	
	public GroupeDTO createGroupe(String name, String description, boolean isInstitutionnel);

	public void editGroupe(int id, String newName, String description);

	public boolean removeGroupe(int id);
	
	public boolean removeGroupeNonInstit(int id , int idMembre);

	public List<GroupeDTO> getAllGroupe();
	
	public List<GroupeDTO> getAllGroupeInstitutionnel();
	
	public List<GroupeDTO> getAllGroupeNonInstitutionnel();

	public List<GroupeDTO> getAllMesGroupesNonInstitutionnel(AncienEtudiantDTO ancien);
	
	public GroupeDTO getGroupeDTOById(int id);

	public int getGroupeSize(int id);

	public void editGroupe(int id, String description);

	List<GroupeDTO> getGroupesOfAncienByIdAncien(int id);

	public GroupeDTO getGroupeDTOByIdWithMemberList(int id);
	
	public boolean membreExistInListGroupe(int idGroupe, int idMembre);
	
	public boolean peutRejoindreGroupe(int idGroupe, int idMembre);
	
	public boolean peutQuitterGroupe(int idGroupe, int idMembre);
	
	/**
	 * Permet l'ajout d'un animateur à un groupe non institutionnel
	 * @param ansEtu
	 * @param groupe
	 */
//	public void addGroupeAnimateur(AncienEtudiant ansEtu, GroupeDTO groupe);

	public int getAnimByGroupeSize(int id);

}
