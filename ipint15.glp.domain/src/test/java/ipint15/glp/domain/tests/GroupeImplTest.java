package ipint15.glp.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.domain.entities.Groupe;
import ipint15.glp.domain.impl.GroupeImpl;
import ipint15.glp.domain.util.Conversion;

public class GroupeImplTest {

	@PersistenceContext
	EntityManager em;

	Conversion ce = new Conversion();

	private GroupeImpl groupeImpl = new GroupeImpl();


	private static final String PERSISTENCE_UNIT_NAME = "ipint.ejb.personbean";
	private static EntityManagerFactory factory;

	@Before
	public void setUp(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Query queryDelete = em.createQuery("delete from Groupe ");
		queryDelete.executeUpdate();

		Groupe g = new Groupe();
		g.setName("groupe");
		em.persist(g);


		Groupe g1 = new Groupe();
		g1.setName("groupe1");
		em.persist(g1);


		tx.commit();
	}

	@After
	public void tearDown(){
		em.close();
		factory.close();
	}

	@Test
	public void testGetGroupeById() {
		em = factory.createEntityManager();
		int id = 101 ;
		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", id);
		Groupe g = (Groupe) q.getSingleResult();
		assertEquals("groupe",g.getName());

	}

	@Test
	public void testCreateGroupe() {
		em = factory.createEntityManager();

		GroupeDTO g = new GroupeDTO();
		g = groupeImpl.createGroupe("Miage");

		assertEquals("Miage",g.getName());


	}

//	@Test
//	public void testEditeGroupe() {
//		em = factory.createEntityManager();
//		em.getTransaction().begin();
//
//		Groupe g1 = new Groupe();
//		g1.setName("groupe_miage");
//		em.persist(g1);
//		em.getTransaction().commit();
//
//		System.out.println(g1.getName());
//		groupeImpl.editGroupe(g1.getId(),"Miage_Lille");
//		System.out.println(g1.getName());
//
//		assertEquals("Miage_Lille",g1.getName());
//
//
//	}	

	@Test
	public void testGetAllGroupe() {
		em = factory.createEntityManager();
		List<GroupeDTO> liste = new ArrayList<GroupeDTO>();
		liste = groupeImpl.getAllGroupe();

		assertEquals("groupe1",liste.get(1).getName());
		assertEquals("groupe",liste.get(0).getName());


	}

	@Test
	public void testRemoveGroupe() {
		em = factory.createEntityManager();
		em.getTransaction().begin();

		Groupe g1 = new Groupe();	
		g1.setName("groupe_miage");
		em.persist(g1);
		em.getTransaction().commit();

		groupeImpl.removeGroupe(g1.getId());

		Query q = em.createQuery("select o from Groupe o WHERE o.id = :id");
		q.setParameter("id", g1.getId());
		Groupe g = (Groupe) q.getSingleResult();
		System.out.println(g.getName());
		assertNull(g);



	}

}
