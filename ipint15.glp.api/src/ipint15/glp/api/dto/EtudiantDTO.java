package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.Date;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class EtudiantDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String prenom;
	private String nom;
	private Civilite civilite;
	private String password;
	private String email;
	private Date naissance;
	private EtudiantProfilDTO profil ;


	
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
	
	
	public EtudiantProfilDTO getProfil() {
		return profil;
	}
	public void setProfil(EtudiantProfilDTO profil) {
		this.profil = profil;
	}
	@Override
	public String toString() {
		return "EtudiantDTO [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", civilite=" + civilite
				+ ", password=" + password + ", email=" + email + ", naissance=" + naissance + ", profil=" + profil
				+ "]";
	}
	
	

}
