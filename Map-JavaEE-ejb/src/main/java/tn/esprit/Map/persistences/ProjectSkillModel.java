package tn.esprit.Map.persistences;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectSkillModel {
	@JsonProperty("skillName")
	private String skillName ;
	@JsonProperty("percentage")
	private int percentage ;
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
}
