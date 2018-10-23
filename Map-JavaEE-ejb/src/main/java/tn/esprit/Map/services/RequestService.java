package tn.esprit.Map.services;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.interfaces.RequestServiceRemote;
import tn.esprit.Map.persistences.Request;

@Stateful
public class RequestService implements RequestServiceRemote{
	
	@PersistenceContext(unitName="Map-JavaEE-ejb")
	EntityManager em;

	@Override
	public int addRequest(Request request) {
		em.persist(request);
		return request.getId();
	}

}
