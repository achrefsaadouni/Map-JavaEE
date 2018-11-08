package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class PK_ProjectSkill implements Serializable{
	private int idProject ;
	private int idSkill;
	
	
	
	public PK_ProjectSkill() {
		super();
	}



	public PK_ProjectSkill(int idProject, int idSkill) {
		super();
		this.idProject = idProject;
		this.idSkill = idSkill;
	}



	public int getIdProject() {
		return idProject;
	}



	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}



	public int getIdSkill() {
		return idSkill;
	}



	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProject;
		result = prime * result + idSkill;
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
		PK_ProjectSkill other = (PK_ProjectSkill) obj;
		if (idProject != other.idProject)
			return false;
		if (idSkill != other.idSkill)
			return false;
		return true;
	}
	
	
	
	
}
