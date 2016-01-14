package ipint15.glp.api.remote;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.HobbieDTO;

@Remote
public interface EtudiantCatalogRemote {

	
	/**
	 * Permet de créer un nouvel étudiant DTO
	 * @param firstname
	 * @param lastname 
	 * @param civilite
	 * @param email
	 * @param numTelephone
	 * @param password
	 * @param naissance
	 * @param posteActu
	 * @param villeActu
	 * @param nomEntreprise
	 * @return
	 */
	public EtudiantDTO createEtudiant(String firstname, String lastname, Civilite civilite, String email, String numTelephone,
			String password, Date naissance, String posteActu, String villeActu, String nomEntreprise, String diplome, int anneeDiplome, GroupeDTO groupe);


	/**
	 * 
	 * @return
	 */
	public List<EtudiantDTO> listEtudiant();

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean connexion(String email, String password);

	public EtudiantDTO getEtudiant(String email);
	
	public EtudiantDTO getEtudiant(int id);

	public void addCompetence(EtudiantDTO eDTO, String competence);

	public List<CompetenceDTO> getCompetences(EtudiantDTO eDTO);

	public List<PublicationDTO> getPublications(EtudiantDTO eDTO);

	void addPublication(EtudiantDTO eDTO, String titre, String message, Date date);

	public void addExperience(EtudiantDTO eDTO, String experience, String entreprise, String duree, String anneeDebut);

	public List<ExperienceDTO> getExperiences(EtudiantDTO eDTO);

	public void addHobbie(EtudiantDTO eDTO, String hobbie);

	public List<HobbieDTO> getHobbies(EtudiantDTO eDTO);

	public void addEcole(EtudiantDTO eDTO, String ecole);

	public List<EcoleDTO> getEcoles(EtudiantDTO eDTO);

	List<PublicationDTO> getPublications();

	public boolean isMailExists(String mail);

	public boolean isPasswordIsGood(String mail, String password);

	public void deleteExpProList(EtudiantDTO eDTO);

	public void deleteCompetenceList(EtudiantDTO eDTO);

	/**
	 * Permet de supprimer la liste des formations d'un étudiant
	 * @param eDTO
	 */
	public void deleteFormationList(EtudiantDTO eDTO);
	/**
	 * permet de supprimer la liste des loisir d'un étudiant
	 * @param eDTO
	 */
	public void deleteLoisirList(EtudiantDTO eDTO);

	
	/**
	 * Permet de modifier les information du profil d'un étudiant
	 * @param id
	 * @param posteActu
	 * @param villeActu
	 * @param nomEntreprise
	 * @param numtelephone
	 */
	
	public void updateEtudiant(int id, String posteActu, String villeActu, String nomEntreprise, String numtelephone, String facebook, String twitter, String viadeo, String linkedin);


	public void setGroupe(EtudiantDTO eDTO, GroupeDTO gDTO);


	public GroupeDTO getGroupe(EtudiantDTO eDTO);


	boolean valideLien(String lien, String site);
}
