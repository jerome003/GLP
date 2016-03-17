package ipint15.glp.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.GroupeDTO;

@Entity
@Table(name = "GROUPE")
@NamedQueries({
		@NamedQuery(name = "getGroupesOfAncienByIdAncien", query = "select g from Groupe g join g.ancienEtudiants a where a.id = :id"),
		@NamedQuery(name="getGroupeById", query="select o from Groupe o WHERE o.id = :id"),
		@NamedQuery(name="getGroupesInstitutionnels", query="select g from Groupe g where g.institutionnel = true"),
		@NamedQuery(name="getGroupesNonInstitutionnels", query="select g from Groupe g where g.institutionnel = false")})
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
	@ManyToMany(mappedBy = "groupesAnimes")
	private List<Enseignant> animateur;
	@OneToMany(mappedBy = "groupe")
	private List<Publication> publications;
	
	private AncienEtudiant animateurGroupeNonInstit;
	
	private boolean institutionnel;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AncienEtudiant getAnimateurGroupeNonInstit() {
		return animateurGroupeNonInstit;
	}

	public void setAnimateurGroupeNonInstit(AncienEtudiant animateurGroupeNonInstit) {
		this.animateurGroupeNonInstit = animateurGroupeNonInstit;
	}

	public List<AncienEtudiant> getAncienEtudiants() {
		return ancienEtudiants;
	}

	public void setAncienEtudiants(List<AncienEtudiant> ancienEtudiants) {
		this.ancienEtudiants = ancienEtudiants;
	}

	public List<Etudiant> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
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
	
	

	public List<Enseignant> getAnimateur() {
		return animateur;
	}

	public void setAnimateur(List<Enseignant> animateur) {
		this.animateur = animateur;
	}

	public GroupeDTO toGroupeDTO() {

		GroupeDTO gDTO = new GroupeDTO();
		gDTO.setId(this.getId());
		gDTO.setName(this.getName());
		gDTO.setDescription(description);
		gDTO.setInstitutionnel(this.institutionnel);
		return gDTO;

	}

	/**
	 * @return the isInstitutionnel
	 */
	public boolean isInstitutionnel() {
		return institutionnel;
	}

	/**
	 * @param isInstitutionnel the isInstitutionnel to set
	 */
	public void setInstitutionnel(boolean isInstitutionnel) {
		this.institutionnel = isInstitutionnel;
	}

}
