package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class EtudiantDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String prenom;
	private String nom;
	private String password;
	private String email;
	private Calendar naissance;

	
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
	public Calendar getNaissance() {
		return naissance;
	}
	public void setNaissance(Calendar naissance) {
		this.naissance = naissance;
	}
	@Override
	public String toString() {
		return "Etudiant [id=" + id  + ", prenom=" + prenom + ", nom=" + nom + ", password="
				+ password + ", email=" + email + ", naissance=" + naissance + "]";
	}

}
