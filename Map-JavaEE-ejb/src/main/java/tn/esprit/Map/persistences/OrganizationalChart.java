package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class OrganizationalChart implements Serializable {
	@Id
	@GeneratedValue
	private int id;
	private String directionalLevel;
	private String programName;
	private String projectResponsible;
	private String projectName;
	private String clientName;
	private String accountManager;
	private String nameAssignmentManagerClient;
	@OneToMany(mappedBy = "organizationalChart")
	private List<Project> projects;
	@ManyToMany
	private List<Resource> resources;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirectionalLevel() {
		return directionalLevel;
	}

	public void setDirectionalLevel(String directionalLevel) {
		this.directionalLevel = directionalLevel;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProjectResponsible() {
		return projectResponsible;
	}

	public void setProjectResponsible(String projectResponsible) {
		this.projectResponsible = projectResponsible;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getNameAssignmentManagerClient() {
		return nameAssignmentManagerClient;
	}

	public void setNameAssignmentManagerClient(String nameAssignmentManagerClient) {
		this.nameAssignmentManagerClient = nameAssignmentManagerClient;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
