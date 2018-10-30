package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Contract implements Serializable{
	@Id
	@GeneratedValue
	@JsonProperty("idContract")
	private int id;
	@Temporal(TemporalType.DATE)
	@JsonProperty("StartDate")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonProperty("EndDate")
	private Date endDate;
	@JsonProperty("client")
	@ManyToOne
	private Client client;
	private int archived;
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
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public int getArchived() {
		return archived;
	}
	public void setArchived(int archived) {
		this.archived = archived;
	}
	
	
	
	
}
