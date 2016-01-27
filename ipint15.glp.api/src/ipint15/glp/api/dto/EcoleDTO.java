package ipint15.glp.api.dto;

import java.io.Serializable;

public class EcoleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	   
    private EtudiantProfilDTO profil ;
    
    private String libelle;
    private String etablissement;
    private String debut;
    private String fin;
    private String ville;
    private String region;
    private String pays;
    
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

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "EcoleDTO [id=" + id + ", libelle=" + libelle + ", etablissement=" + etablissement + ", debut=" + debut
				+ ", fin=" + fin + ", ville=" + ville + ", region=" + region + ", pays=" + pays + "]";
	}
	
	

}