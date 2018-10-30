package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName("ResourceSkill")
public class ResourceSkill implements Serializable{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@JsonProperty("IdResourceSkill")
	private int IdResourceSkill;
	@JsonProperty("rateSkill")
	private float rateSkill;
	
	
	
	@ManyToOne
	@JoinColumn(name="IdSkill" , referencedColumnName="IdSkill", insertable = false, updatable = false)
	@JsonProperty("skill")
	private Skill skill;
	
	@ManyToOne
	@JoinColumn(name="id" , referencedColumnName="id", insertable = false, updatable = false)
	@JsonProperty("resource")
	private Resource resource;

	public int getIdResourceSkill() {
		return IdResourceSkill;
	}

	public void setIdResourceSkill(int idResourceSkill) {
		IdResourceSkill = idResourceSkill;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

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
	
	
	

}
