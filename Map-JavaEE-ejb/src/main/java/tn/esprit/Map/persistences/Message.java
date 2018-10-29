package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name="employe")
public class Message implements Serializable {
	

	@Id
	@GeneratedValue
	private int id;
	private String receiver;
	private String sender;
	private String object;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMessage;
	private TypeMessage typeMessage; 
	@ManyToOne
	private InBox inBox;
	@OneToOne
	private Person person;
	

	public int getId() {
		return id;
	}

	@XmlAttribute(name="id",required=true)
	public void setId(int id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}
	@XmlElement(name="Object")
	public void setObject(String object) {
		this.object = object;
	}

	public String getContent() {
		return content;
	}
	@XmlElement(name="Content")
	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateMessage() {
		return dateMessage;
	}
	@XmlElement(name="DateMessage")
	public void setDateMessage(Date dateMessage) {
		this.dateMessage = dateMessage;
	}

	public TypeMessage getTypeMessage() {
		return typeMessage;
	}
	@XmlElement(name="TypeMessage")
	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

	public InBox getInBox() {
		return inBox;
	}
	@XmlElement(name="InBox")
	public void setInBox(InBox inBox) {
		this.inBox = inBox;
	}

	public String getReceiver() {
		return receiver;
	}
	@XmlElement(name="Receiver")
	public void setReceiver(String receiver) {
		receiver = receiver;
	}

	public String getSender() {
		return sender;
	}
	@XmlElement(name="Sender")
	public void setSender(String sender) {
		this.sender = sender;
	}

	public Person getPerson() {
		return person;
	}
	@XmlElement(name="Person")
	public void setPerson(Person person) {
		this.person = person;
	}

	public Message() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dateMessage == null) ? 0 : dateMessage.hashCode());
		result = prime * result + id;
		result = prime * result + ((inBox == null) ? 0 : inBox.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((typeMessage == null) ? 0 : typeMessage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (dateMessage == null) {
			if (other.dateMessage != null)
				return false;
		} else if (!dateMessage.equals(other.dateMessage))
			return false;
		if (id != other.id)
			return false;
		if (inBox == null) {
			if (other.inBox != null)
				return false;
		} else if (!inBox.equals(other.inBox))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (typeMessage != other.typeMessage)
			return false;
		return true;
	}



}
