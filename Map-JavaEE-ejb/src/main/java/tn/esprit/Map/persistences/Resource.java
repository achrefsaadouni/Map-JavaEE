package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "resource")
public class Resource extends Person implements Serializable {
	
	private String seniority;
	
	private String workProfil;
	
	private float note;
	
	private String cv;
	
	private String picture;
	
	@Enumerated(EnumType.STRING)
	private AvailabilityType availability;
	
	private String businessSector;
	
	private float salary;
	
	private String jobType;
	
	@ManyToOne
	private Project project;
	
	
	@ManyToMany
	private List<DayOff> dayOffs;
	
	
	@ManyToMany
	private List<Skill> skills;
	
	
	@ManyToMany(mappedBy = "resources")
	private List<OrganizationalChart> organizationalCharts;

	
	
	public String getSeniority() {
		return seniority;
	}
	public void setSeniority(String seniority) {
		this.seniority = seniority;
	}

	
	public String getWorkProfil() {
		return workProfil;
	}
	public void setWorkProfil(String workProfil) {
		this.workProfil = workProfil;
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

	
	
	
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	
	
	

	public Project getProject() {
		return project;
	}
    public void setProject(Project project) {
		this.project = project;
	}

    
    
	public List<DayOff> getDayOffs() {
		return dayOffs;
	}
	public void setDayOffs(List<DayOff> dayOffs) {
		this.dayOffs = dayOffs;
	}

	
	
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	
	
	public List<OrganizationalChart> getOrganizationalCharts() {
		return organizationalCharts;
	}
	public void setOrganizationalCharts(List<OrganizationalChart> organizationalCharts) {
		this.organizationalCharts = organizationalCharts;
	}
	
	

}
