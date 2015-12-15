package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
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
    
    private List<PublicationDTO> mesPublications;
    
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
		if (mesCompetences == null)
			mesCompetences = new ArrayList<CompetenceDTO>();
		return mesCompetences;
	}
	public void setMesCompetences(List<CompetenceDTO> mesCompetences) {
		this.mesCompetences = mesCompetences;
	}
	public List<HobbieDTO> getMesHobbies() {
		if (mesHobbies == null)
			mesHobbies = new ArrayList<HobbieDTO>();
		return mesHobbies;
	}
	public void setMesHobbies(List<HobbieDTO> mesHobbies) {
		this.mesHobbies = mesHobbies;
	}
	public List<ExperienceDTO> getMesExperiences() {
		if (mesExperiences == null)
			mesExperiences = new ArrayList<ExperienceDTO>();
		return mesExperiences;
	}
	public void setMesExperiences(List<ExperienceDTO> mesExperiences) {
		this.mesExperiences = mesExperiences;
	}
	public List<EcoleDTO> getMesEcoles() {
		if (mesEcoles == null)
			mesEcoles = new ArrayList<EcoleDTO>();
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

	public List<PublicationDTO> getMesPublications(){
		if (mesPublications == null)
			mesPublications = new ArrayList<PublicationDTO>();
		return mesPublications;
	}
	
	public void setMesPublications(List<PublicationDTO> mesPublications){
		this.mesPublications = mesPublications;
	}
	
	

}
