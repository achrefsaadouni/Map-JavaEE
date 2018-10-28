package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;


import tn.esprit.Map.persistences.Message;
import tn.esprit.Map.persistences.Request;

@Remote
public interface MessageServiceRemote {
	
	public int addMessage(Message message);
	public int deleteMessage(int messageID);
	public List<Message> AllMessage(); 

}
