package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Message;
import tn.esprit.Map.persistences.Person;





@Remote
public interface PersonServiceRemote {
	
	public Person getPersonById(int id);
	public String test(Message msg); 
	public boolean login (String username,String password);
	public Person findPersonByUsername(String username);
 
}
