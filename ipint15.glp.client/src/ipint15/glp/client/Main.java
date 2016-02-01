package ipint15.glp.client;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ipint15.glp.api.dto.Civilite;
import ipint15.glp.api.dto.AncienEtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;
import ipint15.glp.api.remote.RechercheRemote;




public class Main {

	public static void main(String[] args) {
		try {
			// création du "contexte initial" = de la connexion à l'annuaire du serveur
						InitialContext context = new InitialContext();
						// requête sur le nom de la ressource que l'on veut, ici notre EJB
						EtudiantCatalogRemote catalog =(EtudiantCatalogRemote)context.lookup("ipint15.glp.api.remote.EtudiantCatalogRemote");
						RechercheRemote recherche =(RechercheRemote)context.lookup("ipint15.glp.api.remote.RechercheRemote");
						catalog.createEtudiant("Maxime", "Delporte", Civilite.M, "maximus@gmail.com","0654897514","toto", new Date(),"Développeur", "Lille","Wordline","miage",2015);
						catalog.createEtudiant("Maximus", "Delporte", Civilite.M, "maximus@gmail.com","0568547895","toto", new Date(),"MOA","Lille","CGI","miage",2016);
						catalog.createEtudiant("Jerome", "Delporto", Civilite.M, "maximus@gmail.com","0565548970","toto", new Date(),"BI","Lille","GCI","miage", 2017);
						catalog.createEtudiant("Jeromus", "Gideon", Civilite.M, "maximus@gmail.com","0654878950","toto", new Date(),"Dev","Lille","Open","miage", 2018);
						
						
						//List<EtudiantDTO> myPersons = catalog.listEtudiant();
						List<AncienEtudiantDTO> myRecherche = recherche.rechercherEtudiant("max");
						Iterator it = myRecherche.iterator();
						while(it.hasNext()) {
							System.out.println(it.next().toString());
						}
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}
				public Main() { super(); }
			}