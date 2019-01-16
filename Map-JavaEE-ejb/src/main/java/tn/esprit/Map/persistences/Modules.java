package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Modules implements Serializable{
	
	@Id
	@GeneratedValue
	private int id ;
	private String title ;
	@JsonIgnore
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy="modules" , fetch = FetchType.EAGER)
	private Set<Qcm> Question;
	@JsonIgnore
	@ManyToMany
	private List<Test> test;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	public List<Test> getTest() {
		return test;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

	public Set<Qcm> getQuestion() {
		return Question;
	}

	public void setQuestion(Set<Qcm> question) {
		Question = question;
	}
	
	
}
