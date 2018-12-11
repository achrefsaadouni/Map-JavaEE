package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName("MessageChat")
public class MessageChat implements Serializable {
	
	@Id
	@GeneratedValue
	private int messageChatID;
	private int senderID;
	private int discussionID;
	private String content;
	@Temporal(TemporalType.DATE) 
	private Date date;
	private Boolean seen;
	
	
	public Boolean getSeen() {
		return seen;
	}
	public void setSeen(Boolean seen) {
		this.seen = seen;
	}
	
	public int getMessageChatID() {
		return messageChatID;
	}
	public void setMessageChatID(int messageChatID) {
		this.messageChatID = messageChatID;
	}
	public int getSenderID() {
		return senderID;
	}
	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}
	public int getDiscussionID() {
		return discussionID;
	}
	public void setDiscussionID(int discussionID) {
		this.discussionID = discussionID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "MessageChat [messageChatID=" + messageChatID + ", senderID=" + senderID + ", discussionID="
				+ discussionID + ", content=" + content + ", date=" + date + ", seen=" + seen + "]";
	}
	public MessageChat() {
		super();
	}
	
	
	
	
	
	
	

}
