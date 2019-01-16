package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("DayOff")
@Entity
public class DayOff implements Serializable {
	@Id
	@GeneratedValue
	@JsonProperty("id")

	private int id;
	
	@JsonProperty("startDate")

	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@JsonProperty("endDate")

	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@JsonProperty("reason")

	private String reason;
	
	@JsonProperty("color")

	private String color;
	
	@JsonProperty("typeDayOff")

	@Enumerated(EnumType.STRING)
	private TypeDayOff typeDayOff;
	
	@JsonProperty("stateType")

	@Enumerated(EnumType.STRING)
	private StateType stateType;
	
	@JsonIgnore
	@JsonProperty("resources")

	@ManyToMany(mappedBy="dayOffs")
	private List<Resource> Resources;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public TypeDayOff getTypeDayOff() {
		return typeDayOff;
	}

	public void setTypeDayOff(TypeDayOff typeDayOff) {
		this.typeDayOff = typeDayOff;
	}

	public StateType getStateType() {
		return stateType;
	}

	public void setStateType(StateType stateType) {
		this.stateType = stateType;
	}

	
	public List<Resource> getResources() {
		return Resources;
	}
	public void setResources(List<Resource> resources) {
		Resources = resources;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}
