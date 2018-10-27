package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.InBox;

@Remote
public interface InboxServiceRemote {
	
	public int addInbox(InBox inbox);

}
