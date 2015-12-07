package ipint15.glp.domain.impl;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.domain.entities.Person;
import ipint15.glp.domain.remote.PersonCatalogRemote;


@Stateless
public class PersonCatalogImpl implements PersonCatalogRemote {
	
	@PersistenceContext
	EntityManager em;
	
	public PersonCatalogImpl() {
		
	}

	@Override
	public Person createPerson(String firstname, String lastname) {
		Person p = new Person();
		p.setFirstName(firstname);
		p.setLastName(lastname);
		em.persist(p);
		return p;
	}

	@Override
	public List<Person> listPerson() {
		List<Person> ps = em.createQuery("select o from Person o").getResultList();
		return ps;
	}
	
	public String toString() {
		return "Success MB !";
	}
}