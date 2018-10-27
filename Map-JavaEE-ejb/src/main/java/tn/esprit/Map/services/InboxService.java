package tn.esprit.Map.services;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Map.interfaces.InboxServiceRemote;
import tn.esprit.Map.persistences.InBox;

@Stateful
public class InboxService implements InboxServiceRemote{
	@PersistenceContext(unitName="MAP")
	EntityManager em;
	
	@Override
	public int addInbox(InBox inbox) { 
		em.persist(inbox);
		return 0;
	}
	
	
  
}
