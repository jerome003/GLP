package ipint15.glp.domain.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.domain.entities.Etudiant;
import ipint15.glp.domain.entities.EtudiantProfil;
import ipint15.glp.domain.impl.EtudiantCatalogImpl;
import ipint15.glp.domain.util.Conversion;

public class EtudiantCatalogImplTest {

	@PersistenceContext
	EntityManager em;

	Conversion ce = new Conversion();
	
	private EtudiantCatalogImpl etuCata = new EtudiantCatalogImpl();


	private static final String PERSISTENCE_UNIT_NAME = "ipint.ejb.personbean";
	private static EntityManagerFactory factory;
	@Before
	public void setUp(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query queryDelete = em.createQuery("delete from Etudiant o where o.prenom = 'paul'");
		queryDelete.executeUpdate();
		Etudiant etudiant = new Etudiant();
		etudiant.setPrenom("paul");
		etudiant.setNom("dupont");
		etudiant.setCivilite(null);
		etudiant.setPassword("password");
		etudiant.setEmail("paul@gmail.com");
		etudiant.setNumTelephone("0606060606");
		etudiant.setNaissance(new Date());
		etudiant.setVilleActu("Paris");
		etudiant.setPosteActu("Dev");
		etudiant.setNomEntreprise("MIAGE CORP");
		etudiant.setDiplome("MIAGE");
		etudiant.setAnneeDiplome(2000);
		EtudiantProfil ep = new EtudiantProfil();
		ep.setEtudiant(etudiant);
		etudiant.setProfil(ep);
		em.persist(etudiant);
		em.persist(ep);
		tx.commit();
	}
	
	@After
	public void tearDown(){
		em.close();
		factory.close();
	}
	@Test
	public void testGetEtudiantByMail() {
		em = factory.createEntityManager();
		String email = "paul@gmail.com";
		Query q = em.createQuery("select o from Etudiant o WHERE o.email = :email");

		q.setParameter("email",email);
		Etudiant e = (Etudiant) q.getSingleResult();
		System.out.println("azeaze ->"+e.getId()+" AZEAZE");
		
		assertEquals("paul",e.getPrenom());
		assertEquals("dupont",e.getNom());
		assertEquals("paul@gmail.com",e.getEmail());
		assertEquals(2000,e.getAnneeDiplome());

	}

	@Test
	public void testGetEtudiantById() {
		em = factory.createEntityManager();

	
		String email = "paul@gmail.com";
		Query q1 = em.createQuery("select o from Etudiant o WHERE o.email = :email");
		q1.setParameter("email",email);
		Etudiant e1 = (Etudiant) q1.getSingleResult();

		Query q = em.createQuery("select o from Etudiant o WHERE o.id = :id");
		q.setParameter("id", e1.getId() );
		System.out.println(e1.getId());
		Etudiant e = (Etudiant) q.getSingleResult();
		
		assertEquals("paul",e.getPrenom());
		assertEquals("dupont",e.getNom());
		assertEquals("paul@gmail.com",e.getEmail());
		assertEquals(2000,e.getAnneeDiplome());

	}

//	@Test
//	public void testCreateEtudiant() {
//		em = factory.createEntityManager();
//
//		EtudiantDTO etu = new EtudiantDTO();
//		etu = etuCata.createEtudiant("Henri", "Charles", Civilite.M, "henri@gmail.com","000000", 
//				"password", new Date(),"CP", "Lille", "", "Miage",
//				2004);
//		
//		assertEquals("Henri",etu.getPrenom());
//		assertEquals("Charles",etu.getNom());
//		assertEquals("henri@gmail.com",etu.getEmail());
//		assertEquals(2004,etu.getAnneeDiplome());
//
//	}

	@Test
	public void testGetEtudiant() {
		em = factory.createEntityManager();
		List<Etudiant> ps = em.createQuery("select o from Etudiant o").getResultList();

		EtudiantDTO etu = new EtudiantDTO();
		assertEquals("paul",etu.getPrenom());
		assertEquals("dupont",etu.getNom());
		assertEquals("paul@gmail.com",etu.getEmail());
		assertEquals(2000,etu.getAnneeDiplome());

	}
	
	
}
