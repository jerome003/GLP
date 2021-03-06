package ipint15.glp.client;


import java.util.Date;


import javax.naming.InitialContext;
import javax.naming.NamingException;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.EnseignantDTO;
import ipint15.glp.api.dto.GroupeDTO;
import ipint15.glp.api.dto.ModerateurDTO;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.remote.AdministrationRemote;
import ipint15.glp.api.remote.AncienEtudiantCatalogRemote;
import ipint15.glp.api.remote.EnseignantCatalogRemote;
import ipint15.glp.api.remote.GroupeRemote;
import ipint15.glp.api.remote.PublicationRemote;

public class Main {
	private static InitialContext ctx;
	private static AncienEtudiantCatalogRemote etuBean ;
	private static GroupeRemote groupBean;
	private static EnseignantCatalogRemote enseignBean;
	private static AdministrationRemote adminBean;
	private static PublicationRemote pubBean;
	private static AncienEtudiantDTO etudiant;
	private static AncienEtudiantDTO etudiant2;
	private static AncienEtudiantDTO etudiant3;
	private static AncienEtudiantDTO etudiant4;
	private static AncienEtudiantDTO etudiant5;
	private static AncienEtudiantDTO etudiant6;
	private static GroupeDTO groupe;
	private static GroupeDTO groupe2;
	private static GroupeDTO groupe3;
	private static GroupeDTO groupe4;
	private static GroupeDTO groupe5;
	private static GroupeDTO groupe6;
	private static GroupeDTO groupe7;

	private static ModerateurDTO moderateur;
	private static ModerateurDTO moderateur2;
	private static ModerateurDTO modo;
	private static ModerateurDTO modo2;
	private static ModerateurDTO modo3;
	private static EnseignantDTO prof;
	private static EnseignantDTO prof2;
	private static EnseignantDTO prof3;
	private static EnseignantDTO prof4;
	

