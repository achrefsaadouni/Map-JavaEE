package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement(name="request")
public class Request implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String requestedProfil;
	private String experienceYear;
	private String educationScolarity;
	@OneToOne
	private Project project;
	private String manager;
	@Temporal(TemporalType.DATE)
	private Date depositDate;
	@Temporal(TemporalType.TIME)
	private Date depositTime;     
	@Temporal(TemporalType.DATE)
	private Date startDateMondate;
	@Temporal(TemporalType.DATE)
	private Date endDateMondate;
	@ManyToOne
	private Administrator administrator;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Client client;
	

	
	public int getId() {
		return id;
	}
	@XmlAttribute(name="id",required=true)
	public void setId(int id) {
		this.id = id;
	}

	public String getRequestedProfil() {
		return requestedProfil;
	}
	@XmlElement(name="RequestedProfil")
	public void setRequestedProfil(String requestedProfil) {
		this.requestedProfil = requestedProfil;
	}

	public String getExperienceYear() {
		return experienceYear;
	}
	@XmlElement(name="ExperienceYear")
	public void setExperienceYear(String experienceYear) {
		this.experienceYear = experienceYear;
	}

	public String getEducationScolarity() {
		return educationScolarity;
	}
	@XmlElement(name="EducationScolarity")
	public void setEducationScolarity(String educationScolarity) {
		this.educationScolarity = educationScolarity;
	}


	public Project getProject() {
		return project;
	}
	@XmlElement(name="Project")
	public void setProject(Project project) {
		this.project = project;
	}

	public String getManager() {
		return manager;
	}
	@XmlElement(name="Manager")
	public void setManager(String manager) {
		this.manager = manager;
	}

	public Date getDepositDate() {
		return depositDate;
	}
	@XmlElement(name="DepositDate")
	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getDepositTime() {
		return depositTime;
	}
	@XmlElement(name="DepositTime")
	public void setDepositTime(Date depositTime) {
		this.depositTime = depositTime;
	}

	public Date getStartDateMondate() {
		return startDateMondate;
	}
	@XmlElement(name="StartDateMondate")
	public void setStartDateMondate(Date startDateMondate) {
		this.startDateMondate = startDateMondate;
	}

	public Date getEndDateMondate() {
		return endDateMondate;
	}
	@XmlElement(name="EndDateMondate")
	public void setEndDateMondate(Date endDateMondate) {
		this.endDateMondate = endDateMondate;
	}

	public Administrator getAdministrator() {
		return administrator;
	}
	@XmlElement(name="Administrator")
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
    
	public Client getClient() {
		return client;
	}
	@XmlElement(name="Client")
	public void setClient(Client client) {
		this.client = client;
	}

	public Request() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((administrator == null) ? 0 : administrator.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((depositDate == null) ? 0 : depositDate.hashCode());
		result = prime * result + ((depositTime == null) ? 0 : depositTime.hashCode());
		result = prime * result + ((educationScolarity == null) ? 0 : educationScolarity.hashCode());
		result = prime * result + ((endDateMondate == null) ? 0 : endDateMondate.hashCode());
		result = prime * result + ((experienceYear == null) ? 0 : experienceYear.hashCode());
		result = prime * result + id;
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((requestedProfil == null) ? 0 : requestedProfil.hashCode());
		result = prime * result + ((startDateMondate == null) ? 0 : startDateMondate.hashCode());
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
		Request other = (Request) obj;
		if (administrator == null) {
			if (other.administrator != null)
				return false;
		} else if (!administrator.equals(other.administrator))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (depositDate == null) {
			if (other.depositDate != null)
				return false;
		} else if (!depositDate.equals(other.depositDate))
			return false;
		if (depositTime == null) {
			if (other.depositTime != null)
				return false;
		} else if (!depositTime.equals(other.depositTime))
			return false;
		if (educationScolarity == null) {
			if (other.educationScolarity != null)
				return false;
		} else if (!educationScolarity.equals(other.educationScolarity))
			return false;
		if (endDateMondate == null) {
			if (other.endDateMondate != null)
				return false;
		} else if (!endDateMondate.equals(other.endDateMondate))
			return false;
		if (experienceYear == null) {
			if (other.experienceYear != null)
				return false;
		} else if (!experienceYear.equals(other.experienceYear))
			return false;
		if (id != other.id)
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (requestedProfil == null) {
			if (other.requestedProfil != null)
				return false;
		} else if (!requestedProfil.equals(other.requestedProfil))
			return false;
		if (startDateMondate == null) {
			if (other.startDateMondate != null)
				return false;
		} else if (!startDateMondate.equals(other.startDateMondate))
			return false;
		return true;
	}
	
	 
	

}
