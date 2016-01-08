package ipint15.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class GroupeImpl implements GroupeRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "ipint.ejb.personbean";
	private static EntityManagerFactory factory;

	private Groupe getGroupeById(int id) {
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		return g;
	}

	@Override
	public GroupeDTO createGroupe(String name) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		Groupe g = new Groupe();
		g.setName(name);

		em.persist(g);

		GroupeDTO gDTO = g.toGroupeDTO();
		return gDTO;

	}

	@Override
	public void editGroupe(int id, String newName) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		Groupe g = getGroupeById(id);
		g.setName(newName);

		em.merge(g);

	}

	@Override
	public List<GroupeDTO> getAllGroupe() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		Query q = em.createQuery("select o from Groupe o");
		List<Groupe> gList = (List<Groupe>) q.getResultList();
		List<GroupeDTO> gDTOList = new ArrayList<GroupeDTO>();
		for(Groupe g : gList) {
			gDTOList.add(g.toGroupeDTO());
		}
		return gDTOList;
	}

	@Override
	public void removeGroupe(int id) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		Groupe g = getGroupeById(id);
		em.remove(g);
	}

}
