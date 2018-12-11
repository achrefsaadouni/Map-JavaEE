package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonRootName;


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@JsonRootName("request")
public class Request implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	@Enumerated(EnumType.STRING)
	private WorkType requestedProfil;
	private int experienceYear;
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
	private int accept;
	private float daysMondate;
	@ManyToOne
	@JsonIgnore
	private Administrator administrator;
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Client client;
	@OneToOne
	private Resource suggessedResource; 
	private boolean traiter ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WorkType getRequestedProfil() {
		return requestedProfil;
	}

	public void setRequestedProfil(WorkType requestedProfil) {
		this.requestedProfil = requestedProfil;
	}

	public int getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(int experienceYear) {
		this.experienceYear = experienceYear;
	}

	public String getEducationScolarity() {
		return educationScolarity;
	}

	public void setEducationScolarity(String educationScolarity) {
		this.educationScolarity = educationScolarity;
	}


	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getDepositTime() {
		return depositTime;
	}

	public void setDepositTime(Date depositTime) {
		this.depositTime = depositTime;
	}

	public Date getStartDateMondate() {
		return startDateMondate;
	}

	public void setStartDateMondate(Date startDateMondate) {
		this.startDateMondate = startDateMondate;
	}

	public Date getEndDateMondate() {
		return endDateMondate;
	}

	public void setEndDateMondate(Date endDateMondate) {
		this.endDateMondate = endDateMondate;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	

	public int getAccept() {
		return accept;
	}

	public void setAccept(int accept) {
		this.accept = accept;
	}

	public float getDaysMondate() {
		return daysMondate;
	}

	public void setDaysMondate(float daysMondate) {
		this.daysMondate = daysMondate;
	}

	public Request() {
		super();
	}

	public boolean isTraiter() {
		return traiter;
	}

	public void setTraiter(boolean traiter) {
		this.traiter = traiter;
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
		result = prime * result + experienceYear;
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
		if (experienceYear != other.experienceYear)
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
		if (requestedProfil != other.requestedProfil)
			return false;
		if (startDateMondate == null) {
			if (other.startDateMondate != null)
				return false;
		} else if (!startDateMondate.equals(other.startDateMondate))
			return false;
		return true;
	}

	public Resource getSuggessedResource() {
		return suggessedResource;
	}

	public void setSuggessedResource(Resource suggessedResource) {
		this.suggessedResource = suggessedResource;
	}



	
	 
	

}
