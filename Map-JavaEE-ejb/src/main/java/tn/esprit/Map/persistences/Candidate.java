package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "candidate")
public class Candidate extends Person implements Serializable {
	@OneToOne
	private JobRequest jobRequest;

	public JobRequest getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(JobRequest jobRequest) {
		this.jobRequest = jobRequest;
	}

}
