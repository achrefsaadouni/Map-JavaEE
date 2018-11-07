package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ProjectSkill implements Serializable{
	@EmbeddedId
	private PK_ProjectSkill Pk_ProjectSkill;
	private int percentage;
	public ProjectSkill() {
		super();
	}
	public ProjectSkill(PK_ProjectSkill pk_ProjectSkill, int percentage) {
		super();
		Pk_ProjectSkill = pk_ProjectSkill;
		this.percentage = percentage;
	}
	public PK_ProjectSkill getPk_ProjectSkill() {
		return Pk_ProjectSkill;
	}
	public void setPk_ProjectSkill(PK_ProjectSkill pk_ProjectSkill) {
		Pk_ProjectSkill = pk_ProjectSkill;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Pk_ProjectSkill == null) ? 0 : Pk_ProjectSkill.hashCode());
		result = prime * result + percentage;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectSkill other = (ProjectSkill) obj;
		if (Pk_ProjectSkill == null) {
			if (other.Pk_ProjectSkill != null)
				return false;
		} else if (!Pk_ProjectSkill.equals(other.Pk_ProjectSkill))
			return false;
		if (percentage != other.percentage)
			return false;
		return true;
	}
	
	
}
