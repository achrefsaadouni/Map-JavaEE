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
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Request;
import tn.esprit.Map.persistences.TypeMessage;

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

	@Override
	public List<Message> MessageByPerson(Person personne) {
		TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE person= :personne", Message.class);
		query.setParameter("personne",personne);
		List<Message> results = query.getResultList();
		return results;
	}

	@Override
	public long countSatisfactionMessageByPerson(Person personne) {
		TypedQuery<Long> query = em.createQuery("SELECT count(m.id) FROM Message m  WHERE person= :personne AND typeMessage= :typeMessage",Long.class);
		query.setParameter("personne",personne);
		query.setParameter("typeMessage",TypeMessage.satisfaction);
		return (long)query.getSingleResult();
	}

	@Override
	public long countReclamationMessageByPerson(Person personne) {
		TypedQuery<Long> query = em.createQuery("SELECT count(m.id) FROM Message m  WHERE person= :personne AND typeMessage= :typeMessage",Long.class);
		query.setParameter("personne",personne);
		query.setParameter("typeMessage",TypeMessage.reclamation);
		return (long)query.getSingleResult();
	}

	@Override
	public long countProblemMessageByPerson(Person personne) {
		TypedQuery<Long> query = em.createQuery("SELECT count(m.id) FROM Message m  WHERE person= :personne AND typeMessage= :typeMessage",Long.class);
		query.setParameter("personne",personne);
		query.setParameter("typeMessage",TypeMessage.technicalProblem);
		return (long)query.getSingleResult();
	}

	@Override
	public double calculNotePerson(Person personne) {
		double total=0;
		double satisfaction=0;
		double problem=0;
		double note=0;
		total=countProblemMessageByPerson(personne)+countReclamationMessageByPerson(personne)+countSatisfactionMessageByPerson(personne);
		satisfaction=(countSatisfactionMessageByPerson(personne)*100)/total;
		note=(satisfaction*10)/100;
	
		return note;
	}

	@Override
	public String updateMessage(Person personne) { 
		Query query = em.createQuery("update Person p set p.notePerson= :notePerson where p.id= :personneId");
		query.setParameter("notePerson",calculNotePerson(personne));
		query.setParameter("personneId", personne.getId());
		int update = query.executeUpdate();
		if (update == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public List<Message> allMessageSortedByPerson() {
		TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE m.person IN ( SELECT p FROM Person p ORDER BY p.notePerson DESC)", Message.class);
		//query.setParameter("personne",personne);
		List<Message> results = query.getResultList();
		return results;
	}

	

	

	

	
	
	
	
	

}
