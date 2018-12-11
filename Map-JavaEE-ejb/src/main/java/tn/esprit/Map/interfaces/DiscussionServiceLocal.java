package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.DiscussionChat;

@Local
public interface DiscussionServiceLocal {

	public int addDiscussion(DiscussionChat disc);
	public List<DiscussionChat> getListDiscussionByPerson(int personID);
	
}
