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
			//System.out.println(message.getContent());
			em.persist(message);
			//System.out.println(message.getObject());
			/*Query query = em.createQuery("INSERT INTO Message ('Receiver', 'content', 'dateMessage', 'object', 'sender', 'typeMessage', 'inBox_id') VALUES (?, ?, ?, ?, ?, ?, ?)");
			query.setParameter(1, message.getReceiver());
			query.setParameter(2, message.getContent());
			query.setParameter(3,null);
			query.setParameter(4,message.getObject());
			query.setParameter(5,message.getSender());
			query.setParameter(6,1);
			query.setParameter(7,1);
			
			query.executeUpdate();*/
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
