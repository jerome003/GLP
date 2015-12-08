package ipint15.glp.domain.impl;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.dto.PersonDTO;
import ipint15.glp.api.remote.PersonCatalogRemote;
import ipint15.glp.domain.entities.Person;

@Stateless
public class PersonCatalogImpl implements PersonCatalogRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public PersonCatalogImpl() {
		
	}

	
	@Override
	public PersonDTO createPerson(String firstname, String lastname) {
		Person p = new Person();
		p.setFirstName(firstname);
		p.setLastName(lastname);
		em.persist(p);
		PersonDTO pDTO = new PersonDTO();
		pDTO.setFirstName(firstname);
		pDTO.setLastName(lastname);
		return pDTO;

	}

	@Override
	public List<PersonDTO> listPerson() {
		List<Person> ps = em.createQuery("select o from Person o").getResultList();
		List<PersonDTO> psDTO = new ArrayList<PersonDTO>();
		for (Person p : ps) {
			PersonDTO pDTO = new PersonDTO();
			pDTO.setFirstName(p.getFirstName());
			pDTO.setLastName(p.getLastName());
			psDTO.add(pDTO);
		}
		return psDTO;
	}
	
	public String toString() {
		return "Success MB !";
	}
}