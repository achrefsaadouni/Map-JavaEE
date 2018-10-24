
package tn.esprit.Map.services;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Map.interfaces.RequestServiceRemote;
import tn.esprit.Map.persistences.Request;

@Stateful
public class RequestService implements RequestServiceRemote{
	
	@PersistenceContext(unitName="MAP")
	EntityManager em;

	@Override
	public int addRequest(Request request) {
		em.persist(request); 
		return request.getId();
	}    

	
}
