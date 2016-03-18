package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Moderateur;
import ipint15.glp.domain.entities.Publication;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class GroupeImpl implements GroupeRemote {
	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}

	@Override
	public GroupeDTO getGroupeDTOById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		GroupeDTO gDTO = g.toGroupeDTO();
		return gDTO;
	}

	@Override
	public GroupeDTO createGroupe(String name, String description) {
		Groupe g = new Groupe();
		g.setName(name);
		g.setDescription(description);
		g.setInstitutionnel(true);
		em.persist(g);

		GroupeDTO gDTO = g.toGroupeDTO();
		return gDTO;
	}

	@Override
	public void editGroupe(int id, String newName, String description) {
		Groupe g = getGroupeById(id);
		g.setName(newName);
		g.setDescription(description);

		em.merge(g);

	}

	@Override
	public void editGroupe(int id, String description) {
		Groupe g = getGroupeById(id);
		g.setDescription(description);

		em.merge(g);

	}

	@Override
	public List<GroupeDTO> getAllGroupe() {
		List<Groupe> gList = em.createQuery("select o from Groupe o", Groupe.class).getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();
		for (Groupe g : gList) {
			gDTOList.add(g.toGroupeDTO());
		}

		return gDTOList;

	}

	@Override
	public int getGroupeSize(int id) {
		Groupe g = getGroupeById(id);
		int size = g.getAncienEtudiants().size();
		size += g.getEnseignant().size();
		size += g.getEtudiants().size();

		return size;
	}

	@Override
	public int getAnimByGroupeSize(int id) {
		Groupe g = getGroupeById(id);
		return g.getAnimateur().size();
	}

	@Override
	public boolean removeGroupe(int id) {
		Groupe g = getGroupeById(id);
		List<Moderateur> listeModo = g.getModerateurs();
		List<AncienEtudiant> listeAncienEtu = g.getAncienEtudiants();
		List<Etudiant> listeEtu = g.getEtudiants();
		List<Enseignant> listeEnseign = g.getEnseignant();
		if ((listeModo.isEmpty()) && (listeAncienEtu.isEmpty()) && (listeEtu.isEmpty()) && (listeEnseign.isEmpty())) {
			em.remove(g);
			return true;
		}
		return false;

	}

	public void removePublication(int id) {
		Publication publi = getPublicationById(id);
		List<AncienEtudiant> mesAnciensEtudiants = em.createNamedQuery("getListAncien", AncienEtudiant.class)
				.getResultList();

		for (AncienEtudiant anc : mesAnciensEtudiants) {
			EtudiantProfil ep = anc.getProfil();
			List<Publication> publicationsP = ep.getMesPublications();
			for (Publication p : publicationsP) {

				if (p.getId() == publi.getId()) {
					p.setGroupe(null);
					p.setProfil(null);
					List<Publication> publics = ep.getMesPublications();
					publics.remove(p);
					ep.setPublications(publics);

					em.persist(ep);
					anc.setProfil(ep);
					em.persist(anc);
				}
			}

		}

		Groupe g = getGroupeById(publi.getGroupe().getId());
		List<Publication> publicationsP = g.getPublications();
		for (Publication p : publicationsP) {
			if (p.getId() == publi.getId()) {

				List<Publication> publics = g.getPublications();
				publics.remove(p);
				g.setPublications(publics);
				em.persist(g);

			}
		}
		em.remove(publi);

	}

	private Publication getPublicationById(int id) {

		Query q = em.createQuery("select p from Publication p WHERE p.id = :id");
		q.setParameter("id", id);
		Publication p = (Publication) q.getSingleResult();
		return p;
	}

	@Override
	public boolean removeGroupeNonInstit(int id, int idMembre) {
		Groupe g = getGroupeById(id);
		if (g.getAnimateurGroupeNonInstit().getId() == idMembre) {

			List<AncienEtudiant> lesAnciens = g.getAncienEtudiants();
			List<Enseignant> enseignants = g.getEnseignant();
			List<Publication> publications = g.getPublications();

			for (AncienEtudiant ancien : lesAnciens) {
				List<Groupe> lesGroupes = ancien.getLesGroupes();
				lesGroupes.remove(g);
				ancien.setLesGroupes(lesGroupes);
				em.persist(ancien);
			}

			for (Publication publi : publications) {
				removePublication(publi.getId());

			}

			for (Enseignant e : enseignants) {
				List<Groupe> lesGroupes = e.getGroupes();
				lesGroupes.remove(g);
				e.setGroupes(lesGroupes);
				em.persist(e);
			}

			g.setAncienEtudiants(null);
			g.setAnimateur(null);
			g.setAnimateurGroupeNonInstit(null);
			g.setEnseignant(null);
			g.setPublications(null);
			g.setModerateurs(null);
			em.remove(g);

			return true;
		}

		return false;

	}

	@Override
	public List<GroupeDTO> getGroupesOfAncienByIdAncien(int id) {
		List<Groupe> gList = em.createNamedQuery("getGroupesOfAncienByIdAncien", Groupe.class).setParameter("id", id)
				.getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();
		for (Groupe g : gList) {
			gDTOList.add(g.toGroupeDTO());
		}
		return gDTOList;
	}

	/**
	 * Renvoi le groupe avec la liste des membre
	 */
	@Override
	public GroupeDTO getGroupeDTOByIdWithMemberList(int id) {
		Groupe g = em.createNamedQuery("getGroupeById", Groupe.class).setParameter("id", id).getSingleResult();
		GroupeDTO gDTO = g.toGroupeDTO();
		List<AncienEtudiantDTO> listAe = new ArrayList<>();
		List<AncienEtudiant> list = g.getAncienEtudiants();
		System.out.println("List : " + list);
		System.out.println("Size List : " + list.size());
		for (AncienEtudiant ae : list) {
			listAe.add(ae.toEtudiantDTO());
		}
		gDTO.setAncienEtudiants(listAe);
		System.out.println("List DTO : " + gDTO.getAncienEtudiants());
		System.out.println("Size List DTO : " + gDTO.getAncienEtudiants().size());

		List<EnseignantDTO> listEnseignDTO = new ArrayList<>();
		List<Enseignant> listEnseign = g.getEnseignant();
		for (Enseignant e : listEnseign) {
			listEnseignDTO.add(e.toEnseignantDTO());
		}
		gDTO.setEnseignants(listEnseignDTO);

		listEnseignDTO = new ArrayList<>();
		listEnseign = g.getAnimateur();
		if (!listEnseign.isEmpty()) {
			for (Enseignant e : listEnseign) {
				listEnseignDTO.add(e.toEnseignantDTO());
			}
			gDTO.setAnimateurs(listEnseignDTO);
		} else {
			try {
				gDTO.setAnimateurGroupeNonInstit(g.getAnimateurGroupeNonInstit().toEtudiantDTO());
			} catch (Exception e) {
				// Pas d'anim !
			}
		}

		List<EtudiantDTO> listEtuDTO = new ArrayList<>();
		List<Etudiant> listEtu = g.getEtudiants();
		for (Etudiant e : listEtu) {
			listEtuDTO.add(e.toEtudiantDTO());
		}
		gDTO.setEtudiants(listEtuDTO);
		return gDTO;
	}

	@Override
	public List<GroupeDTO> getAllGroupeInstitutionnel() {
		List<Groupe> gList = em.createNamedQuery("getGroupesInstitutionnels", Groupe.class).getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();

		for (Groupe g : gList) {

			GroupeDTO gDTO = g.toGroupeDTO();
			List<AncienEtudiantDTO> listAe = new ArrayList<>();
			List<AncienEtudiant> list = g.getAncienEtudiants();
			for (AncienEtudiant ae : list) {
				listAe.add(ae.toEtudiantDTO());
			}
			gDTO.setAncienEtudiants(listAe);

			List<EnseignantDTO> listEnseignDTO = new ArrayList<>();
			List<Enseignant> listEnseign = g.getEnseignant();
			for (Enseignant e : listEnseign) {
				listEnseignDTO.add(e.toEnseignantDTO());
			}
			gDTO.setEnseignants(listEnseignDTO);

			listEnseignDTO = new ArrayList<>();
			listEnseign = g.getAnimateur();
			for (Enseignant e : listEnseign) {
				listEnseignDTO.add(e.toEnseignantDTO());
			}
			gDTO.setAnimateurs(listEnseignDTO);

			List<EtudiantDTO> listEtuDTO = new ArrayList<>();
			List<Etudiant> listEtu = g.getEtudiants();
			for (Etudiant e : listEtu) {
				listEtuDTO.add(e.toEtudiantDTO());
			}
			gDTO.setEtudiants(listEtuDTO);
			gDTOList.add(gDTO);
		}

		return gDTOList;
	}

	@Override
	public List<GroupeDTO> getAllGroupeNonInstitutionnel() {
		List<Groupe> gList = em.createNamedQuery("getGroupesNonInstitutionnels", Groupe.class).getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();
		for (Groupe g : gList) {
			gDTOList.add(g.toGroupeDTO());
		}

		return gDTOList;

	}

	@Override
	public List<GroupeDTO> getAllMesGroupesNonInstitutionnel(AncienEtudiantDTO ancien) {

		List<Groupe> gList = em.createNamedQuery("getGroupesNonInstitutionnels", Groupe.class).getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();
		System.out.println("Taille de la liste" + gList.size());
		for (Groupe g : gList) {
			if (g.getAnimateurGroupeNonInstit() != null) {
				System.out.println(
						"id de l'animateur du groupe quand il existe est :" + g.getAnimateurGroupeNonInstit().getId());
				if (g.getAnimateurGroupeNonInstit().getId() == ancien.getId()) {
					gDTOList.add(g.toGroupeDTO());
				}
			}
		}

		return gDTOList;
	}

	public boolean peutRejoindreGroupeAncien(int idGroupe, AncienEtudiantDTO eDTO) {
		// TODO Auto-generated method
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<AncienEtudiantDTO> ancienEtdiantDTO = groupeAvecListeDesMembre.getAncienEtudiants();
		boolean a = true;
		Groupe g = getGroupeById(idGroupe);

		// if(groupeAvecListeDesMembre.isInstitutionnel() == false) {

		if (membreExistInListGroupe(idGroupe, eDTO.getId())) {
			a = false;
		} else {

			if (idGroupe != eDTO.getGroupe().getId()) {

				if (g.getAnimateurGroupeNonInstit() != null) {
					if (g.getAnimateurGroupeNonInstit().getId() == eDTO.getId()) {
						a = false;
					}

					else {
						boolean peurej = true;
						for (AncienEtudiantDTO ancien : ancienEtdiantDTO) {
							if (ancien.getId() == eDTO.getId()) {
								System.out.println("je fais deja partie du groupe donc je peux publier dans le groupe");
								peurej = false;
							}
						}
						a = peurej;
					}
				}
			}

			else {
				a = false;
			}
		}
		return a;
	}

	public boolean peutRejoindreGroupe(int idGroupe, int idMembre) {
		// TODO Auto-generated method
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<AncienEtudiantDTO> ancienEtdiantDTO = groupeAvecListeDesMembre.getAncienEtudiants();
		boolean a = true;
		Groupe g = getGroupeById(idGroupe);

		if (g.getAnimateurGroupeNonInstit() != null) {
			if (g.getAnimateurGroupeNonInstit().getId() == idMembre) {
				a = false;
			}

			else {
				boolean peurej = true;
				for (AncienEtudiantDTO ancien : ancienEtdiantDTO) {
					if (ancien.getId() == idMembre) {
						System.out.println("je fais deja partie du groupe donc je peux publier dans le groupe");
						peurej = false;
					}
				}
				a = peurej;
			}
		}
		return a;
	}

	public GroupeDTO createGroupe(String name, String description, boolean isInstitutionnel) {
		Groupe g = new Groupe();
		g.setName(name);
		g.setDescription(description);
		g.setInstitutionnel(isInstitutionnel);

		em.persist(g);

		GroupeDTO gDTO = g.toGroupeDTO();
		return gDTO;
	}

	@Override
	public boolean membreExistInListGroupe(int idGroupe, int idMembre) {
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<AncienEtudiantDTO> ancienEtdiantDTO = groupeAvecListeDesMembre.getAncienEtudiants();
		boolean a = false;
		for (AncienEtudiantDTO ancien : ancienEtdiantDTO) {
			if (ancien.getId() == idMembre) {
				a = true;
			}
		}

		return a;
	}

	@Override
	public boolean membreEtudiantExistInListGroupe(int idGroupe, int idMembre) {
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<EtudiantDTO> etudiantDTO = groupeAvecListeDesMembre.getEtudiants();
		boolean a = false;
		for (EtudiantDTO etu : etudiantDTO) {
			if (etu.getId() == idMembre) {
				a = true;
			}
		}
		return a;
	}

	@Override
	public boolean membreEnseignantExistInListGroupe(int idGroupe, int idMembre) {
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<EnseignantDTO> enseignantDTO = groupeAvecListeDesMembre.getEnseignants();
		boolean a = false;
		for (EnseignantDTO prof : enseignantDTO) {
			if (prof.getId() == idMembre) {
				a = true;
			}
		}

		return a;
	}

	@Override
	public boolean peutQuitterGroupeAncien(int idGroupe, AncienEtudiantDTO eDTO) {
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		Groupe g = getGroupeById(idGroupe);
		boolean a = false;
		// if(groupeAvecListeDesMembre.isInstitutionnel() == false){

		if (g.getAnimateurGroupeNonInstit() != null) {
			System.out.println("l'animateur n'est pas vide");
			if (g.getAnimateurGroupeNonInstit().getId() == eDTO.getId()) { // l'animateur
																			// c
																			// moi
				System.out.println("L'animateur c moi " + g.getAnimateurGroupeNonInstit().getId() + " == "
						+ eDTO.getId() + "je ne peux pas quitter le groupe");
				a = false;
			} else { // l'animateur c pas moi

				if (membreExistInListGroupe(idGroupe, eDTO.getId()) == true) {
					System.out.println(
							"l'animateur c pas moi donc je peix et je fais partie du groupe quitter le groupe");
					a = true;
				} else {
					System.out.println(
							"l'animateur c pas moi, je ne fais pas partie du groupe je ne peux pas le quitter");
					a = false;
				}
			}

		} else { // pas d'annimateur donc c un grouope institutionnel

			if (membreExistInListGroupe(idGroupe, eDTO.getId()) == true) {

				if (idGroupe == eDTO.getGroupe().getId()) {
					a = false;
				} else {

					System.out.println(
							"l'animateur c pas moi donc je peix et je fais partie du groupe quitter le groupe");
					a = true;
				}
			} else {
				System.out.println("l'animateur c pas moi, je ne fais pas partie du groupe je ne peux pas le quitter");
				a = false;
			}

		}
		/*
		 * } else{ a = false; }
		 */
		System.out.println("peut quitter le groupe" + a);
		return a;
	}

	@Override
	public boolean peutRejoindreGroupeEtudiant(int idGroupe, EtudiantDTO eDTO) {
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<EtudiantDTO> etudiants = groupeAvecListeDesMembre.getEtudiants();
		boolean a = true;
		Groupe g = getGroupeById(idGroupe);
		if (membreExistInListGroupe(idGroupe, eDTO.getId())) {
			a = false;
		} else {
			if (idGroupe != eDTO.getGroupe().getId()) {
				if (g.getAnimateurGroupeNonInstit() != null) {
					if (g.getAnimateurGroupeNonInstit().getId() == eDTO.getId()) {
						a = false;
					} else {
						boolean peurej = true;
						for (EtudiantDTO etu : etudiants) {
							if (etu.getId() == eDTO.getId()) {
								System.out.println("je fais deja partie du groupe donc je peux publier dans le groupe");
								peurej = false;
							}
						}
						a = peurej;
					}
				}
			} else {
				a = false;
			}
		}
		return a;
	}

	@Override
	public boolean peutRejoindreGroupeEnseignant(int idGroupe, EnseignantDTO eDTO) {
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<AncienEtudiantDTO> ancienEtdiantDTO = groupeAvecListeDesMembre.getAncienEtudiants();
		boolean a = true;
		Groupe g = getGroupeById(idGroupe);
		if (membreExistInListGroupe(idGroupe, eDTO.getId())) {
			a = false;
		} else {
			/// if(idGroupe != eDTO.getGroupe().getId()){
			if (g.getAnimateurGroupeNonInstit() != null) {
				if (g.getAnimateurGroupeNonInstit().getId() == eDTO.getId()) {
					a = false;
				} else {
					boolean peurej = true;
					for (AncienEtudiantDTO ancien : ancienEtdiantDTO) {
						if (ancien.getId() == eDTO.getId()) {
							System.out.println("je fais deja partie du groupe donc je peux publier dans le groupe");
							peurej = false;
						}
					}
					a = peurej;
				}
			}
			// }
			// else{
			// a = false;
			// }
		}
		return a;
	}

	@Override
	public boolean peutQuitterGroupeEtudiant(int idGroupe, EtudiantDTO eDTO) {
		
		if(eDTO.getGroupe() != null){
			return eDTO.getGroupe().getId() != idGroupe;
		}
		Groupe g = em.find(Groupe.class, idGroupe);
		Etudiant e = em.find(Etudiant.class, eDTO.getId());
		return g.getId() != e.getGroupe().getId();
	}

}
