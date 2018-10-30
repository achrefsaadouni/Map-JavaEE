package tn.esprit.Map.persistences;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
@JsonRootName("JobRequest")
@Entity
public class JobRequest implements Serializable {
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private int id;
	@Enumerated(EnumType.STRING)
	@JsonProperty("stateType")
	private StateType stateType;
	@Temporal(TemporalType.DATE)
	@JsonProperty("rdvdate")
	private Date rdvdate;
	@Temporal(TemporalType.DATE)
	@JsonProperty("sentdate")
	private Date sentdate;
	@JsonProperty("speciality")
	private String speciality;
	@JsonProperty("Cv")
	private String Cv;
	@OneToOne
	private Candidate candidate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}
	
	public String getSpeciality() {
		return speciality;
	}
	
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	public String getCv() {
		return Cv;
	}

	public void setCv(String cv) {
		Cv = cv;
	}

	public Date getRdvdate() {
		return rdvdate;
	}

	public void setRdvdate(Date rdvdate) {
		this.rdvdate = rdvdate;
	}

	public Date getSentdate() {
		return sentdate;
	}

	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}
	

}
