package tn.esprit.Map.persistences;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@Entity
public class JobRequest implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	@Enumerated(EnumType.STRING)
	private StateType stateType;
	@Temporal(TemporalType.DATE)
	private Date Rdvdate;
	@Temporal(TemporalType.DATE)
	private Date Sentdate;
	private String speciality;
	@OneToOne
	private Candidate candidate;
	@XmlElement(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@XmlElement(name="stateType")
	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}
	
	@XmlElement(name="speciality")
	public String getSpeciality() {
		return speciality;
	}
	
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	@XmlElement(name="candidate")
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	@XmlElement(name="Rdvdate")
	public Date getRdvdate() {
		return Rdvdate;
	}

	public void setRdvdate(Date rdvdate) {
		Rdvdate = rdvdate;
	}
	@XmlElement(name="Sentdate")
	public Date getSentdate() {
		return Sentdate;
	}

	public void setSentdate(Date sentdate) {
		Sentdate = sentdate;
	}
	

}
