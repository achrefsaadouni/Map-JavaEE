
package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName("Resource")
@DiscriminatorValue(value = "resource")
public class Resource extends Person implements Serializable {
	@Enumerated(EnumType.STRING)
	@JsonProperty("seniority")
	private SeniorityType seniority;
	@JsonProperty("taux")
	private static double taux;

	@Enumerated(EnumType.STRING)
	@JsonProperty("workProfil")
	private WorkType workProfil;

	@JsonProperty("note")
	private float note;

	@JsonProperty("cv")
	private String cv;

	@JsonProperty("picture")
	private String picture;

	@JsonProperty("availability")
	@Enumerated(EnumType.STRING)
	private AvailabilityType availability;

	@JsonProperty("businessSector")
	private String businessSector;

	@JsonProperty("salary")
	private float salary;

	@Enumerated(EnumType.STRING)
	@JsonProperty("jobType")
	private JobType jobType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonProperty("project")
	private Project project;

	@JsonProperty("moyenneSkill")
	private float moyenneSkill;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonProperty("dayOffs")
	@JsonIgnore
	private Set<DayOff> dayOffs;

	@OneToMany(mappedBy = "resource", fetch = FetchType.EAGER)
	@JsonProperty("ResourceSkills")
	@JsonIgnore
	private Set<ResourceSkill> resourceSkills;

	@JsonProperty("organizationalCharts")
	@ManyToMany(mappedBy = "resources", fetch = FetchType.EAGER)
	private Set<OrganizationalChart> organizationalCharts;

	@OneToMany(mappedBy = "ressource", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Mandate> listeMondats;
	
	
	public Set<Mandate> getListeMondats() {
		return listeMondats;
	}

	public void setListeMondats(Set<Mandate> listeMondats) {
		this.listeMondats = listeMondats;
	}

	public float getNote() {
		return note;
	}

	public void setNote(float note) {
		this.note = note;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public AvailabilityType getAvailability() {
		return availability;
	}

	public void setAvailability(AvailabilityType availability) {
		this.availability = availability;
	}

	public String getBusinessSector() {
		return businessSector;
	}

	public void setBusinessSector(String businessSector) {
		this.businessSector = businessSector;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Set<DayOff> getDayOffs() {
		return dayOffs;
	}

	public void setDayOffs(Set<DayOff> dayOffs) {
		this.dayOffs = dayOffs;
	}

	public Set<ResourceSkill> getResourceSkills() {
		return resourceSkills;
	}

	public void setResourceSkills(Set<ResourceSkill> resourceSkills) {
		this.resourceSkills = resourceSkills;
	}

	public SeniorityType getSeniority() {
		return seniority;
	}

	public void setSeniority(SeniorityType seniority) {
		this.seniority = seniority;
	}

	public WorkType getWorkProfil() {
		return workProfil;
	}

	public void setWorkProfil(WorkType workProfil) {
		this.workProfil = workProfil;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Set<OrganizationalChart> getOrganizationalCharts() {
		return organizationalCharts;
	}

	public void setOrganizationalCharts(Set<OrganizationalChart> organizationalCharts) {
		this.organizationalCharts = organizationalCharts;
	}

	public float getMoyenneSkill() {
		return moyenneSkill;
	}

	public void setMoyenneSkill(float moyenneSkill) {
		this.moyenneSkill = moyenneSkill;
	}

	
	
	public double getTaux() {
		return taux;
	}

	public  void setTaux(double taux) {
		Resource.taux = taux;
	}

	public Resource() {
		this.taux = 1.8;
	}
	

}