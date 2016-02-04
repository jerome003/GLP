package ipint15.glp.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ipint15.glp.api.dto.GroupeDTO;

@Entity
@Table(name = "GROUPE")
@NamedQueries({
		@NamedQuery(name = "getGroupesOfAncienByIdAncien", query = "select g from Groupe g join g.ancienEtudiants a where a.id = :id") })
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(mappedBy = "groupe")
	private List<AncienEtudiant> ancienEtudiants;
	@ManyToMany(mappedBy = "groupes")
	private List<Moderateur> moderateurs;
	private String description;
	@ManyToMany(mappedBy = "groupes")
	private List<Etudiant> etudiants;
	@ManyToMany(mappedBy = "groupes")
	private List<Enseignant> enseignant;
	@OneToMany(mappedBy = "groupe")
	private List<Publication> publications;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AncienEtudiant> getAncienEtudiant() {
		return ancienEtudiants;
	}

	public void setAncienEtudiant(List<AncienEtudiant> etudiants) {
		this.ancienEtudiants = etudiants;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Moderateur> getModerateurs() {
		return moderateurs;
	}

	public void setModerateurs(List<Moderateur> moderateurs) {
		this.moderateurs = moderateurs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Groupe [id=" + id + ", name=" + name + "]";
	}

	public List<AncienEtudiant> getAncienEtudiants() {
		return ancienEtudiants;
	}

	public List<Enseignant> getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(List<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public GroupeDTO toGroupeDTO() {

		GroupeDTO gDTO = new GroupeDTO();
		gDTO.setId(this.getId());
		gDTO.setName(this.getName());
		gDTO.setDescription(description);
		return gDTO;

	}

}
