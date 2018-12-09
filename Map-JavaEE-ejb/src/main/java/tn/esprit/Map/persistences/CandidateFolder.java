package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@JsonRootName("CandidateFolder")
@Entity
public class CandidateFolder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1388847371658863607L;
	@Id
	@GeneratedValue
	private int id;
	private String Section_3;
	private String Motivation_Letter;
	private String medical_folder;
	private String Passport;
	@JsonIgnore
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
	public String getSection_3() {
		return Section_3;
	}
	public void setSection_3(String section_3) {
		Section_3 = section_3;
	}
	public String getMotivation_Letter() {
		return Motivation_Letter;
	}
	public void setMotivation_Letter(String motivation_Letter) {
		Motivation_Letter = motivation_Letter;
	}
	public String getMedical_folder() {
		return medical_folder;
	}
	public void setMedical_folder(String medical_folder) {
		this.medical_folder = medical_folder;
	}
	public String getPassport() {
		return Passport;
	}
	public void setPassport(String passport) {
		Passport = passport;
	}
	
	
	
	
}