	public static void main(String[] args) {
		try {

			ctx= new InitialContext();
			etuBean = (AncienEtudiantCatalogRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/AncienEtudiantCatalogImpl");
			groupBean = (GroupeRemote)ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/GroupeImpl");
			adminBean = (AdministrationRemote) ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/AdministrationImpl");
			enseignBean = (EnseignantCatalogRemote) ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/EnseignantCatalogImpl");
			pubBean = (PublicationRemote) ctx.lookup("java:global/ipint15.glp.ear/ipint15.glp.domain/PublicationImpl");
			
			

			moderateur = adminBean.createModerateur("Sarra", "Bahbah", "sara@googleNON.bla", adminBean.generatePassword(8));
			moderateur2 = adminBean.createModerateur("Maxime", "Gidon", "maxime@googleNON.bla", adminBean.generatePassword(8));
			modo = adminBean.createModerateur("bla", "bla", "email@bla.bla", adminBean.generatePassword(8));
			modo2 = adminBean.createModerateur("bla2", "bla2", "email2@bla.bla", adminBean.generatePassword(8));
			modo3 = adminBean.createModerateur("bla3", "bla3", "email3@bla.bla", adminBean.generatePassword(8));
		
			
			groupe = groupBean.createGroupe("Miage","Groupe Miage", true);
			groupe2 = groupBean.createGroupe("Elfe", "Ecole des Elfes", true);
			groupe3 = groupBean.createGroupe("Test", "description", true);
			groupe4 = groupBean.createGroupe("Test2", "description", false);
			groupe5 = groupBean.createGroupe("Raclette", "Pour les vrais fans de raclette.", false);
			groupe6 = groupBean.createGroupe("Pause café", "Avec du sucre ?", false);
			groupe7 = groupBean.createGroupe("VB league", "En équipe", false);
			
			System.out.println("moderateur du groupe  "+ groupe.getName()+" : "+ moderateur.getEmail()+ " son mot de passe est : "+ moderateur.getPassword());
			System.out.println("moderateur du groupe  "+ groupe2.getName()+" : "+ moderateur2.getEmail()+ " son mot de passe est : "+ moderateur2.getPassword());
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("moderateur sans groupe : "+ modo.getEmail()+ " son mot de passe est : "+ modo.getPassword());
			System.out.println("moderateur sans groupe : "+ modo2.getEmail()+ " son mot de passe est : "+ modo2.getPassword());
			System.out.println("moderateur sans groupe : "+ modo3.getEmail()+ " son mot de passe est : "+ modo3.getPassword());
			System.out.println("------------------------------------------------------------------------------------------------");
			
			adminBean.addGroupetoModo(moderateur.getId(), groupe);
			adminBean.addGroupetoModo(moderateur2.getId(), groupe2);
			
			etudiant =etuBean.createEtudiant("Sarra", "Bahbah", Civilite.Mme, "sara@googleNON.bla","000000",adminBean.generatePassword(8), 
					new Date(),"Sans emploi","Dev", "Paris", "CGI","miage", 2015, groupe);
			etudiant2 =etuBean.createEtudiant("Roberto", "Sanchez", Civilite.M, "roberto@googleNON.bla","000000", 
					adminBean.generatePassword(8), new Date(),"Sans emploi","Dev", "Lille", "GFI","miage", 2015, groupe);
			etudiant3 =etuBean.createEtudiant("Maxime", "Gidon", Civilite.M, "maxime@googleNON.bla","000000", 
					adminBean.generatePassword(8), new Date(),"Sans emploi","Dev", "Lyon", "Worldline","miage", 2015, groupe2);
			etudiant4 =etuBean.createEtudiant("Paolo", "Delpiro", Civilite.M, "paolo@googleNON.bla","000000",
					adminBean.generatePassword(8), new Date(),"Sans emploi","Dev", "Lille", "GFI","miage", 2015, groupe2);
			
			etudiant5 =etuBean.createEtudiant("Sa", "Ba", Civilite.Mme, "sara@gooNON.bla","000000",adminBean.generatePassword(8), 
					new Date(),"Sans emploi","Dev", "Paris", "CGI","miage", 2015, groupe2);
			etudiant6 =etuBean.createEtudiant("Sarraaaa", "Bahbahaaaa", Civilite.Mme, "sara@goaaaaaogleNON.bla","000000",adminBean.generatePassword(8), 
					new Date(),"Sans emploi","Dev", "Paris", "CGI","miage", 2015, groupe2);
			
			System.out.println("etudiant 1 est : "+ etudiant.getPrenom()+" "+etudiant.getNom()+" son adresse mail : "+etudiant.getEmail()+" et son mot de passe : "+etudiant.getPassword());
			System.out.println("etudiant 2 est : "+ etudiant2.getPrenom()+" "+etudiant2.getNom()+" son adresse mail : "+etudiant2.getEmail()+" et son mot de passe : "+etudiant2.getPassword());
			System.out.println("etudiant 3 est : "+ etudiant3.getPrenom()+" "+etudiant3.getNom()+" son adresse mail : "+etudiant3.getEmail()+" et son mot de passe : "+etudiant3.getPassword());
			System.out.println("etudiant 4 est : "+ etudiant4.getPrenom()+" "+etudiant4.getNom()+" son adresse mail : "+etudiant4.getEmail()+" et son mot de passe : "+etudiant4.getPassword());
			System.out.println("------------------------------------------------------------------------------------------------");
			
			adminBean.validationInscription(etudiant);
			adminBean.validationInscription(etudiant3);
			
			
			System.out.println("Etudiant 1 et etudiant 3 leurs inscription est validée ");
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("2 groupes non institutionnel : "+groupe5.getName()+" et "+ groupe6.getName());
			System.out.println("------------------------------------------------------------------------------------------------");
			


			etuBean.addGroupeInLesGroupesNonInstitEtudiant(etudiant2, groupe5);
			etuBean.addGroupeInLesGroupesNonInstitEtudiant(etudiant3, groupe5);
			
			System.out.println(" Etudiant 1 et etudiant 3 ont rejoins le groupe non institutionnel : "+groupe5.getName());
			System.out.println("------------------------------------------------------------------------------------------------");
			
			prof= enseignBean.createEnseignant("Caron", "Anne-Cecile", "caron@googleNON.bla");
			prof2= enseignBean.createEnseignant("Bossut", "Francis", "bossut@googleNON.bla");
			prof3= enseignBean.createEnseignant("Clerbout", "Mireille","clerbout@googleNON.bla");
			prof4= enseignBean.createEnseignant("Roos", "Jean-François", "roos@googleNON.bla");
			
			System.out.println("Prof 1 est : "+prof.getNom()+" "+prof.getPrenom()+" son adresse mail est : "+prof.getMail());
			System.out.println("Prof 2 est : "+prof2.getNom()+" "+prof2.getPrenom()+" son adresse mail est : "+prof2.getMail());
			System.out.println("Prof 3 est : "+prof3.getNom()+" "+prof3.getPrenom()+" son adresse mail est : "+prof3.getMail());
			System.out.println("Prof 4 est : "+prof4.getNom()+" "+prof4.getPrenom()+" son adresse mail est : "+prof4.getMail());
			System.out.println("------------------------------------------------------------------------------------------------");
			
			for (int i = 0; i<30; i++) {
				pubBean.addPublication(etudiant, "wesh alors", "wesh aloooooors", new Date(), true, groupe);
			}


		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
