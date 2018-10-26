package tn.esprit.Map.persistences;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	
	private File TestFile ;
	
	private File TestResponseFile ;
	
	private String Employment_Letter;
	
	@OneToOne
	private Candidate candidate;

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

	public File getTestFile() {
		return TestFile;
	}

	public void setTestFile(File testFile) {
		TestFile = testFile;
	}

	public File getTestResponseFile() {
		return TestResponseFile;
	}

	public void setTestResponseFile(File testResponseFile) {
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

	
	
	
	
	
}
