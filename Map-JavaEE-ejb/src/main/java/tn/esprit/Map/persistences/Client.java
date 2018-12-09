package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.jms.JMSSessionMode;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("client")
@Entity
@DiscriminatorValue(value = "client")
public class Client extends Person implements Serializable {
	@Enumerated(EnumType.STRING)
	@JsonProperty("clientType")
	private ClientType clientType;
	@Enumerated(EnumType.STRING)
	@JsonProperty("clientCategory")
	private ClientCategory clientCategory;
	@JsonProperty("nameSociety")
	private String nameSociety;
	@JsonProperty("logo")
	private String logo;
	@JsonProperty("address")
	private String address;
	@OneToMany(mappedBy = "client",fetch=FetchType.EAGER)
	@JsonIgnore
	@JsonProperty("projects")
	private List<Project> projects;
	@JsonIgnore
	
	@OneToMany(mappedBy = "client")
	private List<Request> requests;

	public ClientType getClientType() {
		return clientType;
	}
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public ClientCategory getClientCategory() {
		return clientCategory;
	}
	public void setClientCategory(ClientCategory clientCategory) {
		this.clientCategory = clientCategory;
	}

	public String getNameSociety() {
		return nameSociety;
	}

	public void setNameSociety(String nameSociety) {
		this.nameSociety = nameSociety;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	@JsonIgnore
	public List<Request> getRequests() {
		return requests;
	}
	@JsonProperty
	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	

	

}
