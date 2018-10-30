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
	public Project getProjectById(int projectId);
	public String addProject(Project project);
	public String updateProject(Project project);
	public String deleteProject(int projectId);
	public String archiveProject() throws ParseException;
	public String assignProjectToClient(int clientId,int projectId) ;
	public String sumAmountProject(String startDate, String endDate);
	
	
}
