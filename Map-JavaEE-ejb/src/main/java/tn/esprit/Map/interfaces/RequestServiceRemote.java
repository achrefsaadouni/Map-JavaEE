package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Request;

@Remote
public interface RequestServiceRemote {
	
	public int addRequest(Request request);
	public void calcul();   

}   
