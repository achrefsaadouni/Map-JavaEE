package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public class Person implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	@OneToMany(mappedBy = "person")
	private List<InBox> inBoxs;
	@OneToMany(mappedBy = "person")
	private List<Message> messages;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<InBox> getInBoxs() {
		return inBoxs;
	}

	public void setInBoxs(List<InBox> inBoxs) {
		this.inBoxs = inBoxs;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
