package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.List;


public class EtudiantProfilDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private EtudiantDTO etudiant ;
    
    private List<CompetenceDTO> mesCompetences ;
    
    private List<HobbieDTO> mesHobbies ;
    
    private List<ExperienceDTO> mesExperiences ;
    
    private List<EcoleDTO> mesEcoles ;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EtudiantDTO getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(EtudiantDTO etudiant) {
		this.etudiant = etudiant;
	}
	public List<CompetenceDTO> getMesCompetences() {
		return mesCompetences;
	}
	public void setMesCompetences(List<CompetenceDTO> mesCompetences) {
		this.mesCompetences = mesCompetences;
	}
	public List<HobbieDTO> getMesHobbies() {
		return mesHobbies;
	}
	public void setMesHobbies(List<HobbieDTO> mesHobbies) {
		this.mesHobbies = mesHobbies;
	}
	public List<ExperienceDTO> getMesExperiences() {
		return mesExperiences;
	}
	public void setMesExperiences(List<ExperienceDTO> mesExperiences) {
		this.mesExperiences = mesExperiences;
	}
	public List<EcoleDTO> getMesEcoles() {
		return mesEcoles;
	}
	public void setMesEcoles(List<EcoleDTO> mesEcoles) {
		this.mesEcoles = mesEcoles;
	}
	@Override
	public String toString() {
		return "EtudiantProfilDTO [id=" + id + ", etudiant=" + etudiant + ", mesCompetences=" + mesCompetences
				+ ", mesHobbies=" + mesHobbies + ", mesExperiences=" + mesExperiences + ", mesEcoles=" + mesEcoles
				+ "]";
	}

	
	
	

}
