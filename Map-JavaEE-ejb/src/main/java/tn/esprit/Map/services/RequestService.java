package tn.esprit.Map.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import tn.esprit.Map.interfaces.RequestServiceRemote;
import tn.esprit.Map.persistences.Administrator;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Request;

@Stateless
public class RequestService implements RequestServiceRemote{
	
	@PersistenceContext(unitName="MAP")
	EntityManager em;
	@EJB
	MailService mail;
	
	@Override
	public int addRequest(Request request  , int idc) {
		Client client = em.find(Client.class, idc);
		//mail.send(client.getEmail(), "Request accepted", "your request has been accepted", "your request has been accepted","", "", "", "", "", "");
	    em.persist(request); 
		updateDaysMondate(request.getId());
		request.setClient(client);
		return request.getId();
	}
    
	
	
	@Override
	public List<Request> AllRequest() {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request  r", Request.class);
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
	public int deleteRequest(int requestID) {
		Request request = em.find(Request.class,requestID);
		System.out.println("requestid : "+request.getId());
		em.remove(request);
		return request.getId();

	}



	@Override
	public String updateRequest(int requestId) {
		Request request = em.find(Request.class, requestId);
		Query query = em.createQuery("update Request r set r.accept= :accept where r.id= :requestId");
		query.setParameter("accept",1);
		query.setParameter("requestId", requestId);
		int update = query.executeUpdate();
		try {
			Client client = request.getClient();
			mail.send(client.getEmail(), "Request accepted", "your request has been accepted", "your request has been accepted","", "", "", "", "", "");
		} catch (Exception e) {
			System.out.println("pas de client");
		}
		if (update == 1) {
			return "success";
		} else {
			return "fail";
		}
	}  



	@Override
	public float calculDaysMondate(Request request) {
		//Request request = em.find(Request.class,requestID);
		 /*SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		 String dateBeforeString = request.getStartDateMondate();
		 String dateAfterString = request.getEndDateMondate();*/
		float daysBetween = 0;
		 try {
		       Date dateBefore = request.getStartDateMondate();
		       Date dateAfter = request.getEndDateMondate();
		       long difference = dateAfter.getTime() - dateBefore.getTime();
		        daysBetween = (difference / (1000*60*60*24));
	               /* You can also convert the milliseconds to days using this method
	                * float daysBetween = 
	                *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
	                */
		       
		 } catch (Exception e) {
		       e.printStackTrace();
		 }
		return daysBetween;
	}



	@Override
	public String updateDaysMondate(int requestID) {
		Request request = em.find(Request.class, requestID);
		Query query = em.createQuery("update Request r set r.daysMondate= :daysMondate where r.id= :requestId");
		query.setParameter("daysMondate",this.calculDaysMondate(request));
		query.setParameter("requestId", requestID);
		int update = query.executeUpdate();
		if (update == 1) {
			return "success";
		} else {
			return "fail";
		}
	}



	@Override
	public List<Request> sortByDate() {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request  r ORDER BY r.depositDate DESC", Request.class);
		List<Request> results = query.getResultList();
		return results;
	}
	
	@Override
	public List<Request> sortByDateClient(int clientId) {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request  r where r.client.id = :s ORDER BY r.depositDate DESC", Request.class);
		query.setParameter("s", clientId);
		List<Request> results = query.getResultList();
		return results;
	}
	


	@Override
	public Request getRequestById(int requestID) {
		Request request = em.find(Request.class, requestID);
		return request;
	}
	
	
	

	}

	
	

