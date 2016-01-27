package ipint15.glp.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.EtudiantDTO;


/**
 * @author kabri
 *
 */
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
	private boolean validation;
	private String numTelephone;
	@Temporal(TemporalType.TIME)
	private Date naissance;

	private String villeActu;
	private String posteActu;
	private String nomEntreprise;
	private String twitter;
	private String facebook;
	private String linkedin;
	private String viadeo;

	private String diplome;
	private int anneeDiplome;
    @ManyToOne
    private Groupe groupe ;

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
	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	
	public boolean getValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
	}
	
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getViadeo() {
		return viadeo;
	}
	public void setViadeo(String viadeo) {
		this.viadeo = viadeo;
	}
	public EtudiantDTO toEtudiantDTO() {
		EtudiantDTO pDTO = new EtudiantDTO();
		pDTO.setId(this.getId());
		pDTO.setPrenom(this.getPrenom());
		pDTO.setNom(this.getNom());
		pDTO.setCivilite(this.getCivilite());
		pDTO.setEmail(this.getEmail());
		pDTO.setNumTelephone(this.getNumTelephone());
		pDTO.setPassword(this.getPassword());
		pDTO.setNaissance(this.getNaissance());

		pDTO.setPosteActu(this.getPosteActu());
		pDTO.setVilleActu(this.getVilleActu());
		pDTO.setNomEntreprise(this.getNomEntreprise());

		pDTO.setDiplome(this.getDiplome());
		pDTO.setAnneeDiplome(this.anneeDiplome);
		
		pDTO.setGroupe(this.groupe.toGroupeDTO());
		pDTO.setTwitter(this.getTwitter());
		pDTO.setFacebook(this.getFacebook());
		pDTO.setLinkedin(this.getLinkedin());
		pDTO.setViadeo(this.getViadeo());

		return pDTO;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", civilite=" + civilite + ", password="
				+ password + ", email=" + email + ", validation=" + validation + ", naissance=" + naissance + ", profil=" + ((profil!=null) ? "oui" : "non") 
						+ ", monGroupe=" + groupe + ", villeActu=" + villeActu + ", posteActu=" + posteActu + ", nomEntreprise=" + nomEntreprise
						 + "]";

	}
	


	
	
}