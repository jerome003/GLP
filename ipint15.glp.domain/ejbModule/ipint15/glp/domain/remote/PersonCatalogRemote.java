package ipint15.glp.domain.remote;

import java.util.List;
import javax.ejb.Remote;

import ipint15.glp.domain.entities.Person;

@Remote
public interface PersonCatalogRemote {
	public Person createPerson(String firstname, String
			lastname);
	
	public List<Person> listPerson();
}
