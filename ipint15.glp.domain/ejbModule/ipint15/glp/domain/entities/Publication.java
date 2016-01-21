package ipint15.glp.domain.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	    
		@Temporal(TemporalType.TIME)
		private Date date;
	    
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

		public PublicationDTO toPublicationDTO() {
			PublicationDTO cDTO = new PublicationDTO();
			cDTO.setId(this.id);
			cDTO.setTitre(this.titre);
			cDTO.setMessage(this.message);
			cDTO.setDate(this.date);
			return cDTO;
		}
		@Override
		public String toString() {
			return "Competence [id=" + id + ", profil="  + ((profil!=null) ? "oui" : "non") + ", libelle=" + titre + ", message ="+message +"]";
		}
}
