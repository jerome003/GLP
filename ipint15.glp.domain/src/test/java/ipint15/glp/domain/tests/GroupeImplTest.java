package ipint15.glp.domain.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;


public class GroupeImplTest {


	private static InitialContext ctx;
	private static GroupeRemote groupBean;
	private static AncienEtudiantCatalogRemote etuBean ;
	private static GroupeDTO groupe;
	private static GroupeDTO groupe2;
	private static GroupeDTO groupe3;
	
	@BeforeClass
	public static void setUp() throws NamingException{
		ctx= new InitialContext();
		groupBean = (GroupeRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/GroupeImpl");
		etuBean = (AncienEtudiantCatalogRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/EtudiantCatalogImpl");
		groupe = groupBean.createGroupe("Miage","groupeMiage");
		groupe3 = groupBean.createGroupe("bidule","groupeBidule");
		
		etuBean.createEtudiant("Roberto", "Sanchez", Civilite.M, "roberto@gmail.com","00000000", "password",new Date(),"Sans emploi", "prof","Lille", "Université lille", "miage",1980, groupe );
		etuBean.createEtudiant("Steven", "Dupont", Civilite.M, "steven@gmail.com","000000", "password", new Date(),"Sans emploi", "CP", "Paris","Miage Corp", "miage",2006, groupe);
	}

	@AfterClass
	public static void tearDown(){
		
	}


	@Test
	public void testCreateGroupe() {
		
		groupe2 = groupBean.createGroupe("fil","groupeFil");
		assertEquals("fil",groupe2.getName());

	}
	
	@Test
	public void testGetGroupeDTOById () {
		GroupeDTO g = groupBean.getGroupeDTOById(groupe.getId());
		assertEquals(g.getName(),groupe.getName());
		assertEquals(g.getId(), groupe.getId());
	}

	@Test
	public void testEditeGroupe() {
		groupBean.editGroupe(groupe.getId(), "Miage_Lille","groupeMiageLille");
		groupe = groupBean.getGroupeDTOById(groupe.getId());
		assertEquals("Miage_Lille", groupe.getName());

	}	

	@Test
	public void testGetAllGroupe() {
		
		List<GroupeDTO> liste = groupBean.getAllGroupe();
		
		assertEquals("Miage",liste.get(0).getName());
		assertEquals("fil",liste.get(1).getName());
		//assertEquals("bidule",liste.get(1).getName());
		assertEquals(2,liste.size());

	}
	
	@Test
	public void testGroupeSize(){
		int nb = groupBean.getGroupeSize(groupe.getId());
		assertEquals( 2 , nb);
		int nb2 = groupBean.getGroupeSize(groupe2.getId());
		assertEquals( 0 , nb2);
	}

	@Test
	public void testRemoveGroupe() {
		groupBean.removeGroupe(groupe3.getId());
		
		List<GroupeDTO> liste = groupBean.getAllGroupe();
		assertEquals(2,liste.size());
		assertEquals("Miage",liste.get(0).getName());
		assertEquals("fil",liste.get(1).getName());


	}

}
