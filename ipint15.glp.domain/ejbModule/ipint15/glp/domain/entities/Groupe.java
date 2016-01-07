package ipint15.glp.domain.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.GroupeDTO;

@Entity
@Table(name = "GROUPE")
public class Groupe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToMany(mappedBy="groupes")
    private List<Etudiant> etudiants ;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Groupe [id=" + id + ", name=" + name + "]";
	}

	public GroupeDTO toGroupeDTO() {

		GroupeDTO gDTO = new GroupeDTO();
		gDTO.setId(this.getId());
		gDTO.setName(this.getName());
		return gDTO;

	}
	
}
