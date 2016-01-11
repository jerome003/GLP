package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;



/**
 * @author kabri
 *
 */
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
	private String numTelephone;
	@NotNull (message="Veuillez saisir une date de naissance")
	@Past (message= "Veuillez saisir une date de naissance valide")
	private Date naissance;
	
	private String villeActu;
	private String posteActu;
	private String nomEntreprise;
	
	private EtudiantProfilDTO profil ;
	@NotEmpty( message = "Veuillez saisir un diplome" )
	private String diplome;
	@NotNull(message = "Veuillez saisir une année") 
	private int anneeDiplome;
	private GroupeDTO groupe ;
	private String facebook;
	private String twitter;
	private String viadeo;
	private String linkedin;


	
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
	
	
	public String getNumTelephone() {
		return numTelephone;
	}
	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}
	public String getVilleActu() {
		return villeActu;
	}
	public void setVilleActu(String villeActu) {
		this.villeActu = villeActu;
	}
	public String getPosteActu() {
		return posteActu;
	}
	public void setPosteActu(String posteActu) {
		this.posteActu = posteActu;
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
	
	
	public EtudiantProfilDTO getProfil() {
		return profil;
	}
	public void setProfil(EtudiantProfilDTO profil) {
		this.profil = profil;
	}
	

	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	
	public int getAnneeDiplome() {
		return anneeDiplome;
	}
	public void setAnneeDiplome(int anneeDiplome) {
		this.anneeDiplome = anneeDiplome;
	}
	
	public GroupeDTO getGroupe() {
		return groupe;
	}
	public void setGroupe(GroupeDTO groupe) {
		this.groupe = groupe;
	}
	
	
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getViadeo() {
		return viadeo;
	}
	public void setViadeo(String viadeo) {
		this.viadeo = viadeo;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	@Override
	public String toString() {
		return "EtudiantDTO [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", civilite=" + civilite
				+ ", password=" + password + ", email=" + email + ", naissance=" + naissance + ", profil=" + ((profil!=null) ? "oui" : "non")
				+ ", diplome=" + diplome + ", Annee obtention=" + anneeDiplome+ ", groupe= " + groupe + ", villeActu=" + villeActu + ", posteActu=" + posteActu + ", nomEntreprise="
						+ nomEntreprise + "]";

	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((civilite == null) ? 0 : civilite.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((naissance == null) ? 0 : naissance.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((nomEntreprise == null) ? 0 : nomEntreprise.hashCode());
		result = prime * result + ((numTelephone == null) ? 0 : numTelephone.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((posteActu == null) ? 0 : posteActu.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((villeActu == null) ? 0 : villeActu.hashCode());
		return result;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EtudiantDTO other = (EtudiantDTO) obj;
		if (civilite != other.civilite)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (naissance == null) {
			if (other.naissance != null)
				return false;
		} else if (!naissance.equals(other.naissance))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (nomEntreprise == null) {
			if (other.nomEntreprise != null)
				return false;
		} else if (!nomEntreprise.equals(other.nomEntreprise))
			return false;
		if (numTelephone == null) {
			if (other.numTelephone != null)
				return false;
		} else if (!numTelephone.equals(other.numTelephone))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (posteActu == null) {
			if (other.posteActu != null)
				return false;
		} else if (!posteActu.equals(other.posteActu))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (villeActu == null) {
			if (other.villeActu != null)
				return false;
		} else if (!villeActu.equals(other.villeActu))
			return false;
		return true;
	}

	
	

}
