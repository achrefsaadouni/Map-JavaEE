package tn.esprit.Map.persistences;

import java.io.Serializable;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CandidateFolder implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	@OneToOne
	private Candidate Candidate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Candidate getCandidate() {
		return Candidate;
	}
	public void setCandidate(Candidate candidate) {
		Candidate = candidate;
	}
	
}
