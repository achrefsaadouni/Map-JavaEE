package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("resource")
@Entity
@DiscriminatorValue(value = "resource")
public class Resource extends Person implements Serializable {
	@JsonProperty("seniority")
	private String seniority;
	@JsonProperty("workProfil")
	private String workProfil;
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
	@JsonProperty("jobType")
	private String jobType;
	@JsonProperty("taux")
	private double taux;

	public Resource() {
		super();
		this.taux = 1.8;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	private Set<DayOff> dayOffs=new HashSet<>();;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Skill> skills=new HashSet<>();;

	@ManyToMany(mappedBy = "resources",fetch=FetchType.EAGER)
	private Set<OrganizationalChart> organizationalCharts=new HashSet<>();;
	
	@OneToMany(mappedBy="ressource",fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Mandate> listeMondats=new HashSet<>();

	public Set<Mandate> getListeMondats() {
		return listeMondats;
	}

	public void setListeMondats(Set<Mandate> listeMondats) {
		this.listeMondats = listeMondats;
	}

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

	public Set<DayOff> getDayOffs() {
		return dayOffs;
	}

	public void setDayOffs(Set<DayOff> dayOffs) {
		this.dayOffs = dayOffs;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Set<OrganizationalChart> getOrganizationalCharts() {
		return organizationalCharts;
	}

	public void setOrganizationalCharts(Set<OrganizationalChart> organizationalCharts) {
		this.organizationalCharts = organizationalCharts;
	}

}
