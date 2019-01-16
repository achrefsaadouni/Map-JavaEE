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

	private int id;
	@Enumerated(EnumType.STRING)

	private StateType stateType;
	@Temporal(TemporalType.DATE)

	private Date rdvdate;
	@Temporal(TemporalType.DATE)

	private Date sentdate;
	
	private String speciality;


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
