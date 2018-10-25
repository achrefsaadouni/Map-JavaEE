package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@XmlRootElement(name="person")
public class Person implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	@OneToOne
	private InBox inBox;


	public int getId() {
		return id;
	}
	@XmlAttribute(name="id",required=true)
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	@XmlElement(name="firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	@XmlElement(name="lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}
	@XmlElement(name="login")
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


}
