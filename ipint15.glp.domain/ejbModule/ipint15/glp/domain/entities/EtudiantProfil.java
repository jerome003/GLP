package ipint15.glp.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.EtudiantProfilDTO;

@Entity
@Table(name = "PROFIL")
public class EtudiantProfil {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne(mappedBy = "profil")
	private AncienEtudiant etudiant;

	@OneToMany(mappedBy = "profil", orphanRemoval = true, cascade = CascadeType.REMOVE)
	private List<Competence> mesCompetences;

	@OneToMany(mappedBy = "profil")
	private List<Hobbie> mesHobbies;

	@OneToMany(mappedBy = "profil")
	private List<Experience> mesExperiences;

	@OneToMany(mappedBy = "profil")
	private List<Ecole> mesEcoles;

	@OneToMany(mappedBy = "profil")
	private List<Publication> mesPublications;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AncienEtudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(AncienEtudiant etudiant) {
		this.etudiant = etudiant;
	}

	public List<Competence> getMesCompetences() {
		return mesCompetences;
	}

	public void setMesCompetences(List<Competence> mesCompetences) {
		this.mesCompetences = mesCompetences;
	}

	public List<Hobbie> getMesHobbies() {
		return mesHobbies;
	}

	public void setMesHobbies(List<Hobbie> mesHobbies) {
		this.mesHobbies = mesHobbies;
	}

	public List<Experience> getMesExperiences() {
		return mesExperiences;
	}

	public void setMesExperiences(List<Experience> mesExperiences) {
		this.mesExperiences = mesExperiences;
	}

	public List<Ecole> getMesEcoles() {
		return mesEcoles;
	}

	public void setMesEcoles(List<Ecole> mesEcoles) {
		this.mesEcoles = mesEcoles;
	}

	public List<Publication> getMesPublications() {
		return mesPublications;
	}

	public void setPublications(List<Publication> mesPublications) {
		this.mesPublications = mesPublications;
	}

	public EtudiantProfilDTO toEtudiantProfilDTO() {

		EtudiantProfilDTO eDTO = new EtudiantProfilDTO();
		eDTO.setId(this.getId());
		return eDTO;

	}

	@Override
	public String toString() {
		return "EtudiantProfil [id=" + id + ", etudiant=" + etudiant + ", mesCompetences=" + mesCompetences
				+ ", mesHobbies=" + mesHobbies + ", mesExperiences=" + mesExperiences + ", mesEcoles=" + mesEcoles
				+ "]";
	}

}
