package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName("DiscussionChat")
public class DiscussionChat implements Serializable {
 
	@Id
	@GeneratedValue
	private int discussionID;
	
	private int person1ID;
	
	@ManyToOne
	private Person person2ID;
	
	
	public int getDiscussionID() {
		return discussionID;
	}
	public void setDiscussionID(int discussionID) {
		this.discussionID = discussionID;
	}
	public int getPerson1ID() {
		return person1ID;
	}
	public void setPerson1ID(int person1id) {
		person1ID = person1id;
	}

	
	public Person getPerson2ID() {
		return person2ID;
	}
	public void setPerson2ID(Person person2id) {
		person2ID = person2id;
	}
	
	
	@Override
	public String toString() {
		return "DiscussionChat [discussionID=" + discussionID + ", person1ID=" + person1ID + ", person2ID=" + person2ID
				+ "]";
	}
	
	
	public DiscussionChat() {
		super();
	}
	
	
	
	
	
	
}
