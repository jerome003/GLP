package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EtudiantProfilDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.PublicationRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
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

	private AncienEtudiant getEtudiantByMail(String mail) {

		Query q = em.createQuery("select o from AncienEtudiant o WHERE o.email = :email");
		q.setParameter("email", mail);
		AncienEtudiant e = (AncienEtudiant) q.getSingleResult();

		return e;
	}

	private Groupe getGroupeById(int id) {
		System.out.println("passe dans getGroupe" + id);
//		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
//		q.setParameter("id", id);
//		Groupe g = (Groupe) q.getSingleResult();
		Groupe g = em.find(Groupe.class, id);
		System.out.println("passe apres le single result");
		return g;
	}

	@Override
	public List<PublicationDTO> getMyPublications(AncienEtudiantDTO eDTO, int idGroupe) {
		// TODO
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
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
			PublicationDTO cDTO = ce.MappingProfilPublication(p.getProfil(), p);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;

	}

	@Override
	public void addPublication(AncienEtudiantDTO eDTO, String titre, String message, Date date, boolean isPublic,
			GroupeDTO groupe) {
		System.out.println("addpublication publiImpl------------");
		AncienEtudiant e = getEtudiantByMail(eDTO.getEmail());
		Publication c = new Publication();
		List<Publication> publications = new ArrayList<Publication>();
	
		// TODO gérer cas si e = null
		c.setTitre(titre);
		c.setMessage(message);
		c.setDate(date);
		c.setPublic(isPublic);
		EtudiantProfil ep = e.getProfil();
		ep.getMesPublications().add(c);
		c.setProfil(ep);
		if (groupe != null) {
			System.out.println("passe dans groupe != null");
			Groupe g = getGroupeById(groupe.getId());
			System.out.println("groupe id"+g.getId());
			c.setGroupe(g);
			System.out.println("avant de faire appel a publication");
			publications = g.getPublications();
			if(publications == null){
				System.out.println("publication null ");
			}
			System.out.println("nbr de publications avant une nouvelle publi"+publications.size());
			publications.add(c);
			System.out.println("nbr de publications apres une nouvelle publi"+publications.size());
			g.setPublications(publications);
			em.merge(g);
		}
		em.persist(c);
		em.merge(ep);
		em.merge(e);

	}
	
	
	@Override
	public List<PublicationDTO> getAllGroupPublications(int idGroupe) {
		// TODO Possiblement a modifier selon les regles de publication
		List<Publication> mesPublications;
		mesPublications = em.createNamedQuery("selectAllPublicationGroup", Publication.class)
				.setParameter("idgroupe", idGroupe).getResultList();
		List<PublicationDTO> mesPublicationsDTO = new ArrayList<PublicationDTO>();
		for (Publication c : mesPublications) {
			PublicationDTO cDTO = c.toPublicationDTO();
			EtudiantProfilDTO epDTO = c.getProfil().toEtudiantProfilDTO();
			AncienEtudiantDTO aeDTO = c.getProfil().getEtudiant().toEtudiantDTO();
			epDTO.setEtudiant(aeDTO);
			cDTO.setProfil(epDTO);
			mesPublicationsDTO.add(cDTO);
		}
		return mesPublicationsDTO;
	}

	

}
