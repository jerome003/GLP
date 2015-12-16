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
import ipint15.glp.api.dto.HobbieDTO;

@Remote
public interface EtudiantCatalogRemote {
	public EtudiantDTO createEtudiant(String firstname, String lastname, Civilite civilite, String email,
			String password, Date naissance);

	public List<EtudiantDTO> listEtudiant();

	public boolean connexion(String email, String password);

	public EtudiantDTO getEtudiant(String email);
	
	public EtudiantDTO getEtudiant(int id);

	public void addCompetence(EtudiantDTO eDTO, String competence);

	public List<CompetenceDTO> getCompetences(EtudiantDTO eDTO);

	public List<PublicationDTO> getPublications(EtudiantDTO eDTO);

	void addPublication(EtudiantDTO eDTO, String titre, String message, Date date);

	public void addExperience(EtudiantDTO eDTO, String experience);

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

	public void deleteFormationList(EtudiantDTO eDTO);

	public void deleteLoisirList(EtudiantDTO eDTO);
}
