package ipint15.glp.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import ipint15.glp.api.dto.EnseignantDTO;

/**
 * @author grimonprez
 *
 */
@Entity
@Table(name = "ENSEIGNANT")
public class Enseignant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String prenom;
	private String nom;
	private String mail;
	@ManyToMany
	private List<Groupe> groupes;
	@ManyToMany
	private List<Groupe> groupesAnimes;

	public List<Groupe> getGroupesAnimes() {
		return groupesAnimes;
	}

	public void setGroupesAnimes(List<Groupe> groupesAnimes) {
		this.groupesAnimes = groupesAnimes;
	}

	public Enseignant() {
		super();
	}

	@Override
	public String toString() {
		return "Enseignant [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + "]";
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

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public int getId() {
		return id;
	}

	public EnseignantDTO toDTO() {
		EnseignantDTO e = new EnseignantDTO();
		e.setId(id);
		e.setMail(mail);
		e.setNom(nom);
		e.setPrenom(prenom);
		for(Groupe g : groupes){
			e.addGroupeDTO(g.toGroupeDTO());
		}
		for(Groupe g : groupesAnimes){
			e.addGroupeDTOAnime(g.toGroupeDTO());
		}
		return e;
	}

}
