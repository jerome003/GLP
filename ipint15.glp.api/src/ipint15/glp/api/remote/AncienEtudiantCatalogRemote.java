package ipint15.glp.api.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.HobbieDTO;

@Remote
public interface AncienEtudiantCatalogRemote {

	
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
	public AncienEtudiantDTO createEtudiant(String firstname, String lastname, Civilite civilite, String email, String numTelephone,
			String password, Date naissance,String statut, String posteActu, String villeActu, String nomEntreprise, String diplome, int anneeDiplome, GroupeDTO groupe);


	/**
	 * 
	 * @return
	 */
	public List<AncienEtudiantDTO> listEtudiant();

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean connexion(String email, String password);

	public AncienEtudiantDTO getEtudiant(String email);
	
	public AncienEtudiantDTO getEtudiant(int id);

	public void addCompetence(AncienEtudiantDTO eDTO, String competence, int niveau);

	public List<CompetenceDTO> getCompetences(AncienEtudiantDTO eDTO);

	public void addExperience(AncienEtudiantDTO eDTO, String libelle, String entreprise, String ville, String region, String pays, String debut, String fin, String description);

	public List<ExperienceDTO> getExperiences(AncienEtudiantDTO eDTO);

	public void addHobbie(AncienEtudiantDTO eDTO, String hobbie);

	public List<HobbieDTO> getHobbies(AncienEtudiantDTO eDTO);

	public void addEcole(AncienEtudiantDTO eDTO, String libelle, String etablissement, String debut, String fin, String ville, String region, String pays);

	public List<EcoleDTO> getEcoles(AncienEtudiantDTO eDTO);

	public boolean isMailExists(String mail);

	public boolean isPasswordIsGood(String mail, String password);

	public void deleteExpProList(AncienEtudiantDTO eDTO);

	public void deleteCompetenceList(AncienEtudiantDTO eDTO);

	/**
	 * Permet de supprimer la liste des formations d'un étudiant
	 * @param eDTO
	 */
	public void deleteFormationList(AncienEtudiantDTO eDTO);
	/**
	 * permet de supprimer la liste des loisir d'un étudiant
	 * @param eDTO
	 */
	public void deleteLoisirList(AncienEtudiantDTO eDTO);

	
	/**
	 * Permet de modifier les information du profil d'un étudiant
	 * @param id
	 * @param posteActu
	 * @param villeActu
	 * @param nomEntreprise
	 * @param numtelephone
	 * @param attentes
	 */
	
	public void updateEtudiant(int id, String statut, String posteActu, String villeActu, String nomEntreprise, String numtelephone, String facebook, String twitter, String viadeo, String linkedin, String attentes);


	public void setGroupe(AncienEtudiantDTO eDTO, GroupeDTO gDTO);


	public GroupeDTO getGroupe(AncienEtudiantDTO eDTO);
	
	
	
	/**
	 * Permet de modifier la liste des groupes
	 * 
	 */
	public void setLesGroupe(AncienEtudiantDTO eDTO, List<GroupeDTO> lesGroupe);
	
	
	/**
	 * Permet de recupérer la liste des groupe auquels appartient un ancien etudiant 
	 * @return
	 */
	public List<GroupeDTO> getLesGroupes(AncienEtudiantDTO eDTO);
	
	/**
	 * Permet de supprimer un groupe de la liste des groupes d'un ancien etudiant
	 * 
	 */
	public void removeGroupeInLesGroupes(AncienEtudiantDTO eDTO, GroupeDTO gDTO); 

	/**
	 * Permet l'ajout d'un nouveau groupe pour un ancien étudiant 
	
	 */
	public void addGroupeInLesGroupesNonInstitEtudiant(AncienEtudiantDTO eDTO, GroupeDTO gDTO);
	
	public void addAnimateurToGroupe(AncienEtudiantDTO eDTO, GroupeDTO gDTO);

	boolean valideLien(String lien, String site);
	
}