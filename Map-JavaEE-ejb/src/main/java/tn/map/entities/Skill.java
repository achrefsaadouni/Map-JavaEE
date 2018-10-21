package tn.map.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Skill implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String nameSkill;
	private String descriptionSkill;
	private float rateSkill;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameSkill() {
		return nameSkill;
	}

	public void setNameSkill(String nameSkill) {
		this.nameSkill = nameSkill;
	}

	public String getDescriptionSkill() {
		return descriptionSkill;
	}

	public void setDescriptionSkill(String descriptionSkill) {
		this.descriptionSkill = descriptionSkill;
	}

	public float getRateSkill() {
		return rateSkill;
	}

	public void setRateSkill(float rateSkill) {
		this.rateSkill = rateSkill;
	}

}
