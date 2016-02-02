package ipint15.glp.client;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;



public class Main {

	private static InitialContext ctx;
	private static AncienEtudiantCatalogRemote etuBean ;
	private static GroupeRemote groupBean;
	private static AdministrationRemote adminBean;
	private static AncienEtudiantDTO etudiant;
	private static AncienEtudiantDTO etudiant2;
	private static AncienEtudiantDTO etudiant3;
	private static AncienEtudiantDTO etudiant4;
	private static GroupeDTO groupe;
	private static GroupeDTO groupe2;
	private static ModerateurDTO moderateur;
	private static ModerateurDTO moderateur2;

	public static void main(String[] args) {
		try {

			ctx= new InitialContext();
			etuBean = (AncienEtudiantCatalogRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/AncienEtudiantCatalogImpl");
			groupBean = (GroupeRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/GroupeImpl");
			adminBean = (AdministrationRemote) ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/AdministrationImpl");


			moderateur = adminBean.createModerateur("Sarra", "Bahbah", "bahbahsara@yahoo.fr", adminBean.generatePassword(8));
			moderateur2 = adminBean.createModerateur("Maxime", "Gidon", "gidon.maxime@gmail.com", adminBean.generatePassword(8));
			
			groupe = groupBean.createGroupe("Miage","Groupe Miage");
			groupe2 = groupBean.createGroupe("Elfe", "Ecole des Elfes");
			
			adminBean.addGroupetoModo(moderateur.getId(), groupe);
			adminBean.addGroupetoModo(moderateur2.getId(), groupe2);
			
			etudiant =etuBean.createEtudiant("Sarra", "Bahbah", Civilite.Mme, "bahbahsara@yahoo.fr","000000",adminBean.generatePassword(8), 
					new Date(),"Dev", "Paris", "CGI","miage", 2015, groupe);
			etudiant2 =etuBean.createEtudiant("Jerome", "Delporte", Civilite.M, "jerome.delporte003@gmail.com","000000", 
					adminBean.generatePassword(8), new Date(),"Dev", "Lille", "CGI","miage", 2015, groupe);
			etudiant3 =etuBean.createEtudiant("Maxime", "Gidon", Civilite.M, "gidon.maxime@gmail.com","000000", 
					adminBean.generatePassword(8), new Date(),"Dev", "Lyon", "Worldline","miage", 2015, groupe2);
			etudiant4 =etuBean.createEtudiant("Camille", "Marquette", Civilite.M, "camille.marquette1@gmail.com","000000",
					adminBean.generatePassword(8), new Date(),"Dev", "Lille", "CGI","miage", 2015, groupe2);
			

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	public Main() { super(); }
}