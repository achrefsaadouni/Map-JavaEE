package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
public class Skill implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdSkill;
	
	private String NameSkill;
	
	private String DescriptionSkill;
	
	private float RateSkill;
	
	@ManyToMany(mappedBy="skills")
	private List<Resource> Resources;
	
	
	
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
	
	
	
	
	public String getDescriptionSkill() {
		return DescriptionSkill;
	}
	public void setDescriptionSkill(String descriptionSkill) {
		DescriptionSkill = descriptionSkill;
	}
	
	
	
	
	public float getRateSkill() {
		return RateSkill;
	}
	public void setRateSkill(float rateSkill) {
		RateSkill = rateSkill;
	}
	
	
	public List<Resource> getResources() {
		return Resources;
	}
	public void setResources(List<Resource> resources) {
		Resources = resources;
	}
	
}
