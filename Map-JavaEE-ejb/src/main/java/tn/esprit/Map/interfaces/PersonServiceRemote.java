package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Message;





@Remote
public interface PersonServiceRemote {
	
	
	public String test(Message msg); 

}
