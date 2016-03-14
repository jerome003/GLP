package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;


public class PublicationDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private EtudiantProfilDTO profil;
	
	private EtudiantDTO etudiant;
	
	private EnseignantDTO enseignant;

	@NotEmpty( message = "Veuillez saisir un titre")
	private String titre;

	@NotEmpty( message = "Veuillez saisir un message")
	private String message;

	private Date date;

	private GroupeDTO groupeDTO;

	private boolean publicationPublic;
	
	private boolean postByAnim ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EtudiantProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(EtudiantProfilDTO profil) {
		this.profil = profil;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public GroupeDTO getGroupeDTO() {
		return groupeDTO;
	}

	public void setGroupeDTO(GroupeDTO groupeDTO) {
		this.groupeDTO = groupeDTO;
	}

	public boolean isPublicationPublic() {
		return publicationPublic;
	}

	public void setPublicationPublic(boolean publicationPublic) {
		this.publicationPublic = publicationPublic;
	}
	
	public boolean isPostByAnim() {
		return postByAnim;
	}

	public void setPostByAnim(boolean isPostByAnim) {
		this.postByAnim = isPostByAnim;
	}

	public EtudiantDTO getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(EtudiantDTO etudiant) {
		this.etudiant = etudiant;
	}

	public EnseignantDTO getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(EnseignantDTO enseignant) {
		this.enseignant = enseignant;
	}

	@Override
	public String toString() {
		return "PublicationDTO [id=" + id + ", profil=" + profil + ", titre=" + titre + ", message=" + message
				+ ", date=" + date + ", publicationPublic=" + publicationPublic + ", groupe : "+((groupeDTO == null) ? "null" : "oui")  +"]"+"publicationAnimateur="+ postByAnim;
	}

}
