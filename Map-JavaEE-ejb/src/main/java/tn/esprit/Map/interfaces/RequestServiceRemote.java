package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.Map.persistences.Request;

@Remote
public interface RequestServiceRemote {
	
	public int addRequest(Request request);
	public List<Request> AllRequest();
	public Request getRequestById(int requestID);
	public void affectClienttToRequest(int requestId , int clientId);
	public void affectAdminToRequest(int requestId , int adminId);
	public int deleteRequest(int requestID);
	public String updateRequest(int requestID);
    public float calculDaysMondate(Request request);
    public String updateDaysMondate(int requestID);
    public List<Request> sortByDate(); 

}   
