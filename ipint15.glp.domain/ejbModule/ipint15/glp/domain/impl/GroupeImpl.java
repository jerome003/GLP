package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.domain.entities.AncienEtudiant;
import ipint15.glp.domain.entities.Enseignant;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.entities.Moderateur;
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
		return g.getAncienEtudiants().size();
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
		List<AncienEtudiant> list = em.createNamedQuery("getListAncienEtudiantByIdGroupe", AncienEtudiant.class).setParameter("id", id)
				.getResultList();
		for (AncienEtudiant ae : list /*g.getAncienEtudiants()*/) {
			System.out.println("zbra " + ae);
			listAe.add(ae.toEtudiantDTO());
		}
		gDTO.setEtudiants(listAe);
		return gDTO;
	}

	@Override
	public List<GroupeDTO> getAllGroupeInstitutionnel() {
		List<Groupe> gList = em.createNamedQuery("getGroupesInstitutionnels",Groupe.class).getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();
		for (Groupe g : gList) {
			gDTOList.add(g.toGroupeDTO());
		}

		return gDTOList;
	}

	@Override
	public boolean membreExistInListGroupe(int idGroupe, int idMembre) {
		// TODO Auto-generated method stub
		GroupeDTO groupeAvecListeDesMembre = getGroupeDTOByIdWithMemberList(idGroupe);
		List<AncienEtudiantDTO> ancienEtdiantDTO = groupeAvecListeDesMembre.getEtudiants();
		boolean a = false;
		for (AncienEtudiantDTO ancien : ancienEtdiantDTO ){
			if(ancien.getId() == idMembre){
				a = true;
			}
		}
		if(!groupeAvecListeDesMembre.isInstitutionnel()){
			a = false;
		}
		return a;
	}



}
