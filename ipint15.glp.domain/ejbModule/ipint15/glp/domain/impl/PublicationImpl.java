package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.PublicationRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Publication;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class PublicationImpl implements PublicationRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	// @Inject
	// protected AncienEtudiantCatalogRemote etudiantBean;

	public PublicationImpl() {
		super();
	}

	private AncienEtudiant getAncienEtudiantByMail(String mail) {

		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();

		return e;
	}

	private Etudiant getEtudiantByMail(String mail) {

		Query q = em.createQuery("select o from Etudiant o WHERE o.mail = :mail");
		q.setParameter("mail", mail);
		Etudiant e = (Etudiant) q.getSingleResult();

		return e;
	}
	private Enseignant getEnseignantByMail(String mail) {

		Query q = em.createQuery("select o from Enseignant o WHERE o.mail = :mail");
		q.setParameter("mail", mail);
		Enseignant e = (Enseignant) q.getSingleResult();

		return e;
	}

	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}

	private boolean isAnimateurGroupeAncienEtudiant(int idGroupe,int idAncien){
		Groupe g = getGroupeById(idGroupe);
		AncienEtudiant ae = g.getAnimateurGroupeNonInstit();
		if (ae ==null){
			return false;
		}
		if (ae.getId() == idAncien){
			return true;
		}
		else {
			return false;
		}


	}

	@Override
	public List<PublicationDTO> getMyPublications(AncienEtudiantDTO eDTO, int idGroupe) {
		// TODO
		AncienEtudiant e = getAncienEtudiantByMail(eDTO.getEmail());
		// TODO gérer le cas si e = null
		List<Publication> mesPublications;
		if (idGroupe == -2) {
			// Recherche des publications pour tout le monde + groupes
			mesPublications = e.getProfil().getMesPublications();

		} else if (idGroupe <= 0) {
			// Recherche des publications pour tout le monde
			mesPublications = em.createNamedQuery("selectAllPublicationPublicOfAncienEtudiant", Publication.class)
					.setParameter("idetu", eDTO.getId()).getResultList();
		} else {
			// Recherche pour un groupe
			mesPublications = em.createNamedQuery("selectAllPublicationGroupOfAncienEtudiant", Publication.class)
					.setParameter("idgroupe", idGroupe).setParameter("idetu", eDTO.getId()).getResultList();
		}
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication c : mesPublications) {
			PublicationDTO cDTO = ce.MappingProfilPublication(e.getProfil(), c);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;

	}

	@Override
	public List<PublicationDTO> getAllPublications(AncienEtudiantDTO eDTO, int idGroupe) {
		// TODO
		List<Publication> mesPublications;
		if (idGroupe == -2) {
			// Recherche des publications pour tout le monde + groupes
			mesPublications = em.createNamedQuery("selectAllPublicationForAncienEtudiant", Publication.class)
					.setParameter("idetu", eDTO.getId()).getResultList();
		} else if (idGroupe <= 0) {
			// Recherche des publications pour tout le monde
			mesPublications = em.createNamedQuery("selectAllPublicationPublic", Publication.class).getResultList();
		} else {
			// Recherche pour un groupe
			mesPublications = em.createNamedQuery("selectAllPublicationGroup", Publication.class)
					.setParameter("idgroupe", idGroupe).getResultList();
		}
		// mesPublications = em.createQuery("select p from Publication p order
		// by p.date desc").getResultList();
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication p : mesPublications) {
			//PublicationDTO cDTO = ce.MappingProfilPublication(p.getProfil(), p);
			//mesPublicationsDTO.add(cDTO);
			PublicationDTO cDTO = p.toPublicationDTO();
			if (p.getProfil() != null) {
				EtudiantProfilDTO epDTO = p.getProfil().toEtudiantProfilDTO();
				AncienEtudiantDTO aeDTO = p.getProfil().getEtudiant().toEtudiantDTO();
				epDTO.setEtudiant(aeDTO);
				cDTO.setProfil(epDTO);
				mesPublicationsDTO.add(cDTO);
			}
			if (p.getEtudiant() != null) {
				EtudiantDTO eDTO2 = p.getEtudiant().toEtudiantDTO();
				cDTO.setEtudiant(eDTO2);
				mesPublicationsDTO.add(cDTO);
			}
			if (p.getEnseignant() != null) {
				EnseignantDTO eDTO2 = p.getEnseignant().toEnseignantDTO();
				cDTO.setEnseignant(eDTO2);
				mesPublicationsDTO.add(cDTO);
			}
		}
		return mesPublicationsDTO;

	}

	@Override
	public void addPublication(AncienEtudiantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe) {
		AncienEtudiant e = getAncienEtudiantByMail(eDTO.getEmail());
		Publication c = new Publication();
		if (groupe != null) {
			Groupe g = getGroupeById(groupe.getId());
			c.setGroupe(g);
		}

		// TODO gérer cas si e = null
		c.setTitre(titre);
		c.setMessage(message);
		c.setDate(date);
		c.setPublic(isPublic);



		if (groupe != null && isAnimateurGroupeAncienEtudiant(groupe.getId(), e.getId())){
			c.setPostByAnim(true);
		}
		else{
			c.setPostByAnim(false);
		}



		if (groupe != null && !groupe.isInstitutionnel()){
			if (isAnimateurGroupeAncienEtudiant(groupe.getId(), e.getId())){
				System.out.println("je passe ici");
				c.setPostByAnim(true);
			}
			else{
				System.out.println("je passe la");
				c.setPostByAnim(false);
			}
		}
		
		

		EtudiantProfil ep = e.getProfil();
		ep.getMesPublications().add(c);
		c.setProfil(ep);
		em.persist(c);
		em.merge(ep);
		em.merge(e);

	}

	@Override
	public List<PublicationDTO> getAllGroupPublications(int idGroupe, int idUtilisateur, String typeCompte) {
		// TODO Possiblement a modifier selon les regles de publication
		List<Publication> mesPublications;
		Groupe groupe = em.find(Groupe.class, idGroupe);
		GroupeDTO groupeDTO = null;
		if (idGroupe == -2) {
			//TODO Recherche des publications pour tout le monde + groupes
			if("ancien".equals(typeCompte)){
				mesPublications = em.createNamedQuery("selectAllPublicationForAncienEtudiant", Publication.class)
					.setParameter("idetu", idUtilisateur).getResultList();
			}else if("prof".equals(typeCompte)){
				mesPublications = em.createNamedQuery("selectAllPublicationForEnseignant", Publication.class)
						.setParameter("idenseignant", idUtilisateur).getResultList();
			}else if ("etudiant".equals(typeCompte)){
				mesPublications = em.createNamedQuery("selectAllPublicationForEtudiant", Publication.class)
						.setParameter("idetu", idUtilisateur).getResultList();
			}else{
				mesPublications = em.createNamedQuery("selectAllPublicationPublic", Publication.class).getResultList();
			}
		} else if (idGroupe <= 0) {
			// Recherche des publications pour tout le monde
			mesPublications = em.createNamedQuery("selectAllPublicationPublic", Publication.class).getResultList();
		} else {
			// Recherche pour un groupe
			mesPublications = em.createNamedQuery("selectAllPublicationGroup", Publication.class)
					.setParameter("idgroupe", idGroupe).getResultList();
			groupeDTO = groupe.toGroupeDTO();
		}
		
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication c : mesPublications) {
			System.out.println("Test isAnim "+c.isPostByAnim());
			PublicationDTO cDTO = c.toPublicationDTO();
			if (c.getProfil() != null) {
				EtudiantProfilDTO epDTO = c.getProfil().toEtudiantProfilDTO();
				AncienEtudiantDTO aeDTO = c.getProfil().getEtudiant().toEtudiantDTO();
				epDTO.setEtudiant(aeDTO);
				cDTO.setProfil(epDTO);
				if(c.getGroupe() != null){
					cDTO.setGroupeDTO(c.getGroupe().toGroupeDTO());
				}
				mesPublicationsDTO.add(cDTO);
			}
			if (c.getEtudiant() != null) {
				EtudiantDTO eDTO = c.getEtudiant().toEtudiantDTO();
				cDTO.setEtudiant(eDTO);
				if(c.getGroupe() != null){
					cDTO.setGroupeDTO(c.getGroupe().toGroupeDTO());
				}
				mesPublicationsDTO.add(cDTO);
			}
			if (c.getEnseignant() != null) {
				EnseignantDTO eDTO = c.getEnseignant().toEnseignantDTO();
				cDTO.setEnseignant(eDTO);
				if(c.getGroupe() != null){
					cDTO.setGroupeDTO(c.getGroupe().toGroupeDTO());
				}
				mesPublicationsDTO.add(cDTO);
			}
		}
		return mesPublicationsDTO;
	}

	@Override
	public List<PublicationDTO> getAllPublicationsEtudiant(EtudiantDTO eDTO, int idGroupe) {
		List<Publication> mesPublications = new ArrayList<Publication> ();
		if (idGroupe == -2) {
			// Recherche des publications pour tout le monde + groupes
			mesPublications = em.createNamedQuery("selectAllPublicationForEtudiant", Publication.class)
					.setParameter("idetu", eDTO.getId()).getResultList();
		} else if (idGroupe <= 0) {
			// Recherche des publications pour tout le monde
			mesPublications = em.createNamedQuery("selectAllPublicationPublic", Publication.class).getResultList();
		} else {
			// Recherche pour un groupe
			mesPublications = em.createNamedQuery("selectAllPublicationGroup", Publication.class)
					.setParameter("idgroupe", idGroupe).getResultList();
		}

		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication p : mesPublications) {
			PublicationDTO cDTO = ce.MappingEtudiantPublication(p.getEtudiant(), p);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;

	}

	@Override
	public List<PublicationDTO> getMyPublicationsEtudiant(EtudiantDTO eDTO, int idGroupe) {
		Etudiant e = getEtudiantByMail(eDTO.getMail());
		// TODO gérer le cas si e = null
		List<Publication> mesPublications;
		if (idGroupe == -2) {
			// Recherche des publications pour tout le monde + groupes
			mesPublications = e.getMesPublications();

		} else if (idGroupe <= 0) {
			// Recherche des publications pour tout le monde
			mesPublications = em.createNamedQuery("selectAllPublicationPublicOfEtudiant", Publication.class)
					.setParameter("idetu", eDTO.getId()).getResultList();
		} else {
			// Recherche pour un groupe
			mesPublications = em.createNamedQuery("selectAllPublicationGroupOfEtudiant", Publication.class)
					.setParameter("idgroupe", idGroupe).setParameter("idetu", eDTO.getId()).getResultList();
		}
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication c : mesPublications) {
			PublicationDTO cDTO = ce.MappingEtudiantPublication(e, c);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;
	}

	@Override
	public void addPublicationEtudiant(EtudiantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe) {
		Etudiant e = getEtudiantByMail(eDTO.getMail());
		Publication c = new Publication();
		if (groupe != null) {
			Groupe g = getGroupeById(groupe.getId());
			c.setGroupe(g);
		}
		// TODO gérer cas si e = null
		c.setTitre(titre);
		c.setMessage(message);
		c.setDate(date);
		c.setPublic(isPublic);
		e.getMesPublications().add(c);
		c.setEtudiant(e);
		em.persist(c);
		em.merge(e);

	}

	@Override
	public List<PublicationDTO> getAllPublicationsEnseignant(EnseignantDTO eDTO, int idGroupe) {
		List<Publication> mesPublications = new ArrayList<Publication> ();
		if (idGroupe == -2) {
			// Recherche des publications pour tout le monde + groupes
			mesPublications = em.createNamedQuery("selectAllPublicationForEnseignant", Publication.class)
					.setParameter("idenseignant", eDTO.getId()).getResultList();
		} else if (idGroupe <= 0) {
			// Recherche des publications pour tout le monde
			mesPublications = em.createNamedQuery("selectAllPublicationPublic", Publication.class).getResultList();
		} else {
			// Recherche pour un groupe
			mesPublications = em.createNamedQuery("selectAllPublicationGroup", Publication.class)
					.setParameter("idgroupe", idGroupe).getResultList();
		}

		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication p : mesPublications) {
			PublicationDTO cDTO = ce.MappingEnseignantPublication(p.getEnseignant(), p);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;
	}

	@Override
	public List<PublicationDTO> getMyPublicationsEnseignant(EnseignantDTO eDTO, int idGroupe) {
		Enseignant e = getEnseignantByMail(eDTO.getMail());
		List<Publication> mesPublications;
		if (idGroupe == -2) {
			// Recherche des publications pour tout le monde + groupes
			mesPublications = e.getMesPublications();

		} else if (idGroupe <= 0) {
			// Recherche des publications pour tout le monde
			mesPublications = em.createNamedQuery("selectAllPublicationPublicOfEnseignant", Publication.class)
					.setParameter("idenseignant", eDTO.getId()).getResultList();
		} else {
			// Recherche pour un groupe
			mesPublications = em.createNamedQuery("selectAllPublicationGroupOfEnseignant", Publication.class)
					.setParameter("idgroupe", idGroupe).setParameter("idenseignant", eDTO.getId()).getResultList();
		}
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication c : mesPublications) {
			PublicationDTO cDTO = ce.MappingEnseignantPublication(e, c);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;
	}

	@Override
	public void addPublicationEnseignant(EnseignantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe) {
		Enseignant e = getEnseignantByMail(eDTO.getMail());
		Publication c = new Publication();
		if (groupe != null) {
			Groupe g = getGroupeById(groupe.getId());
			c.setGroupe(g);
		}
		// TODO gérer cas si e = null
		c.setTitre(titre);
		c.setMessage(message);
		c.setDate(date);
		c.setPublic(isPublic);
		e.getMesPublications().add(c);
		c.setEnseignant(e);
		em.persist(c);
		em.merge(e);

	}

}
