package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

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

	public Date getDate() {
		return Rdvdate;
	}

	public void setDate(Date date) {
		this.Rdvdate = date;
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
		return Rdvdate;
	}

	public void setRdvdate(Date rdvdate) {
		Rdvdate = rdvdate;
	}

	public Date getSentdate() {
		return Sentdate;
	}

	public void setSentdate(Date sentdate) {
		Sentdate = sentdate;
	}
	

}
