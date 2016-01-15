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

	private String anneeDebut;

	private String duree;

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

	@Override
	public String toString() {
		return "ExperienceDTO [id=" + id + ", profil=" + ((profil != null) ? "oui" : "non") + " libelle=" + libelle
				+ ", entreprise=" + entreprise + ", anneeDebut=" + anneeDebut + ", duree=" + duree + "]";
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getAnneeDebut() {
		return anneeDebut;
	}

	public void setAnneeDebut(String anneeDebut) {
		this.anneeDebut = anneeDebut;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

}
