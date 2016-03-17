package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;





public class EtudiantDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String prenom;
	private String nom;
	private String mail;
	private List<GroupeDTO> listeGroupes;

	private List<PublicationDTO> mesPublications;

	private GroupeDTO groupe ;


	public EtudiantDTO() {
		super();
	}

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

	public List<GroupeDTO> getListeGroupes() {
		if (listeGroupes==null) {
			listeGroupes = new ArrayList<GroupeDTO>();
		}
		return listeGroupes;
	}

	public void setListeGroupes(List<GroupeDTO> listeGroupes) {
		this.listeGroupes = listeGroupes;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<PublicationDTO> getMesPublications() {
		if (mesPublications==null) {
			mesPublications = new ArrayList<PublicationDTO>();
		}
		return mesPublications;
	}

	public void setMesPublications(List<PublicationDTO> mesPublications) {
		this.mesPublications = mesPublications;
	}

	@Override
	public String toString() {
		return "EtudiantDTO [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + "]";
	}
	public GroupeDTO getGroupe() {
		return groupe;
	}

	public void setGroupe(GroupeDTO groupe) {
		this.groupe = groupe;
	}
	

}
