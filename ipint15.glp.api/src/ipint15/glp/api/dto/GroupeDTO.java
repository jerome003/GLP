package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<EtudiantDTO> etudiants ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EtudiantDTO> getEtudiants() {
		if (etudiants == null) {
			this.etudiants = new ArrayList<EtudiantDTO>();
		}
		return etudiants;
	}
	public void setEtudiants(List<EtudiantDTO> etudiants) {
		this.etudiants = etudiants;
	}
	@Override
	public String toString() {
		return "GroupeDTO [id=" + id + ", name=" + name +  ", etudiants=" + ((!etudiants.isEmpty()) ? "oui" : "non") +"]";
	}
	
	
	
	

}
