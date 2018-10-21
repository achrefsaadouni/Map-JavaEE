package tn.map.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "client")
public class Client extends Person implements Serializable {
	@Enumerated(EnumType.STRING)
	private ClientType clientType;
	@Enumerated(EnumType.STRING)
	private ClientCategory clientCategory;
	private String nameSociety;
	private String logo;
	private String address;
	@OneToMany(mappedBy = "client")
	private List<Project> projects;
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

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}
