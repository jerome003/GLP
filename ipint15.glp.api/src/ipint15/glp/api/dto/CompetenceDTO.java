package ipint15.glp.api.dto;

import java.io.Serializable;

public class CompetenceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private EtudiantProfilDTO profil;

	private String libelle;

	private int niveau;

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
		return "Competence [id=" + id + ", profil=" + ((profil != null) ? "oui" : "non") + ", libelle=" + libelle + "]";
	}

	/**
	 * @return the niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau
	 *            the niveau to set
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

}
