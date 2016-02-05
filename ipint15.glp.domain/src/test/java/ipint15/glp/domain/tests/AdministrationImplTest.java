package ipint15.glp.domain.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ipint15.glp.api.dto.AdminDTO;
import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;

public class AdministrationImplTest {

	private static InitialContext ctx;
	private static AdministrationRemote adminBean;
	private static GroupeRemote groupeBean;
	private static AncienEtudiantCatalogRemote etuBean;
	private static ModerateurDTO modo;
	private static ModerateurDTO moderateur;
	private static AdminDTO ad ;
	private static GroupeDTO groupe;
	private static AncienEtudiantDTO etu;
	private static AncienEtudiantDTO etu2;
	
	
	
	@BeforeClass
	public static  void setUp() throws NamingException{
		ctx= new InitialContext();
		adminBean = (AdministrationRemote) ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/AdministrationImpl");
		groupeBean = (GroupeRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/GroupeImpl");
		etuBean = (AncienEtudiantCatalogRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/EtudiantCatalogImpl");
		
		groupe= groupeBean.createGroupe("Miage","groupe miage de l'universite de lille 1");
		moderateur = adminBean.createModerateur("Mike", "Ross", "mike@gmail.com", "password");
		ad = adminBean.createAdmin("admin@admin.fr", "password");
		etu = etuBean.createEtudiant("Hervey", "Specter", Civilite.M, "hervey@gmail.com","00000000", "password", new Date(), "Sans emploi", "CP", "Paris", "CGI", "miage", 2008, groupe);
		etu2= etuBean.createEtudiant("Jack", "Dawson", Civilite.M, "jack@gmail.com", "0000000", "password", new Date(), "Sans emploi","Dev", "Lille", "Sopra", "miage", 2011, groupe);
		etuBean.createEtudiant("Rose", "Durand", Civilite.Mme, "rose@gmail.com","00000000", "password", new Date(), "Sans emploi","Dav", "Lille", "CGI", "miage", 2010, groupe);
		
	}
	
	@AfterClass
	public static void tearDown(){
		
	}
	
	@Test
	public void testCreateModerateur() {
		modo = adminBean.createModerateur("Jean", "Durant", "jean@gmail.com", "password");
		assertEquals("Jean", modo.getPrenom());
		assertEquals("Durant", modo.getNom());
		assertEquals("jean@gmail.com", modo.getEmail());
		
	}
	
	
	@Test
	public void testCreateAdmin () {
		AdminDTO admin = adminBean.createAdmin("admin1@admin.fr", "password");
		assertEquals("admin1@admin.fr", admin.getEmail());
		assertEquals("password", admin.getPassword());
	}
	
	@Test
	public void testGeneratePassword() {
		String psw = adminBean.generatePassword(8);
		assertNotNull(psw);
		
	}
	
	@Test
	public void testGetModerateurDTOById() {
		ModerateurDTO mod = adminBean.getModerateurDTOById(modo.getId());
		assertEquals(modo.getId(), mod.getId());
		assertEquals(modo.getNom(), mod.getNom());
		assertEquals(modo.getPrenom(), mod.getPrenom());
	}
	
	@Test
	public void testIsMailExistsForAdmin() {
		Boolean verif = adminBean.isMailExistsForAdmin("admin@admin.fr");
		Boolean verif2 = adminBean.isMailExistsForAdmin("blabla");
		assertTrue(verif);
		assertFalse(verif2);
	}
	
	@Test 
	public void testIsPasswordIsGoodForAdmin() {
		Boolean verif = adminBean.isPasswordIsGoodForAdmin("admin@admin.fr","password");
		Boolean verif2 = adminBean.isPasswordIsGoodForAdmin("admin@admin.fr", "blabla");
		Boolean verif3 = adminBean.isPasswordIsGoodForAdmin("blabla", "password");
		Boolean verif4 = adminBean.isPasswordIsGoodForAdmin("balbal", "balbal");
		assertTrue(verif);
		assertFalse(verif2);
		assertFalse(verif3);
		assertFalse(verif4);
	}
	
	@Test
	public void testConnexionAdmin() {
		Boolean verif = adminBean.connexionAdmin("admin@admin.fr", "password");
		Boolean verif2 = adminBean.connexionAdmin("admin@admin.fr", "balbal");
		assertTrue(verif);
		assertFalse(verif2);
		
	}
	
	@Test 
	public void testConnexionModerateur() {
		Boolean verif = adminBean.connexionModerateur("jean@gmail.com", "password");
		Boolean verif2 = adminBean.connexionModerateur("jean@gmail.com", "blabla");
		assertTrue(verif);
		assertFalse(verif2);
		
	}
	
//	@Test
//	public void testIsThereAnAdmin () {
//		Boolean verif = adminBean.isThereAnAdmin();
//		assertTrue(verif);
//	}
//	
	@Test 
	public void testAddGroupetoModo () {
		ModerateurDTO mod = adminBean.addGroupetoModo(moderateur.getId(), groupe);
		mod = adminBean.getModerateurDTOById(moderateur.getId());
		groupe = groupeBean.getGroupeDTOById(groupe.getId());
		assertEquals(mod.getNom(), moderateur.getNom());
		assertEquals(mod.getId(), moderateur.getId());
		// revoir la liste des groupes et des moderateurs
		
		
//		assertEquals(groupe.getModerateurs().get(0).getNom(),mod.getNom());
//		assertEquals(mod.getGroupes().get(0).getName(),groupe.getName());
		
	}
	
	@Test
	public void testGetAllModerateur() {
		List<ModerateurDTO> mod = adminBean.getAllModerateur();
		assertEquals(mod.get(0).getNom(),moderateur.getNom());
		assertEquals(mod.get(0).getPrenom(),moderateur.getPrenom());
		assertEquals(mod.get(0).getId(),moderateur.getId());
		assertEquals(mod.get(1).getNom(),modo.getNom());
		assertEquals(mod.get(1).getPrenom(),modo.getPrenom());
		assertEquals(mod.get(1).getId(),modo.getId());
		assertEquals(mod.size(),2);
	}
	
	@Test
	public void testGetAdmin() {
		AdminDTO a = adminBean.getAdmin("admin@admin.fr");
		assertEquals(a.getEmail(),ad.getEmail());
		assertEquals(a.getId(),ad.getId());
	}
	
	@Test
	public void testIsMailExistsForModerateur() {
		Boolean verif = adminBean.isMailExistsForModerateur("mike@gmail.com");
		Boolean verif2 = adminBean.isMailExistsForAdmin("blabla");
		assertTrue(verif);
		assertFalse(verif2);
	}
	
	@Test 
	public void testIsPasswordIsGoodForModerateur() {
		Boolean verif = adminBean.isPasswordIsGoodForModerateur("mike@gmail.com","password");
		Boolean verif2 = adminBean.isPasswordIsGoodForModerateur("mike@gmail.com", "blabla");
		Boolean verif3 = adminBean.isPasswordIsGoodForModerateur("blabla", "password");
		Boolean verif4 = adminBean.isPasswordIsGoodForModerateur("balbal", "balbal");
		assertTrue(verif);
		assertFalse(verif2);
		assertFalse(verif3);
		assertFalse(verif4);
	}
	
	@Test
	public void testGetModerateur() {
		ModerateurDTO m = adminBean.getModerateur("mike@gmail.com");
		assertEquals(m.getEmail(), moderateur.getEmail());
		assertEquals(m.getId(), moderateur.getId());
		assertEquals(m.getNom(), moderateur.getNom());
		assertEquals(m.getPrenom(), moderateur.getPrenom());
	}
	

	
	@Test
	public void testGetEtudiantsNonInscritByIdGroupe () {
		List<AncienEtudiantDTO> list = adminBean.getEtudiantsNonInscritByIdGroupe(groupe.getId());
		assertEquals(list.size(),1);
		assertEquals(list.get(0).getNom(),"Durand");
		
	}
	

	
	@Test
	public void testValidationInscription () {
		adminBean.validationInscription(etu);
		etu = etuBean.getEtudiant(etu.getId());
		System.out.println(etu.getValidation());
		// a voir
		//assertTrue(etu.getValidation());
	}
	
	@Test 
	public void testRefusInscription() {
		adminBean.refusInscription(etu2, groupe.getId(),"t'es nul");
		assertEquals(groupe.getEtudiants().size(),0);
		List<AncienEtudiantDTO> list = new ArrayList<AncienEtudiantDTO>();
		assertEquals(groupe.getEtudiants(),list);
		
	}
	
	
}
