package tn.map.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Message implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String object;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMessage;
	private TypeMessage typeMessage;
	@ManyToOne
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
