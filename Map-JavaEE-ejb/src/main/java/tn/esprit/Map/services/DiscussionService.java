package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.DiscussionServiceLocal;
import tn.esprit.Map.persistences.DiscussionChat;
import tn.esprit.Map.persistences.MessageChat;

@Stateless
public class DiscussionService implements DiscussionServiceLocal {

	@PersistenceContext(unitName="MAP")
	EntityManager em; 
	
	@Override
	public int addDiscussion(DiscussionChat disc) {
		em.persist(disc);
		return disc.getDiscussionID();
	}

	@Override
	public List<DiscussionChat> getListDiscussionByPerson(int personID) {
		TypedQuery<DiscussionChat> query = em.createQuery("SELECT d FROM DiscussionChat d WHERE person1ID = :person1ID", DiscussionChat.class);
		query.setParameter("person1ID",personID);
		List<DiscussionChat> results = query.getResultList();
		return results;
	}

	

}
