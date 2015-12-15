package ipint15.glp.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.PublicationDTO;

	@Entity
	@Table(name = "PUBLICATION")
	public class Publication {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
	   
	    @ManyToOne
	    private EtudiantProfil profil ;
	    
	    private String titre;

	    private String message;
	    
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
		public PublicationDTO toPublicationDTO() {
			PublicationDTO cDTO = new PublicationDTO();
			cDTO.setId(this.id);
			cDTO.setTitre(this.titre);
			cDTO.setMessage(this.message);
			return cDTO;
		}
		@Override
		public String toString() {
			return "Competence [id=" + id + ", profil="  + ((profil!=null) ? "oui" : "non") + ", libelle=" + titre + ", message ="+message +"]";
		}
}
