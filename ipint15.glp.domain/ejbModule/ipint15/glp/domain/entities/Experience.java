package ipint15.glp.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.ExperienceDTO;

@Entity
@Table(name = "EXPERIENCE")
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
   
    @ManyToOne
    private EtudiantProfil profil ;
    
    private String libelle;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EtudiantProfil getProfil() {
		return profil;
	}

	public void setProfil(EtudiantProfil profil) {
		this.profil = profil;
	}
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public ExperienceDTO toExperienceDTO() {
		ExperienceDTO expDTO = new ExperienceDTO();
		expDTO.setId(this.id);
		expDTO.setLibelle(this.libelle);
		return expDTO;
	}

	@Override
	public String toString() {
		return "Competence [id=" + id + ", profil=" + ((profil!=null) ? "oui" : "non") + ", libelle=" + libelle + "]";
	}
	
	
}
