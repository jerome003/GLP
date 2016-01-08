package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.domain.entities.Groupe;
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
	public GroupeDTO createGroupe(String name) {
		Groupe g = new Groupe();
		g.setName(name);

		em.persist(g);

		GroupeDTO gDTO = g.toGroupeDTO();
		return gDTO;

	}

	@Override
	public void editGroupe(int id, String newName) {
		Groupe g = getGroupeById(id);
		g.setName(newName);

		em.merge(g);

	}

	@Override
	public List<GroupeDTO> getAllGroupe() {
		List<Groupe> gList = em.createQuery("select o from Groupe o", Groupe.class).getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();
		System.out.println(gList);
		for(Groupe g : gList) {
			gDTOList.add(g.toGroupeDTO());
		}
	
		return gDTOList;
		
	}
	
	@Override
	public int getGroupeSize(int id) {
		Groupe g = getGroupeById(id);
		return g.getEtudiants().size();
	}

	@Override
	public void removeGroupe(int id) {
		Groupe g = getGroupeById(id);
		em.remove(g);
	}

}
