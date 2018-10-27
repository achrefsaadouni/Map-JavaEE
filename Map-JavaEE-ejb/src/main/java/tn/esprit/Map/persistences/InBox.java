package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class InBox implements Serializable {
	@Id
	@GeneratedValue
	private int id;

	@OneToOne(mappedBy="inBox") 
	private Person person;
	@OneToMany(mappedBy = "inBox")
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	

}
