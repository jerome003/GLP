package ipint15.glp.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;


import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.domain.impl.EtudiantCatalogImpl;
import ipint15.glp.domain.impl.RechercheImpl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RechercheImplTest {
	
	@PersistenceContext
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "ipint.ejb.personbean";
	private static EntityManagerFactory factory;
	private EtudiantCatalogImpl etuCatalog = new EtudiantCatalogImpl();
	private RechercheImpl recherche = new RechercheImpl();

	
	@Before
	public void setUp(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		
		
	}
	
	@After
	public void tearDown(){
		em.close();
		factory.close();
	}
	
	@Test
	public void testRechercheEtudiant () {
		em = factory.createEntityManager();
		
		List<EtudiantDTO> list = new ArrayList<EtudiantDTO>();
		EtudiantDTO etu = etuCatalog.createEtudiant("paul","dupont", Civilite.M, "paul@gmail.com", "0000000",
			 "password", new Date(), "dev", "Lille", "Miage CORP", "Miage", 2003, null);
		EtudiantDTO etu1 = etuCatalog.createEtudiant("henry","charles", Civilite.M, "henry@gmail.com", "0000000",
				 "password", new Date(), "dev", "Lille", "Miage CORP", "Miage", 2003, null);
		list = recherche.rechercherEtudiant(etu.getNom());
		
		for (EtudiantDTO e : list) {
			assertEquals(e.getNom(), etu.getNom());
			assertEquals(e.getPrenom(), etu.getPrenom());
			assertEquals(e.getEmail(), etu.getEmail());
			assertEquals(e.getDiplome(), etu.getDiplome());
			
		}
		
		list = recherche.rechercherEtudiant(etu1.getNom());
		for (EtudiantDTO e : list) {
			assertEquals(e.getNom(), etu1.getNom());
			assertEquals(e.getPrenom(), etu1.getPrenom());
			assertEquals(e.getEmail(), etu1.getEmail());
			assertEquals(e.getDiplome(), etu1.getDiplome());
			
		}
		
	}

}
