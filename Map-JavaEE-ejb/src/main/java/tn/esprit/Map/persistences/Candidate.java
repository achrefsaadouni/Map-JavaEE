package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "candidate")
public class Candidate extends Person implements Serializable {
	
	@OneToOne(mappedBy="candidate")
	private Test Test;
	@Enumerated(EnumType.STRING)
	private CandidateState candidateState ;
	@OneToOne(mappedBy="candidate")
	private JobRequest jobRequest;
	@OneToOne(mappedBy="Candidate")
	private CandidateFolder CandidateFolder;
	

	public JobRequest getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(JobRequest jobRequest) {
		this.jobRequest = jobRequest;
	}

	public Test getTechnicalTest() {
		return Test;
	}

	public void setTechnicalTest(Test technicalTest) {
		Test = technicalTest;
	}
	
}
