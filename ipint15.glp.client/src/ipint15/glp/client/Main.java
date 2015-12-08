package ipint15.glp.client;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ipint15.glp.api.remote.PersonCatalogRemote;
import ipint15.glp.api.dto.PersonDTO;


public class Main {

	public static void main(String[] args) {
		try {
			// création du "contexte initial" = de la connexion à l'annuaire du serveur
						InitialContext context = new InitialContext();
						// requête sur le nom de la ressource que l'on veut, ici notre EJB
						PersonCatalogRemote catalog =(PersonCatalogRemote)context.lookup("ipint.ejb.util.personbean.PersonCatalogRemote");
						catalog.createPerson("Maxime", "Delporte");
						List<PersonDTO> myPersons = catalog.listPerson();
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