package ipint15.glp.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	@JoinTable(name = "groupes_enseignant", joinColumns = @JoinColumn(name = "groupes_id"),
    inverseJoinColumns = @JoinColumn(name = "enseignant_id"))
	private List<Groupe> groupes;
	@ManyToMany
	@JoinTable(name = "groupesanimes_animateur", joinColumns = @JoinColumn(name = "groupesanimes_id"),
    inverseJoinColumns = @JoinColumn(name = "animateur_id"))
	private List<Groupe> groupesAnimes;
	@OneToMany(mappedBy = "enseignant")
	private List<Publication> mesPublications;

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

	public void setId(int id) {
		this.id = id;
	}

	public List<Publication> getMesPublications() {
		return mesPublications;
	}

	public void setMesPublications(List<Publication> mesPublications) {
		this.mesPublications = mesPublications;
	}

	public EnseignantDTO toEnseignantDTO() {
		EnseignantDTO e = new EnseignantDTO();
		e.setId(this.getId());
		e.setNom(this.getNom());
		e.setPrenom(this.getPrenom());
		e.setMail(this.getMail());
		return e;
	}

}
