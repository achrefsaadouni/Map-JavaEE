package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Project implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String projectName;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private String address;
	private int totalNumberResource;
	private int levioNumberResource;
	private String picture;
	private ProjectType projectType;
	@ManyToOne
	private OrganizationalChart organizationalChart;
	@ManyToOne
	private Client client;
	@OneToMany(mappedBy = "project")
	private List<Resource> resources;
	
	private List<Mandate> listeMondats;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTotalNumberResource() {
		return totalNumberResource;
	}

	public void setTotalNumberResource(int totalNumberResource) {
		this.totalNumberResource = totalNumberResource;
	}

	public int getLevioNumberResource() {
		return levioNumberResource;
	}

	public void setLevioNumberResource(int levioNumberResource) {
		this.levioNumberResource = levioNumberResource;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public OrganizationalChart getOrganizationalChart() {
		return organizationalChart;
	}

	public void setOrganizationalChart(OrganizationalChart organizationalChart) {
		this.organizationalChart = organizationalChart;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
