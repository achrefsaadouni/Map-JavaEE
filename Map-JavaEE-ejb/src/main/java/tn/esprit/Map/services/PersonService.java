package tn.esprit.Map.services;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Map.interfaces.PersonServiceRemote;
import tn.esprit.Map.persistences.Message;



@Stateful
public class PersonService implements PersonServiceRemote{

	@PersistenceContext(unitName="MAP")
	EntityManager em;
	
 
	

	@Override
	public int sendMessage(String rec, String sen, String ob, String cont) {
		
		Message m = new Message();
		MessageService ms = new MessageService(); 
		m.setId(2);
		m.setReceiver(rec);
		m.setSender(sen);
		m.setObject(ob);
		m.setContent(cont);
		em.persist(m);
		return 5;
	}




	@Override
	public String test(Message msg) {
		em.persist(msg);
		return msg.getContent();
	}

	

	

	

	

	



}
