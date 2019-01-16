package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import tn.esprit.Map.persistences.Candidate;

@JsonRootName("Test")
@Entity
public class Test implements Serializable {
	@Id
	
	@GeneratedValue
	private int id ; 
	
	@Enumerated(EnumType.STRING)
	private TestType TestType;

	@Enumerated(EnumType.STRING)
	private TestResult result;
	
	@Temporal(TemporalType.DATE)
	private Date testDeadLine;
	
	private Timestamp testUploadTime;
	
	private String TestFile ;
	
	private String TestResponseFile ;
	@ManyToMany (mappedBy="test" , fetch=FetchType.EAGER)
	private Set<Modules> modules;
	private String Employment_Letter;
	
	@JsonIgnore
	@ManyToOne
	private Candidate candidate;
	@OneToMany(mappedBy="test" ,fetch=FetchType.EAGER)
	private Set<TestScore> TestScore;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TestResult getResult() {
		return result;
	}

	public void setResult(TestResult result) {
		this.result = result;
	}

	public Date getTestDeadLine() {
		return testDeadLine;
	}

	public void setTestDeadLine(Date testDeadLine) {
		this.testDeadLine = testDeadLine;
	}



	public TestType getTestType() {
		return TestType;
	}

	public void setTestType(TestType testType) {
		TestType = testType;
	}

	public String getTestFile() {
		return TestFile;
	}

	public void setTestFile(String testFile) {
		TestFile = testFile;
	}

	public String getTestResponseFile() {
		return TestResponseFile;
	}

	public void setTestResponseFile(String testResponseFile) {
		TestResponseFile = testResponseFile;
	}

	public String getEmployment_Letter() {
		return Employment_Letter;
	}

	public void setEmployment_Letter(String employment_Letter) {
		Employment_Letter = employment_Letter;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Date getTestUploadTime() {
		return testUploadTime;
	}

	public void setTestUploadTime(Timestamp testUploadTime) {
		this.testUploadTime = testUploadTime;
	}

	public Set<Modules> getModules() {
		return modules;
	}

	public void setModules(Set<Modules> modules) {
		this.modules = modules;
	}

	public Set<TestScore> getTestScore() {
		return TestScore;
	}

	public void setTestScore(Set<TestScore> testScore) {
		TestScore = testScore;
	}

	
	
	
	
	
}
