
package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import tn.esprit.Map.interfaces.RequestServiceRemote;
import tn.esprit.Map.persistences.Administrator;
import tn.esprit.Map.persistences.Client;
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
    
	
	
	@Override
	public List<Request> AllRequest() {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request As r", Request.class);
		List<Request> results = query.getResultList();
		return results;
	}

	@Override
	public void affectClienttToRequest(int requestId , int clientId) {
		
		Client clientEntityManager = em.find(Client.class,clientId);
		Request requestEntityManager = em.find(Request.class,requestId);
		
		requestEntityManager.setClient(clientEntityManager);
		
	}

	@Override
	public void affectAdminToRequest(int requestId, int adminId) {
		Administrator adminEntityManager = em.find(Administrator.class,adminId);
		
		Request requestEntityManager = em.find(Request.class,requestId);
		requestEntityManager.setAdministrator(adminEntityManager);
		
	}

	@Override
	public void deleteRequest(int requestID) {
		Request request = em.find(Request.class,requestID);
		System.out.println("requestid : "+request.getId());
		em.remove(request);
		
	}
	
	

	
}
