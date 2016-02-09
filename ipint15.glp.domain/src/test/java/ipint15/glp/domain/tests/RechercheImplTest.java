package ipint15.glp.domain.tests;

import static org.junit.Assert.*;


import java.util.Date;
import java.util.List;


import javax.naming.InitialContext;
import javax.naming.NamingException;



import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.api.remote.RechercheRemote;




import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RechercheImplTest {
	
	
	private static InitialContext ctx;
	private static RechercheRemote rechBean;
	private static AncienEtudiantCatalogRemote etuBean ;
	private static GroupeRemote groupBean;
	
	@BeforeClass
	public static void setUp() throws NamingException{
		
		ctx= new InitialContext();
		rechBean = (RechercheRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/RechercheImpl");
		etuBean = (AncienEtudiantCatalogRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/EtudiantCatalogImpl");
		groupBean = (GroupeRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/GroupeImpl");
		
		
		
		GroupeDTO groupe = groupBean.createGroupe("miage","groupeMiage");
		groupBean.createGroupe("info","groupeInfo");
		etuBean.createEtudiant("Mireille", "Delpeche", Civilite.Mme, "mireille@gmail.com","00000000", "password",new Date(), "Sans emploi","prof","Lille", "Université lille", "miage",1980, groupe );
		etuBean.createEtudiant("Tom", "Hardy", Civilite.M, "tom@gmail.com","000000", "password", new Date(),"Sans emploi", "CP", "Paris","Miage Corp", "miage",2006, groupe);
	}
	
	@AfterClass
	public static void tearDown(){
		
	}
	
	@Test
	public void testRechercheEtudiant () throws NamingException {
		AncienEtudiantDTO etu = new AncienEtudiantDTO();
		
		List<AncienEtudiantDTO> res = rechBean.rechercherAncienEtudiant("Hardy");
		
		for (AncienEtudiantDTO e : res){
			if (e.getNom().equals("Hardy"))
				etu = e;
		}
		assertEquals("Hardy", etu.getNom());
		
		res = rechBean.rechercherAncienEtudiant("Tom");
		for (AncienEtudiantDTO e : res){
			if (e.getPrenom().equals("Tom"))
				etu = e;
		}
		assertEquals("Tom", etu.getPrenom());
		
		res = rechBean.rechercherAncienEtudiant("Mireille");
		for (AncienEtudiantDTO e : res){
			if (e.getPrenom().equals("Mireille"))
				etu = e;
		}
		assertEquals("Mireille", etu.getPrenom());
		
		res = rechBean.rechercherAncienEtudiant("Delpeche");
		for (AncienEtudiantDTO e : res){
			if (e.getNom().equals("Delepeche"))
				etu = e;
		}
		assertEquals("Delpeche", etu.getNom());
		
		etu = null;
		res = rechBean.rechercherAncienEtudiant("Paul");
		for (AncienEtudiantDTO e : res){
			if (e.getNom().equals("Paul"))
				etu = e;
		}
		assertNull(etu);
	}

	
	@Test
	public void testRechercheGroupe () throws NamingException {
		GroupeDTO groupe = new GroupeDTO();
		
		List<GroupeDTO> res = rechBean.rechercherGroupe("miage");
		
		for (GroupeDTO g : res){
			if (g.getName().equals("miage"))
				groupe = g;
		}
		assertEquals("miage", groupe.getName());
		
		res = rechBean.rechercherGroupe("info");
		for (GroupeDTO g : res){
			if (g.getName().equals("info"))
				groupe = g;
		}
		assertEquals("info", groupe.getName());
		
		
		groupe = null;
		res = rechBean.rechercherGroupe("bidule");
		for (GroupeDTO g : res){
			if (g.getName().equals("bidule"))
				groupe = g;
		}
		assertNull(groupe);
	}
}
