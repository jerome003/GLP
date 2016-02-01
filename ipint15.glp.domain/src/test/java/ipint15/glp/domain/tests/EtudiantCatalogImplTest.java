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


import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.CompetenceDTO;
import ipint15.glp.api.dto.EcoleDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.ExperienceDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.HobbieDTO;
import ipint15.glp.api.dto.PublicationDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;



public class EtudiantCatalogImplTest {


	private static InitialContext ctx;
	private static EtudiantCatalogRemote etuBean ;
	private static GroupeRemote groupBean;
	private static AncienEtudiantDTO etudiant;
	private static AncienEtudiantDTO etu;
	private static GroupeDTO groupe;
	private static GroupeDTO groupe2;
	
	@BeforeClass
	public static  void setUp() throws NamingException{
		ctx= new InitialContext();
		etuBean = (EtudiantCatalogRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/EtudiantCatalogImpl");
		groupBean = (GroupeRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/GroupeImpl");
		
		groupe = groupBean.createGroupe("miage","groupeMiage");
		groupe2 = groupBean.createGroupe("fil","groupeFil");
		etu =etuBean.createEtudiant("Tom", "Hardy", Civilite.M, "tom@gmail.com","000000", "password", new Date(), "Dev", "Lille", "Advens","miage", 2008, groupe2);
		
	}
	
	@AfterClass
	public static void tearDown(){
		
	}
	
	
	@Test
	public void testCreateEtudiant() {
		etudiant = etuBean.createEtudiant("Roberto", "Sanchez", Civilite.M, "roberto@gmail.com","00000000", "password", new Date(), "Dev","Lille", "CGI", "miage",2008, groupe);
		assertEquals("Roberto", etudiant.getPrenom());
		assertEquals("Sanchez", etudiant.getNom());
		assertEquals("roberto@gmail.com", etudiant.getEmail());
		assertEquals("miage", etudiant.getDiplome());
	}
	
	
	@Test
	public void testGetEtudiantByMail() {
		AncienEtudiantDTO etu = etuBean.getEtudiant("roberto@gmail.com");
		assertEquals(etudiant.getNom(), etu.getNom());
		assertEquals(etudiant.getPrenom(), etu.getPrenom());
		assertEquals(etudiant.getDiplome(), etu.getDiplome());
		assertEquals(etudiant.getVilleActu(), etu.getVilleActu());
	}
	
	@Test
	public void testGetEtudiantById () {
		AncienEtudiantDTO etu = etuBean.getEtudiant(etudiant.getId());
		assertEquals(etudiant.getNom(), etu.getNom());
		assertEquals(etudiant.getPrenom(), etu.getPrenom());
		assertEquals(etudiant.getDiplome(), etu.getDiplome());
		assertEquals(etudiant.getVilleActu(), etu.getVilleActu());
	}
	
	@Test
	public void testListEtudiant() {
		List<AncienEtudiantDTO> liste = etuBean.listEtudiant();
		assertEquals("Hardy",liste.get(0).getNom());
		assertEquals("Matisse",liste.get(1).getNom());
		assertEquals("Sanchez",liste.get(2).getNom());
		assertEquals(3,liste.size());
	}
	
	@Test
	public void testConnexion() {
		Boolean verif = etuBean.connexion("roberto@gmail.com", "password");
		assertTrue(verif);
		Boolean verif2 = etuBean.connexion("roberto@gmail.com", "blabla");
		assertFalse(verif2);
		
	}
	
	@Test
	public void testAddCompetence() {
		etuBean.addCompetence(etudiant, "Java", 1);
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<CompetenceDTO> c = etuBean.getCompetences(etudiant);
		assertEquals("Java", c.get(0).getLibelle());
		assertEquals(1, c.size());
	}
	
	
	@Test
	public void testGetCompetences () {
		etuBean.addCompetence(etudiant,"Oracle", 1);
		etuBean.addCompetence(etudiant, "J2EE", 1);
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<CompetenceDTO> liste = etuBean.getCompetences(etudiant);
		assertEquals("Java",liste.get(0).getLibelle());
		assertEquals("Oracle",liste.get(1).getLibelle());
		assertEquals("J2EE",liste.get(2).getLibelle());
		assertEquals(3,liste.size());
	}
	
	@Test
	public void testAddExperience() {
		
		etuBean.addExperience(etudiant, "Stage", "Miage Corp", "Lille", "Nord", "France","2012", "2016", "mission de stage : dev");
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<ExperienceDTO> e = etuBean.getExperiences(etudiant);

		assertEquals("Stage", e.get(4).getLibelle());
		assertEquals("Miage Corp", e.get(4).getEntreprise());
		assertEquals("Lille", e.get(4).getVille());
		assertEquals("Nord", e.get(4).getRegion());
		assertEquals("France", e.get(4).getPays());
		assertEquals("2012", e.get(4).getDebut());
		assertEquals("2016", e.get(4).getFin());
		assertEquals("mission de stage : dev", e.get(4).getDescription());
		assertEquals(5, e.size());
	}
	
	
	@Test
	public void testGetExperiences () {
		etuBean.addExperience(etudiant,"Stage2", "CapGemini","Lille", "Nord", "France", "2012", "2013", "mission gestion de projet");
		etuBean.addExperience(etudiant, "Stage3", "CGI", "Lille", "Nord", "France", "2015", "2015", "mission MOA");
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<ExperienceDTO> liste = etuBean.getExperiences(etudiant);
		assertEquals("Stage2",liste.get(0).getLibelle());
		assertEquals("CapGemini",liste.get(0).getEntreprise());
		assertEquals("Lille",liste.get(0).getVille());
		assertEquals("Nord",liste.get(0).getRegion());
		assertEquals("France",liste.get(0).getPays());
		assertEquals("2012",liste.get(0).getDebut());
		assertEquals("2013",liste.get(0).getFin());
		assertEquals("mission gestion de projet",liste.get(0).getDescription());
		
		
		
		assertEquals("Stage3",liste.get(1).getLibelle());
		assertEquals("CGI",liste.get(1).getEntreprise());
		assertEquals("Lille",liste.get(1).getVille());
		assertEquals("Nord",liste.get(1).getRegion());
		assertEquals("France",liste.get(1).getPays());
		assertEquals("2015",liste.get(1).getDebut());
		assertEquals("2015",liste.get(1).getFin());
		assertEquals("mission MOA",liste.get(1).getDescription());
	
	
		
		
		assertEquals(2,liste.size());
	}
	
	@Test
	public void testGetGroupe(){
		GroupeDTO g = etuBean.getGroupe(etudiant);
		assertEquals("miage", g.getName());
	}
	
	@Test
	public void testSetGroup () {
		etuBean.setGroupe(etudiant, groupe2);
		etudiant = etuBean.getEtudiant(etudiant.getId());
		GroupeDTO g = etuBean.getGroupe(etudiant);
		assertEquals("fil", g.getName());
		
	}
	
	@Test
	public void testAddHobbie() {
		etuBean.addHobbie(etudiant, "Cinema");
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<HobbieDTO> e = etuBean.getHobbies(etudiant);
		assertEquals("Cinema", e.get(0).getLibelle());
		assertEquals(1, e.size());
	}
	
	
	@Test
	public void testGetHobbies () {
		etuBean.addHobbie(etudiant,"Musique");
		etuBean.addHobbie(etudiant, "Jeu video");
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<HobbieDTO> liste = etuBean.getHobbies(etudiant);
		assertEquals("Musique",liste.get(1).getLibelle());
		assertEquals("Jeu video",liste.get(2).getLibelle());
		assertEquals(3,liste.size());
	}
	
	@Test
	public void testAddEcole() {
		etuBean.addEcole(etudiant, "Formation","Universite", "2012", "2013", "Lille", "Nord", "France");
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<EcoleDTO> e = etuBean.getEcoles(etudiant);
		
		assertEquals("Formation", e.get(0).getLibelle());
		assertEquals("Universite", e.get(0).getEtablissement());
		assertEquals("2012", e.get(0).getDebut());
		assertEquals("2013", e.get(0).getFin());
		assertEquals("Lille",e.get(0).getVille());
		assertEquals("Nord", e.get(0).getRegion());
		assertEquals("France",e.get(0).getPays());
		assertEquals(1, e.size());
	}
	
	
	@Test
	public void testGetEcoles () {
		etuBean.addEcole(etudiant, "info","paris8","2014","2015","Paris","Ile-De-France","France");
		etuBean.addEcole(etudiant, "gestion","Créteil","2012","2013","Paris","Ile-De-France","France");
		etudiant = etuBean.getEtudiant(etudiant.getId());
		List<EcoleDTO> liste = etuBean.getEcoles(etudiant);
		assertEquals("info",liste.get(3).getLibelle());
		assertEquals("paris8",liste.get(3).getEtablissement());
		assertEquals("2014",liste.get(3).getDebut());
		assertEquals("2015",liste.get(3).getFin());
		assertEquals("Paris",liste.get(3).getVille());
		assertEquals("Ile-De-France",liste.get(3).getRegion());
		assertEquals("France",liste.get(3).getPays());
		
		assertEquals("gestion",liste.get(4).getLibelle());
		assertEquals("Créteil",liste.get(4).getEtablissement());
		assertEquals("2012",liste.get(4).getDebut());
		assertEquals("2013",liste.get(4).getFin());
		assertEquals("Paris",liste.get(4).getVille());
		assertEquals("Ile-De-France",liste.get(4).getRegion());
		assertEquals("France",liste.get(4).getPays());
		
		assertEquals(5,liste.size());
	}
	
	@Test
	public void testGetPublicationByEtudiant() {
		etuBean.addPublication(etudiant, "coucou"," message test", new Date());
		List<PublicationDTO> p = etuBean.getPublications(etudiant);
		assertEquals("coucou", p.get(0).getTitre());
		assertEquals(1, p.size());
	}
	
	@Test
	public void testGetPublication() {
		etuBean.addPublication(etu, "Salut"," message", new Date());
		List<PublicationDTO> p = etuBean.getPublications();
		assertEquals("Salut", p.get(0).getTitre());
		assertEquals("coucou", p.get(1).getTitre());
		assertEquals("publication1", p.get(2).getTitre());
		assertEquals(3, p.size());
	}
	
	@Test
	public void testAddPublication() {
		AncienEtudiantDTO etu = etuBean.createEtudiant("Henry", "Matisse", Civilite.M, "henry@yahoo.fr", "0000000", "password", new Date(), "Cp", "Lille", "miage Corp", "miage", 2006, groupe);
		etuBean.addPublication(etu, "publication1","pub", new Date());
		etu= etuBean.getEtudiant(etu.getId());
		List<PublicationDTO> e = etuBean.getPublications(etu);
		assertEquals("publication1", e.get(0).getTitre());
		assertEquals(1, e.size());
	}
	
	@Test
	public void testIsMailExists(){
		Boolean verif = etuBean.isMailExists("roberto@gmail.com");
		Boolean verif1 = etuBean.isMailExists("tom@gmail.com");
		Boolean verif2 = etuBean.isMailExists("henry@yahoo.fr");
		Boolean verif3 = etuBean.isMailExists("blalala");
		assertTrue(verif);
		assertTrue(verif1);
		assertTrue(verif2);
		assertFalse(verif3);
	}
	
	@Test 
	public void testIsPasswordIsGood () {
		Boolean verif = etuBean.isPasswordIsGood("roberto@gmail.com", "password");
		Boolean verif2 = etuBean.isPasswordIsGood("henry@yahoo.fr", "password");
		Boolean verif3 = etuBean.isPasswordIsGood("roberto@gmail.com", "blabla");
		assertTrue(verif);
		assertTrue(verif2);
		assertFalse(verif3);
	}
	
	@Test
	public void testDeleteCompetenceList() {
		etuBean.addCompetence(etu,"Oracle", 1);
		etuBean.addCompetence(etu, "J2EE", 1);
		etuBean.deleteCompetenceList(etu);
		etu = etuBean.getEtudiant(etu.getId());
		List<CompetenceDTO> listeEtu = etuBean.getCompetences(etu);
		List<CompetenceDTO> liste = new ArrayList<CompetenceDTO>();
		assertEquals(liste,listeEtu);
	}
	
	@Test
	public void testDeleteExpProList() {
		etuBean.addExperience(etudiant,"Stage2", "CapGemini","Lille", "Nord", "France", "2012", "2013", "mission gestion de projet");
		etuBean.addExperience(etudiant, "Stage3", "CGI", "Lille", "Nord", "France", "2015", "2015", "mission MOA");
	
		etuBean.deleteExpProList(etu);
		etu = etuBean.getEtudiant(etu.getId());
		List<ExperienceDTO> listeEtu = etuBean.getExperiences(etu);
		List<ExperienceDTO> liste = new ArrayList<ExperienceDTO>();
		assertEquals(liste,listeEtu);
	}


	
	@Test
	public void testDeleteFormationList() {
		etuBean.addEcole(etudiant, "info","paris8","2014","2015","Paris","Ile-De-France","France");
		etuBean.addEcole(etudiant, "gestion","Créteil","2012","2013","Paris","Ile-De-France","France");
		etuBean.deleteFormationList(etu);
		etu = etuBean.getEtudiant(etu.getId());
		List<EcoleDTO> listeEtu = etuBean.getEcoles(etu);
		List<EcoleDTO> liste = new ArrayList<EcoleDTO>();
		assertEquals(liste,listeEtu);
	}
	
	@Test
	public void testDeleteLoisirList() {
		etuBean.addHobbie(etu,"Musique");
		etuBean.addHobbie(etu, "Jeu video");
		etuBean.deleteLoisirList(etu);
		etu = etuBean.getEtudiant(etu.getId());
		List<HobbieDTO> listeEtu = etuBean.getHobbies(etu);
		List<HobbieDTO> liste = new ArrayList<HobbieDTO>();
		assertEquals(liste,listeEtu);
	}
	
	@Test
	public void testUpdateEtudiant() {
	
		etuBean.updateEtudiant(etu.getId(), "agriculteur", "Paris", "miage Corp","060606", "https://www.facebook.com/", "https://www.twitter.com/", "https://www.viadeo.com/","https://www.linkedin.com/","Trouver un emploi");
		etu = etuBean.getEtudiant(etu.getId());
		assertEquals("agriculteur", etu.getPosteActu());
		assertEquals("Paris", etu.getVilleActu());
		assertEquals("miage Corp", etu.getNomEntreprise());
		assertEquals("060606", etu.getNumTelephone());
		assertEquals("https://www.facebook.com/", etu.getFacebook());
		assertEquals("https://www.twitter.com/", etu.getTwitter());
		assertEquals("https://www.viadeo.com/", etu.getViadeo());
		assertEquals("https://www.linkedin.com/", etu.getLinkedin());
		assertEquals("Trouver un emploi", etu.getAttentes());
	}
	
	@Test
	public void testValideLien () {
		Boolean verif = etuBean.valideLien("https://www.facebook.com/", "facebook");
		Boolean verif2 = etuBean.valideLien("truc", "site");
		Boolean verif3 = etuBean.valideLien("https://www.facebook.com/", "site");
		Boolean verif4 = etuBean.valideLien("truc", "facebook");
		
		assertTrue(verif);
		assertFalse(verif2);
		assertFalse(verif3);
		assertFalse(verif4);
		
	}
}
