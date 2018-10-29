
package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Skill implements Serializable {

	@JsonProperty("IdSkill")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdSkill;
	
	@JsonProperty("NameSkill")
	private String NameSkill;

	
	@JsonProperty("skillResources")
	@OneToMany(mappedBy="skill" , fetch=FetchType.EAGER)
	@JsonIgnore
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

	

	
	
	
}
