package tn.esprit.Map.persistences;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
@DiscriminatorValue(value = "QCM")
public class Qcm extends Question  {

	private boolean CU;
	@OneToMany (mappedBy="qcm")
	private List<Choices> choices;
	
	public boolean isCU() {
		return CU;
	}
	public void setCU(boolean cU) {
		CU = cU;
	}
	public List<Choices> getChoices() {
		return choices;
	}
	public void setChoices(List<Choices> choices) {
		this.choices = choices;
	}
	
	
	
	
	

}
