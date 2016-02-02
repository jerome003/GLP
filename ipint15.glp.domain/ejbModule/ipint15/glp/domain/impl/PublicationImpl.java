package ipint15.glp.domain.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ipint15.glp.api.remote.PublicationRemote;
import ipint15.glp.domain.util.Conversion;

@Stateless
public class PublicationImpl implements PublicationRemote {

	Conversion ce = new Conversion();
	@PersistenceContext
	EntityManager em;

	public PublicationImpl() {
		super();
	}

}
