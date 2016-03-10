package ipint15.glp.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

import ipint15.glp.api.dto.EtudiantDTO;

/**
 * @author grimonprez
 *
 */
@Entity
@Table(name = "ETUDIANT")
@NamedQueries({
	@NamedQuery(name = "getListEtudiantByIdGroupe", query = "Select e from Etudiant e where e.groupe.id = :id")})
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
	private String mail;
	@ManyToMany
	private List<Groupe> groupes;

	@OneToMany(mappedBy = "etudiant")
	private List<Publication> mesPublications;


	@ManyToOne
	private Groupe groupe;

	public Etudiant() {
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

	public List<Groupe> getGroupes() {
		return groupes;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public List<Publication> getMesPublications() {
		return mesPublications;
	}

	public void setMesPublications(List<Publication> mesPublications) {
		this.mesPublications = mesPublications;
	}

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", mail=" + mail + "]";
	}
	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	
	
	/**
	 * Mappe un Etudiant en etudiantDTO. NE MAPPE PAS la liste de GroupeDTO
	 * 
	 * @return
	 */
	public EtudiantDTO toEtudiantDTO() {
		EtudiantDTO dTO = new EtudiantDTO();
		dTO.setGroupe(this.groupe.toGroupeDTO());
		dTO.setId(this.getId());
		dTO.setNom(this.getNom());
		dTO.setPrenom(this.getPrenom());
		dTO.setMail(this.getMail());
		return dTO;
	}

}
