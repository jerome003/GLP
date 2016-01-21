package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupeDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private List<EtudiantDTO> etudiants ;
	private List<ModerateurDTO> moderateurs ;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ModerateurDTO> getModerateurs() {
		if (moderateurs == null) {
			this.moderateurs = new ArrayList<ModerateurDTO>();
		}
		return moderateurs;
	}
	public void setModerateurs(List<ModerateurDTO> moderateurs) {
		this.moderateurs = moderateurs;
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
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "GroupeDTO [id=" + id + ", name=" + name +  ", etudiants=" + ((!getEtudiants().isEmpty()) ? "oui" : "non") 
				+  ", moderateurs=" + ((!getModerateurs().isEmpty()) ? "oui" : "non")+"]";
	}	
}
