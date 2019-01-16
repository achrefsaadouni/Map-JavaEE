package tn.esprit.Map.interfaces;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.JobRequest;
import tn.esprit.Map.persistences.Project;

@Remote
public interface ProjectRemote {
	public List<Project> getAllProjects();
	public List<Project>  getAllProjectByClient(int clientId);
	public List<Project>  getProjectsByDate(String startDate, String endDate);
	public List<Project>  getProjectsByAdress(String address);
	public Project getProjectById(int projectId);
	public List<Client> getClientByProject(int projectId);
	public int addProject(Project project,int clientId);
	public int addProjectAngular(Project project , int clientId);
	public String updateProject(Project project);
	public String deleteProject(int projectId);
	public String archiveProject() throws ParseException;
	public String archiveOneProject(Project project);
	public String assignProjectToClient(int clientId,int projectId) ;
	public String assignProjectToClientAngular(int clientId, int projectId) ;
	public String sumAmountProject(String startDate, String endDate);
	
	
}
