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
	private List<AncienEtudiantDTO> ancienEtudiants;
	private List<EtudiantDTO> etudiants;
	private List<ModerateurDTO> moderateurs;
	private List<EnseignantDTO> animateurs;

	private AncienEtudiantDTO animateurGroupeNonInstit;

	private List<EnseignantDTO> enseignants;

	@NotEmpty(message = "Veuillez saisir une description du groupe")
	private String description;
	private List<PublicationDTO> listPublications;
	private boolean institutionnel;

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
		return name + " : " + description;
	}

	/**
	 * @return the listPublications
	 */
	public List<PublicationDTO> getListPublications() {
		if (listPublications == null) {
			this.listPublications = new ArrayList<PublicationDTO>();
		}
		return listPublications;
	}

	/**
	 * @param listPublications
	 *            the listPublications to set
	 */
	public void setListPublications(List<PublicationDTO> listPublications) {
		this.listPublications = listPublications;
	}



	public boolean isInstitutionnel() {
		return institutionnel;
	}

	public void setInstitutionnel(boolean institutionnel) {
		this.institutionnel = institutionnel;
	}

	/**
	 * @return the animateurs
	 */
	public List<EnseignantDTO> getAnimateurs() {
		if (animateurs == null) {
			this.animateurs = new ArrayList<EnseignantDTO>();
		}
		return animateurs;
	}

	/**
	 * @param animateurs the animateurs to set
	 */
	public void setAnimateurs(List<EnseignantDTO> animateurs) {
		this.animateurs = animateurs;
	}


	public AncienEtudiantDTO getAnimateurGroupeNonInstit() {
		return animateurGroupeNonInstit;
	}

	public void setAnimateurGroupeNonInstit(AncienEtudiantDTO animateurGroupeNonInstit) {
		this.animateurGroupeNonInstit = animateurGroupeNonInstit;
	}

	public List<AncienEtudiantDTO> getAncienEtudiants() {
		if (ancienEtudiants == null) {
			this.ancienEtudiants = new ArrayList<AncienEtudiantDTO>();
		}
		return ancienEtudiants;
	}

	public void setAncienEtudiants(List<AncienEtudiantDTO> ancienEtudiants) {
		this.ancienEtudiants = ancienEtudiants;
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

	public List<EnseignantDTO> getEnseignants() {
		if (enseignants == null) {
			this.enseignants = new ArrayList<EnseignantDTO>();
		}
		return enseignants;
	}

	public void setEnseignants(List<EnseignantDTO> enseignants) {
		this.enseignants = enseignants;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupeDTO other = (GroupeDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

}
