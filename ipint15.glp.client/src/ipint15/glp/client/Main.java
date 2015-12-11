package ipint15.glp.client;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ipint15.glp.api.dto.EtudiantDTO;
import ipint15.glp.api.remote.EtudiantCatalogRemote;




public class Main {

	public static void main(String[] args) {
		try {
			// création du "contexte initial" = de la connexion à l'annuaire du serveur
						InitialContext context = new InitialContext();
						// requête sur le nom de la ressource que l'on veut, ici notre EJB
						EtudiantCatalogRemote catalog =(EtudiantCatalogRemote)context.lookup("ipint15.glp.api.remote.EtudiantCatalogRemote");
						catalog.createEtudiant("Maxime", "Delporte","maximus@gmail.com","toto", new Date());
						List<EtudiantDTO> myPersons = catalog.listEtudiant();
						Iterator it = myPersons.iterator();
						while(it.hasNext()) {
							System.out.println(it.next().toString());
						}
					} catch (NamingException e) {
						e.printStackTrace();
					}
				}
				public Main() { super(); }
			}