package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.JobRequest;
import tn.esprit.Map.persistences.Project;

@Remote
public interface ProjectRemote {
	public List<Project> getAllProjects();
	public List<Project>  getAllProjectByClient(int clientId);
	public Project getProjectById(int projectId);
	public int addProject(Project project);
	public String updateProject(Project project);
	public String deleteProject(int projectId);
	public String assignProjectToClient(int projectId , Client client);	
}
