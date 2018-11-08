package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("person")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public class Person implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("id")
	private int id;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("login")
	private String login;
	@JsonProperty("password")
	private String password;
	@JsonProperty("email")
	private String email;
	@JsonProperty("archived")
	private int archived;
	@JsonProperty("notePerson")
	private double notePerson;
	@OneToOne
	@JsonProperty("inBox")
	private InBox inBox;
	@OneToOne(mappedBy="person")
	private Message message;
	@Enumerated(EnumType.STRING)
	private Role roleT;
	

	public Role getRoleT() {
		return roleT;
	}
	public void setRoleT(Role role) {
		this.roleT = role;
	}
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
	@XmlElement(name="password")
	public void setPassword(String password) {
		this.password = password;
	}

	public InBox getInBox() {
		return inBox;
	}
	public void setInBoxs(InBox inBox) {
		this.inBox = inBox;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setInBox(InBox inBox) {
		this.inBox = inBox;
	}
	public int isArchived() {
		return archived;
	}
	public void setArchived(int archived) {
		this.archived = archived;
	}
	public double getNotePerson() {  
		return notePerson;
	}
	public void setNotePerson(double notePerson) {
		this.notePerson = notePerson;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public int getArchived() {
		return archived;
	}

   
}
