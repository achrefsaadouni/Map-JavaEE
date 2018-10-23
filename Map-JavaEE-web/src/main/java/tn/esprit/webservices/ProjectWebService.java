package tn.esprit.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.services.ProjectService;

@Path("/projects")
public class ProjectWebService {
	ProjectService projectService = new ProjectService();
	@GET
	@Produces("application/json")
	public String getProjectById(int projectId)
	{
		projectId = 1 ;
		return projectService.getProjectById(projectId);
	}

}
