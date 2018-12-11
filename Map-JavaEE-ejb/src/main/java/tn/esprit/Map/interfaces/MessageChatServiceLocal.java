package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.MessageChat;


@Local
public interface MessageChatServiceLocal {
	
	public int addMessage(MessageChat msg);
	public List<MessageChat> getMessageByDiscussion(int discussionID);
	MessageChat getLastMsg(int discussionID);
}
