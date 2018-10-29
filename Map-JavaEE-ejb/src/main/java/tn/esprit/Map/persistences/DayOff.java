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


@Entity
public class DayOff implements Serializable,Comparable<DayOff> {
	@Id
	@GeneratedValue
	private int id;
	
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	
	private String reason;
	
	
	@Enumerated(EnumType.STRING)
	private TypeDayOff typeDayOff;
	
	
	@Enumerated(EnumType.STRING)
	private StateType stateType;
	
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

	@Override
	public int compareTo(DayOff o) {
	
		return this.endDate.compareTo(o.endDate);
	}
	
}
