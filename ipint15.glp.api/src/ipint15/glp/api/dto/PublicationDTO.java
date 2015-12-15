package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.Date;

public class PublicationDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
   
    private EtudiantProfilDTO profil ;
    
    private String titre;
    
    private String message;
    
    private Date date;

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


	@Override
	public String toString() {
		return "Competence [id=" + id + ", profil="+ ((profil!=null) ? "oui" : "non") + ", titre=" + titre + ", message=" + message+ "]";
	}

}
