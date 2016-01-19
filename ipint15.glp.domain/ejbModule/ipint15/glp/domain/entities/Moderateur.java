package ipint15.glp.domain.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import javax.persistence.TemporalType;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.ModerateurDTO;


/**
 * @author delporte
 *
 */
@Entity
@Table(name = "MODERATEUR")
public class Moderateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String prenom;
	private String nom;

	private String password;
	private String email;

	@ManyToMany
	private List<Groupe> groupes ;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupe(List<Groupe> groupes) {
		this.groupes = groupes;
	}


	public ModerateurDTO toModerateurDTO() {
		ModerateurDTO mDTO = new ModerateurDTO();
		mDTO.setId(this.getId());
		mDTO.setPrenom(this.getPrenom());
		mDTO.setNom(this.getNom());
		mDTO.setEmail(this.getEmail());

		//mDTO.setGroupe(this.groupe.toGroupeDTO());

		return mDTO;
	}


	@Override
	public String toString() {
		return "Moderateur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", password="
				+ password + ", email=" + email +  "]";

	}





}