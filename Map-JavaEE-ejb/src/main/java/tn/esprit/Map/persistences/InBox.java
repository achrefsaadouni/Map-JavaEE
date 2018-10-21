package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class InBox implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private Person receiver;
	@ManyToOne
	private Person person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getReceiver() {
		return receiver;
	}

	public void setReceiver(Person receiver) {
		this.receiver = receiver;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
