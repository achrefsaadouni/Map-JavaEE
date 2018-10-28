package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.MessageServiceRemote;
import tn.esprit.Map.persistences.Message;
import tn.esprit.Map.persistences.Request;

@Stateful
public class MessageService implements MessageServiceRemote {


	@PersistenceContext(unitName="MAP")
	EntityManager em; 

	@Override
	public int addMessage(Message message) {
		
		try {
			
			em.persist(message);
			
		}
		catch(NullPointerException e){
			System.out.println("error");
		}
		return message.getId();
	}

	@Override
	public int deleteMessage(int messageID) {
		Message message = em.find(Message.class,messageID);
		em.remove(message);
		return message.getId();
	}

	@Override
	public List<Message> AllMessage() {
		TypedQuery<Message> query = em.createQuery("SELECT m FROM Message As m", Message.class);
		List<Message> results = query.getResultList();
		return results;
	}
	
	

}
