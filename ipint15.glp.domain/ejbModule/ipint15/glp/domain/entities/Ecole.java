package ipint15.glp.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.EcoleDTO;

@Entity
@Table(name = "ECOLE")
public class Ecole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
   
    @ManyToOne
    private EtudiantProfil profil ;
    
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
	
	
	
	
	
	///////////////////////////////////////////////////////////////////
	
	public EcoleDTO toEcoleDTO() {
		EcoleDTO ecoleDTO = new EcoleDTO();
		ecoleDTO.setId(this.id);
		ecoleDTO.setLibelle(this.libelle);
		ecoleDTO.setEtablissement(etablissement);
		ecoleDTO.setDebut(debut);
		ecoleDTO.setFin(fin);
		ecoleDTO.setVille(ville);
		ecoleDTO.setRegion(region);
		ecoleDTO.setPays(pays);
		return ecoleDTO;
	}

	@Override
	public String toString() {
		return "Ecole [id=" + id + ", libelle=" + libelle + ", etablissement=" + etablissement + ", debut=" + debut
				+ ", fin=" + fin + ", ville=" + ville + ", region=" + region + ", pays=" + pays + "]";
	}
	
	

	
	
	
	
}