package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnseignantDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String prenom;
	private String nom;
	private String mail;
	private List<GroupeDTO> listeGroupes;
	private List<GroupeDTO> listeGroupesAnime;
	private List<PublicationDTO> mesPublications;

	public EnseignantDTO() {
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
	
	public void addGroupeDTO (GroupeDTO groupe){
		this.listeGroupes.add(groupe);
	}
	
	public void addGroupeDTOAnime (GroupeDTO groupe){
		this.listeGroupesAnime.add(groupe);
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
		return "EnseignantDTO [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + "]";
	}

	/**
	 * @return the listeGroupesAnime
	 */
	public List<GroupeDTO> getListeGroupesAnime() {
		if (listeGroupesAnime==null) {
			listeGroupesAnime = new ArrayList<GroupeDTO>();
		}
		return listeGroupesAnime;
	}

	/**
	 * @param listeGroupesAnime the listeGroupesAnime to set
	 */
	public void setListeGroupesAnime(List<GroupeDTO> listeGroupesAnime) {
		this.listeGroupesAnime = listeGroupesAnime;
	}

}
