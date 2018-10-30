package tn.esprit.Map.persistences;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("archivedProjects")
@Entity
//@Table(name="ArchivedProjects")
public class ArchivedProjects {
	@Id
	@GeneratedValue
	@JsonProperty("idProject")
	private int id;
	@JsonProperty("NameProject")
	private String projectName;
	@Temporal(TemporalType.DATE)
	@JsonProperty("StartDate")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonProperty("EndDate")
	private Date endDate;
	@JsonProperty("Address")
	private String address;
	@JsonProperty("TotalNbResource")
	private int totalNumberResource;
	@JsonProperty("LevioNbResource")
	private int levioNumberResource;
	@JsonProperty("Picture")
	private String picture;
	@Enumerated(EnumType.STRING)
	@JsonProperty("ProjectType")
	private ProjectType projectType;


	@JsonProperty("Client")
	private int client;


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


	public int getClient() {
		return client;
	}


	public void setClient(int client) {
		this.client = client;
	}
	
	
	
}
