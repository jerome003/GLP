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

	public EcoleDTO toEcoleDTO() {
		EcoleDTO ecoleDTO = new EcoleDTO();
		ecoleDTO.setId(this.id);
		ecoleDTO.setLibelle(this.libelle);
		return ecoleDTO;
	}
	
	@Override
	public String toString() {
		return "Competence [id=" + id + ", profil=" + ((profil!=null) ? "oui" : "non") + ", libelle=" + libelle + "]";
	}
	
	
	
}
