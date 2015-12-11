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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import javax.persistence.TemporalType;

import ipint15.glp.api.dto.Civilite;


@Entity
@Table(name = "ETUDIANT")
public class Etudiant implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String prenom;
	private String nom;
	@Enumerated(EnumType.ORDINAL)
	private Civilite civilite;
	private String password;
	private String email;
	@Temporal(TemporalType.TIME)
	private Date naissance;
	
	
	@OneToOne
    private EtudiantProfil profil ;
	
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
	public Civilite getCivilite() {
		return civilite;
	}
	public void setCivilite(Civilite civilite) {
		this.civilite = civilite;
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
	public Date getNaissance() {
		return naissance;
	}
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}
	
	public EtudiantProfil getProfil() {
		return profil;
	}
	public void setProfil(EtudiantProfil profil) {
		this.profil = profil;
	}
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", civilite=" + civilite + ", password="
				+ password + ", email=" + email + ", naissance=" + naissance + ", profil=" + profil + "]";
	}

	
	
}