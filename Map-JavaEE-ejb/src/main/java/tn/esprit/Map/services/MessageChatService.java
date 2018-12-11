package tn.esprit.Map.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.MessageChatServiceLocal;
import tn.esprit.Map.persistences.MessageChat;

@Stateless
public class MessageChatService implements MessageChatServiceLocal {

	@PersistenceContext(unitName="MAP")
	EntityManager em; 
	
	@Override
	public int addMessage(MessageChat msg) {
		msg.setDate(new Date());
		em.persist(msg);
		return msg.getMessageChatID();
	}

	@Override
	public List<MessageChat> getMessageByDiscussion(int discussionID) {
		TypedQuery<MessageChat> query = em.createQuery("SELECT m FROM MessageChat m WHERE discussionID = :discussionID", MessageChat.class);
		query.setParameter("discussionID",discussionID);
		List<MessageChat> results = query.getResultList();
		for(MessageChat result : results){
			result.setSeen(true);
		}
		return results;
	}
	
	@Override
	public MessageChat getLastMsg(int discussionID) {
		TypedQuery<MessageChat> query = em.createQuery("SELECT m FROM MessageChat m WHERE discussionID = :discussionID AND senderID = 2 AND seen = false ORDER BY messageChatID DESC", MessageChat.class);
		query.setParameter("discussionID",discussionID);
		try{
			MessageChat msg = query.setMaxResults(1).getSingleResult();
			msg.setSeen(true);
			return msg;
		}
		catch(NoResultException nre){
			
		}
		return null;
	}

}
