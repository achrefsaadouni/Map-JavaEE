package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@DiscriminatorValue(value = "QCM")
public class Qcm extends Question implements Serializable {

	private boolean CU;
	
	
	@ManyToOne
	private Modules modules;
	
	@OneToMany (mappedBy="qcm" , fetch=FetchType.EAGER)
	private Set<Choices> choices;
	
	public boolean isCU() {
		return CU;
	}
	public void setCU(boolean cU) {
		CU = cU;
	}
	public Set<Choices> getChoices() {
		return choices;
	}
	public void setChoices(Set<Choices> choices) {
		this.choices = choices;
	}
	public Modules getModules() {
		return modules;
	}
	public void setModules(Modules modules) {
		this.modules = modules;
	}
	
	
	
	
	
	
	
	

}
