package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("project")
@Entity
public class Project implements Serializable {
	@Id
	@GeneratedValue
	@JsonProperty("idProject")
	private int id;
	private String projectName;
	@Temporal(TemporalType.DATE)
	@JsonProperty("startDate")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonProperty("endDate")
	private Date endDate;
	@JsonProperty("Address")
	private String address;
	@JsonProperty("totalNumberResource")
	private int totalNumberResource;
	@JsonProperty("LevioNbResource")
	private int levioNumberResource;
	@JsonProperty("picture")
	private String picture;
	@Enumerated(EnumType.STRING)
	@JsonProperty("projectType")
	private ProjectType projectType;
	@ManyToOne
	@JsonProperty("organizationalChart_id")
	private OrganizationalChart organizationalChart;
	@ManyToOne	
	@JoinColumn(name = "clientId", referencedColumnName = "id",insertable = false, updatable = false)
	@JsonProperty("Client")
	private Client client;
	@OneToMany(mappedBy = "projet", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Mandate> listeMondats;
	@OneToOne(mappedBy="project")
	@JsonIgnore
	private Request request;
	
	@OneToMany(mappedBy="project", fetch = FetchType.EAGER)
	private Set<ProjectSkill> projectSkills;
	


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

	public Set<Mandate> getListeMondats() {
		return listeMondats;
	}

	public void setListeMondats(Set<Mandate> listeMondats) {
		this.listeMondats = listeMondats;
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

	public Set<ProjectSkill> getProjectSkills() {
		return projectSkills;
	}

	public void setProjectSkills(Set<ProjectSkill> projectSkills) {
		this.projectSkills = projectSkills;
	}


	
	



}
