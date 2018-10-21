package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "candidate")
public class Candidate extends Person implements Serializable {
	
	@OneToOne(mappedBy="candidate")
	private TechnicalTest TechnicalTest;
	
	@OneToOne(mappedBy="candidate")
	private JobRequest jobRequest;

	public JobRequest getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(JobRequest jobRequest) {
		this.jobRequest = jobRequest;
	}

	public TechnicalTest getTechnicalTest() {
		return TechnicalTest;
	}

	public void setTechnicalTest(TechnicalTest technicalTest) {
		TechnicalTest = technicalTest;
	}
	
}
