package ipint15.glp.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class GroupeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	@NotEmpty(message = "Veuillez saisir un nom de groupe")
	private String name;
	private List<AncienEtudiantDTO> etudiants;
	private List<ModerateurDTO> moderateurs;
	@NotEmpty(message = "Veuillez saisir une description du groupe")
	private String description;
	private List<PublicationDTO> listPublications;
	private boolean isInstitutionnel;

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

	public List<AncienEtudiantDTO> getEtudiants() {
		if (etudiants == null) {
			this.etudiants = new ArrayList<AncienEtudiantDTO>();
		}
		return etudiants;
	}

	public void setEtudiants(List<AncienEtudiantDTO> etudiants) {
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
		// return "GroupeDTO [id=" + id + ", name=" + name + ", etudiants=" +
		// ((!getEtudiants().isEmpty()) ? "oui" : "non")
		// + ", moderateurs=" + ((!getModerateurs().isEmpty()) ? "oui" :
		// "non")+"]";
		return name;
	}

	/**
	 * @return the listPublications
	 */
	public List<PublicationDTO> getListPublications() {
		return listPublications;
	}

	/**
	 * @param listPublications
	 *            the listPublications to set
	 */
	public void setListPublications(List<PublicationDTO> listPublications) {
		this.listPublications = listPublications;
	}

	/**
	 * @return the isInstitutionnel
	 */
	public boolean isInstitutionnel() {
		return isInstitutionnel;
	}

	/**
	 * @param isInstitutionnel the isInstitutionnel to set
	 */
	public void setInstitutionnel(boolean isInstitutionnel) {
		this.isInstitutionnel = isInstitutionnel;
	}
}
