package ipint15.glp.api.dto;




public class CompetenceDTO {
	
	private int id;
   
    private EtudiantProfilDTO profil ;
    
    private String libelle;

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
		return "Competence [id=" + id + ", profil=" + profil + ", libelle=" + libelle + "]";
	}

}
