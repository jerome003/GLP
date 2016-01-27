package ipint15.glp.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.ExperienceDTO;

@Entity
@Table(name = "EXPERIENCE")
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private EtudiantProfil profil;

	private String libelle;

	private String entreprise;
	private String ville;
	private String region;
	private String pays;

	private String debut;

	private String fin;
	private String description;

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}


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

	
	

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public ExperienceDTO toExperienceDTO() {
		ExperienceDTO expDTO = new ExperienceDTO();
		expDTO.setId(this.id);
		expDTO.setLibelle(this.libelle);
		expDTO.setFin(fin);
		expDTO.setEntreprise(entreprise);
		expDTO.setDebut(debut);
		expDTO.setDescription(description);
		expDTO.setVille(ville);
		expDTO.setRegion(region);
		expDTO.setPays(pays);
		return expDTO;
	}


}