package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProjectSkill implements Serializable{
	@EmbeddedId
	private PK_ProjectSkill Pk_ProjectSkill;
	
	@ManyToOne
	@JoinColumn(name="idProject",referencedColumnName="id",insertable=false,updatable=false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="idSkill",referencedColumnName="IdSkill",insertable=false,updatable=false)
	private Skill skill;
	
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

	
	
}
