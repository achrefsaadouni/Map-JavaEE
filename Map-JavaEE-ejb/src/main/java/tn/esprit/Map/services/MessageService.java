package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
			System.out.println(message.getContent());
			//em.persist(message);
			System.out.println(message.getObject());
		}
		catch(NullPointerException e){
			System.out.println("error");
		}
		return message.getId();
	}

	@Override
	public void deleteMessage(int messageID) {
		Message message = em.find(Message.class,messageID);
		em.remove(message);
	}

	@Override
	public List<Message> AllMessage() {
		TypedQuery<Message> query = em.createQuery("SELECT m FROM Message As m", Message.class);
		List<Message> results = query.getResultList();
		return results;
	}
	
	

}