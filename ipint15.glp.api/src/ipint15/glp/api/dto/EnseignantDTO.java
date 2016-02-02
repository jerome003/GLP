package ipint15.glp.api.dto;

import java.util.List;

public class EnseignantDTO {

	private int id;
	private String prenom;
	private String nom;
	private String mail;
	private List<GroupeDTO> listeGroupes;

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
		return listeGroupes;
	}

	public void setListeGroupes(List<GroupeDTO> listeGroupes) {
		this.listeGroupes = listeGroupes;
	}

	@Override
	public String toString() {
		return "EnseignantDTO [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + "]";
	}

}
