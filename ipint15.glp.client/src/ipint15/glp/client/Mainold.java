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



public class Mainold {

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
	private static GroupeDTO groupe3;
	private static GroupeDTO groupe4;
	private static ModerateurDTO moderateur;
	private static ModerateurDTO moderateur2;
	private static ModerateurDTO modo;
	private static ModerateurDTO modo2;
	private static ModerateurDTO modo3;

	public static void main(String[] args) {
		try {

			ctx= new InitialContext();
			etuBean = (AncienEtudiantCatalogRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/AncienEtudiantCatalogImpl");
			groupBean = (GroupeRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/GroupeImpl");
			adminBean = (AdministrationRemote) ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/AdministrationImpl");


			moderateur = adminBean.createModerateur("Sarra", "Bahbah", "sara@googleNON.bla", adminBean.generatePassword(8));
			moderateur2 = adminBean.createModerateur("Maxime", "Gidon", "maxime@googleNON.bla", adminBean.generatePassword(8));
			modo = adminBean.createModerateur("bla", "bla", "email@bla.bla", adminBean.generatePassword(8));
			modo2 = adminBean.createModerateur("bla2", "bla2", "email2@bla.bla", adminBean.generatePassword(8));
			modo3 = adminBean.createModerateur("bla3", "bla3", "email3@bla.bla", adminBean.generatePassword(8));
		
			
			groupe = groupBean.createGroupe("Miage","Groupe Miage");
			groupe2 = groupBean.createGroupe("Elfe", "Ecole des Elfes");
			groupe3 = groupBean.createGroupe("Test", "description");
			groupe4 = groupBean.createGroupe("Test2", "description");
			
			System.out.println("moderateur du groupe  "+ groupe.getName()+" : "+ moderateur.getEmail()+ " son mot de passe est : "+ moderateur.getPassword());
			System.out.println("moderateur du groupe  "+ groupe2.getName()+" : "+ moderateur2.getEmail()+ " son mot de passe est : "+ moderateur2.getPassword());
			System.out.println("moderateur sans groupe : "+ modo.getEmail()+ " son mot de passe est : "+ modo.getPassword());
			System.out.println("moderateur sans groupe : "+ modo2.getEmail()+ " son mot de passe est : "+ modo2.getPassword());
			System.out.println("moderateur sans groupe : "+ modo3.getEmail()+ " son mot de passe est : "+ modo3.getPassword());
			
			adminBean.addGroupetoModo(moderateur.getId(), groupe);
			adminBean.addGroupetoModo(moderateur2.getId(), groupe2);
			
			etudiant =etuBean.createEtudiant("Sarra", "Bahbah", Civilite.Mme, "sara@googleNON.bla","000000",adminBean.generatePassword(8), 
					new Date(),"Sans emploi","Dev", "Paris", "CGI","miage", 2015, groupe);
			etudiant2 =etuBean.createEtudiant("Roberto", "Sanchez", Civilite.M, "roberto@googleNON.bla","000000", 
					adminBean.generatePassword(8), new Date(),"Sans emploi","Dev", "Lille", "CGI","miage", 2015, groupe);
			etudiant3 =etuBean.createEtudiant("Maxime", "Gidon", Civilite.M, "maxime@googleNON.bla","000000", 
					adminBean.generatePassword(8), new Date(),"Sans emploi","Dev", "Lyon", "Worldline","miage", 2015, groupe2);
			etudiant4 =etuBean.createEtudiant("Paolo", "Delpiro", Civilite.M, "paolo@googleNON.bla","000000",
					adminBean.generatePassword(8), new Date(),"Sans emploi","Dev", "Lille", "CGI","miage", 2015, groupe2);
			
			System.out.println("etudiant 1 est : "+ etudiant.getPrenom()+" "+etudiant.getNom()+" son adresse mail : "+etudiant.getEmail()+" et son mot de passe : "+etudiant.getPassword());
			System.out.println("etudiant 2 est : "+ etudiant2.getPrenom()+" "+etudiant2.getNom()+" son adresse mail : "+etudiant2.getEmail()+" et son mot de passe : "+etudiant2.getPassword());
			System.out.println("etudiant 3 est : "+ etudiant3.getPrenom()+" "+etudiant3.getNom()+" son adresse mail : "+etudiant3.getEmail()+" et son mot de passe : "+etudiant3.getPassword());
			System.out.println("etudiant 4 est : "+ etudiant4.getPrenom()+" "+etudiant4.getNom()+" son adresse mail : "+etudiant4.getEmail()+" et son mot de passe : "+etudiant4.getPassword());
			
			adminBean.validationInscription(etudiant);
			adminBean.validationInscription(etudiant3);
			
			
			System.out.println("Etudiant 1 et etudiant 3 leurs inscription est validée ");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}