package tn.esprit.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.ProjectRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectType;

@Path("/projects")
@ManagedBean
public class ProjectWebService {
	@EJB
	ProjectRemote projectRemote;
	Project project = new Project();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects() {
		if (projectRemote.getAllProjects() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (projectRemote.getAllProjects().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("No data").build();

		else
			return Response.ok(projectRemote.getAllProjects(), MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idClient}")
	public Response getProjectsByClient(@PathParam("idClient") String idClient) {
		if (projectRemote.getAllProjectByClient(Integer.parseInt(idClient)) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (projectRemote.getAllProjectByClient(Integer.parseInt(idClient)).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("No data").build();

		else
			return Response.ok(projectRemote.getAllProjectByClient(Integer.parseInt(idClient)), MediaType.APPLICATION_JSON).build();

	}	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int addProject(Project project) {	
		int projectId = projectRemote.addProject(project);
		return projectId;
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{idProject}")
	public String assignProjectToClient(@PathParam("idProject") String idProject,Client client) {
		 return projectRemote.assignProjectToClient(Integer.parseInt(idProject), client);

	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(Project project){
		return projectRemote.updateProject(project);
	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{idProject}")
	public String deleteProject(@PathParam("idProject") String idProject){
		return projectRemote.deleteProject(Integer.parseInt(idProject));
	}
	
}
