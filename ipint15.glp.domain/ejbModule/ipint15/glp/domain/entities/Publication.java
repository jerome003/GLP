package ipint15.glp.domain.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ipint15.glp.api.dto.PublicationDTO;

@Entity
@Table(name = "PUBLICATION")
@NamedQueries({
		@NamedQuery(name = "selectAllPublicationOfAncienEtudiant", query = "select o from Publication o WHERE o.profil.etudiant.id = :id order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationGroupOfAncienEtudiant", query = "select o from Publication o WHERE o.profil.etudiant.id = :idetu AND o.groupe.id = :idgroupe order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationOfEtudiant", query = "select o from Publication o WHERE o.etudiant.id = :id order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationGroupOfEtudiant", query = "select o from Publication o WHERE o.etudiant.id = :idetu AND o.groupe.id = :idgroupe order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationOfEnseignant", query = "select o from Publication o WHERE o.enseignant.id = :id order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationGroupOfEnseignant", query = "select o from Publication o WHERE o.enseignant.id = :idenseignant AND o.groupe.id = :idgroupe order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationGroup", query = "select o from Publication o WHERE o.groupe.id = :idgroupe order by o.date desc"),
		// select o from Publication o left join o.groupe.ancienEtudiants a
		// WHERE o.groupe = null OR a.id = :idetu order by o.date desc
		@NamedQuery(name = "selectAllPublicationForAncienEtudiant", query = "select o from Publication o WHERE o.groupe = null OR EXISTS (SELECT a FROM o.groupe.ancienEtudiants a WHERE a.id = :idetu) order by o.date desc"),
		//select o from Publication o WHERE o.groupe = null OR EXISTS (SELECT a FROM o.groupe.ancienEtudiants a WHERE a.id = :idetu) order by o.date desc
		//select o from Publication o WHERE o.groupe = null OR o.groupe IN (select g from Groupe g join g.ancienEtudiants a where a.id = :idetu) order by o.date desc
		@NamedQuery(name = "selectAllPublicationForEtudiant", query = "select o from Publication o WHERE o.groupe = null OR EXISTS (SELECT a FROM o.groupe.etudiants a WHERE a.id = :idetu) order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationForEnseignant", query = "select o from Publication o WHERE o.groupe = null OR EXISTS (SELECT a FROM o.groupe.enseignant a WHERE a.id = :idenseignant) order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationPublic", query = "select o from Publication o WHERE o.groupe = null order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationPublicOfAncienEtudiant", query = "select o from Publication o WHERE o.groupe = null AND o.profil.etudiant.id = :idetu order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationPublicOfEtudiant", query = "select o from Publication o WHERE o.groupe = null AND o.etudiant.id = :idetu order by o.date desc"),
		@NamedQuery(name = "selectAllPublicationPublicOfEnseignant", query = "select o from Publication o WHERE o.groupe = null AND o.enseignant.id = :idenseignant order by o.date desc")})
public class Publication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private EtudiantProfil profil;
	@ManyToOne
	private Etudiant etudiant;
	@ManyToOne
	private Enseignant enseignant;

	private String titre;

	private String message;

	@Temporal(TemporalType.TIME)
	private Date date;

	@ManyToOne
	private Groupe groupe;

	private boolean isPublic;
	
	private boolean postByAnim ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EtudiantProfil getProfil() {
		return profil;
	}

	public void setProfil(EtudiantProfil profil) {
		this.profil = profil;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public PublicationDTO toPublicationDTO() {
		PublicationDTO cDTO = new PublicationDTO();
		cDTO.setId(this.id);
		cDTO.setTitre(this.titre);
		cDTO.setMessage(this.message);
		cDTO.setDate(this.date);
		cDTO.setPostByAnim(this.postByAnim);
		return cDTO;
	}

	@Override
	public String toString() {
		return "Competence [id=" + id + ", profil=" + ((profil != null) ? "oui" : "non") + ", libelle=" + titre
				+ ", message =" + message + "]";
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public boolean isPostByAnim() {
		return postByAnim;
	}

	public void setPostByAnim(boolean postByAnim) {
		this.postByAnim = postByAnim;
	}
	
}
