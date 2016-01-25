package ipint15.glp.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.CompetenceDTO;

@Entity
@Table(name = "COMPETENCE")
public class Competence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private EtudiantProfil profil;

	private String libelle;

	private int niveau;

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

	public CompetenceDTO toCompetenceDTO() {
		CompetenceDTO cDTO = new CompetenceDTO();
		cDTO.setId(this.id);
		cDTO.setLibelle(this.libelle);
		cDTO.setNiveau(this.niveau);
		return cDTO;
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
