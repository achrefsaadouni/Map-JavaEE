package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName("ResourceSkill")
public class ResourceSkill implements Serializable{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@JsonProperty("idResourceSkill")
	private int IdResourceSkill;
	@JsonProperty("rateSkill")
	private float rateSkill;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="IdSkill" , referencedColumnName="IdSkill", updatable = false)
	@JsonProperty("skill")
	
	private Skill skill;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id" , referencedColumnName="id", updatable = false)
	@JsonProperty("resource")
	
	private Resource resource;

	public int getIdResourceSkill() {
		return IdResourceSkill;
	}

	public void setIdResourceSkill(int idResourceSkill) {
		IdResourceSkill = idResourceSkill;
	}
	@JsonIgnore
	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	@JsonIgnore
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public float getRateSkill() {
		return rateSkill;
	}

	public void setRateSkill(float rateSkill) {
		this.rateSkill = rateSkill;
	}

	@Override
	public String toString() {
		return "ResourceSkill [IdResourceSkill=" + IdResourceSkill + ", rateSkill=" + rateSkill + "]";
	}
	
	
	

}
