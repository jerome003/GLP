package ipint15.glp.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ipint15.glp.api.dto.GroupeDTO;

@Entity
@Table(name = "GROUPE")
public class Groupe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(mappedBy="groupe")
    private List<AncienEtudiant> etudiants ;
	@ManyToMany(mappedBy="groupes")
    private List<Moderateur> moderateurs ;
	private String description;

	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<AncienEtudiant> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(List<AncienEtudiant> etudiants) {
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

	public GroupeDTO toGroupeDTO() {

		GroupeDTO gDTO = new GroupeDTO();
		gDTO.setId(this.getId());
		gDTO.setName(this.getName());
		gDTO.setDescription(description);
		return gDTO;

	}
	
}
