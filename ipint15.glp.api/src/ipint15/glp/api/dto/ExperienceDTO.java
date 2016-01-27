package ipint15.glp.api.dto;

import java.io.Serializable;

public class ExperienceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private EtudiantProfilDTO profil;

	private String libelle;
	private String entreprise;
	private String ville;
	private String region;
	private String pays;
	private String debut;
	private String fin;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EtudiantProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(EtudiantProfilDTO profil) {
		this.profil = profil;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
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

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public String toString() {
		return "ExperienceDTO [id=" + id + ", libelle=" + libelle + ", entreprise=" + entreprise + ", ville=" + ville
				+ ", region=" + region + ", pays=" + pays + ", debut=" + debut + ", fin=" + fin + ", description="
				+ description + "]";
	}
	
}