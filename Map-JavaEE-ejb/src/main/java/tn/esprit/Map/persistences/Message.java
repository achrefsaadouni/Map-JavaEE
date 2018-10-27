package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Message implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String Receiver;
	private String sender;
	private String object;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMessage;
	private TypeMessage typeMessage;
	@ManyToOne
	private InBox inBox;
	@OneToOne(mappedBy="message")
	private Person person;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateMessage() {
		return dateMessage;
	}

	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}

	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

	public InBox getInBox() {
		return inBox;
	}

	public void setInBox(InBox inBox) {
		this.inBox = inBox;
	}

	public String getReceiver() {
		return Receiver;
	}

	public void setReceiver(String receiver) {
		Receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Message() {
		super();
	}
	
	



}
