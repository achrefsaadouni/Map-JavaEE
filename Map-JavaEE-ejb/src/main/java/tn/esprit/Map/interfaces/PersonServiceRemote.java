package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Message;





@Remote
public interface PersonServiceRemote {
	
	public int sendMessage(String rec,String sen,String ob,String cont);
	public String test(Message msg); 

}
