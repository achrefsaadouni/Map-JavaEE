package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Request implements Serializable {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	private String requestedProfil;
	private String experienceYear;
	private String educationScolarity;
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
	private Client client;
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequestedProfil() {
		return requestedProfil;
	}

	public void setRequestedProfil(String requestedProfil) {
		this.requestedProfil = requestedProfil;
	}

	public String getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(String experienceYear) {
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

	public Request() {
		super();
	}
	
	

}
