package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@DiscriminatorValue(value = "candidate")
public class Candidate extends Person implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9041221471384080399L;
	/**
	 * 
	 */
	
	@OneToMany(mappedBy="candidate" ,fetch=FetchType.EAGER)
	private Set<Test> Test;
	@Enumerated(EnumType.STRING)
	private CandidateState candidateState ;
	@JsonIgnore
	@OneToOne(mappedBy="candidate" )
	private JobRequest jobRequest;
	@JsonIgnore
	@OneToOne(mappedBy="Candidate" )
	private CandidateFolder CandidateFolder;
	@OneToOne
	private Project project;
	
	@OneToMany(mappedBy="candidate" ,fetch=FetchType.EAGER)
	private Set<TestScore> TestScore;
	
	public Set<Test> getTest() {
		return Test;
	}
	public void setTest(Set<Test> test) {
		Test = test;
	}
	public CandidateState getCandidateState() {
		return candidateState;
	}
	public void setCandidateState(CandidateState candidateState) {
		this.candidateState = candidateState;
	}
	public JobRequest getJobRequest() {
		return jobRequest;
	}
	public void setJobRequest(JobRequest jobRequest) {
		this.jobRequest = jobRequest;
	}
	public CandidateFolder getCandidateFolder() {
		return CandidateFolder;
	}
	public void setCandidateFolder(CandidateFolder candidateFolder) {
		CandidateFolder = candidateFolder;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Set<TestScore> getTestScore() {
		return TestScore;
	}
	public void setTestScore(Set<TestScore> testScore) {
		TestScore = testScore;
	}
	


	
}
