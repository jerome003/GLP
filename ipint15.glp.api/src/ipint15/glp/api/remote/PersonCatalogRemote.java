package ipint15.glp.api.remote;

import java.util.List;
import javax.ejb.Remote;

import ipint15.glp.api.dto.PersonDTO;


@Remote
public interface PersonCatalogRemote {
	public PersonDTO createPerson(String firstname, String
			lastname);
	
	public List<PersonDTO> listPerson();
}
