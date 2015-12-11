package ipint15.glp.api.dto;

import java.io.Serializable;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class EtudiantDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@NotEmpty
	private String prenom;
	@NotEmpty
	private String nom;
	@NotEmpty
	private Civilite civilite;
	@NotEmpty
	private String password;
	@NotEmpty
	@Email
	private String email;
	@Past(message = "Veuillez taper votre vrai date de naissance")
	private Date naissance;


	
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
	@Override
	public String toString() {
		return "Etudiant [id=" + id  + ", prenom=" + prenom + ", nom=" + nom + ", civilite=" + civilite + ", password="
				+ password + ", email=" + email + ", naissance=" + naissance + "]";
	}

}