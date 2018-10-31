
package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Skill implements Serializable {

	@JsonProperty("idSkill")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdSkill;
	
	@JsonProperty("nameSkill")
	private String NameSkill;

	@JsonIgnore
	@JsonProperty("skillResources")
	@OneToMany(mappedBy="skill" , fetch=FetchType.EAGER)
	private Set<ResourceSkill> skillResources;

	public int getIdSkill() {
		return IdSkill;
	}

	public void setIdSkill(int idSkill) {
		IdSkill = idSkill;
	}

	

	public String getNameSkill() {
		return NameSkill;
	}

	public void setNameSkill(String nameSkill) {
		NameSkill = nameSkill;
	}

	public Set<ResourceSkill> getSkillResources() {
		return skillResources;
	}

	public void setSkillResources(Set<ResourceSkill> skillResources) {
		this.skillResources = skillResources;
	}

	@Override
	public String toString() {
		return "Skill [IdSkill=" + IdSkill + ", NameSkill=" + NameSkill + ", skillResources=" + skillResources + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + IdSkill;
		result = prime * result + ((NameSkill == null) ? 0 : NameSkill.hashCode());
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
		Skill other = (Skill) obj;
		if (IdSkill != other.IdSkill)
			return false;
		if (NameSkill == null) {
			if (other.NameSkill != null)
				return false;
		} else if (!NameSkill.equals(other.NameSkill))
			return false;
		return true;
	}

	

	
	
	
}
