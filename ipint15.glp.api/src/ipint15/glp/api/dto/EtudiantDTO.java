package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;



public class EtudiantDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@NotEmpty( message = "Veuillez saisir un prenom" )
	private String prenom;
	@NotEmpty( message = "Veuillez saisir un nom" )
	private String nom;
	private Civilite civilite;
	private String password;
	@NotEmpty( message = "Veuillez saisir une adresse email" )
	private String email;
	@NotNull (message="Veuillez saisir une date de naissance")
	@Past (message= "Veuillez saisir une date de naissance valide")
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
				+ ", password=" + password + ", email=" + email + ", naissance=" + naissance + ", profil=" + ((profil!=null) ? "oui" : "non")
				+ "]";
	}
	
	

}
