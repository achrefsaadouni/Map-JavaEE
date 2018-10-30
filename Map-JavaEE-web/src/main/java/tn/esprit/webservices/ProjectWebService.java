package tn.esprit.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.ProjectRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectType;

@Path("/projects")
@ManagedBean
public class ProjectWebService {
	@EJB
	ProjectRemote projectRemote;

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(@QueryParam("idClient") String idClient,@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate) throws ParseException {
		
		if ((idClient == null)&&(startDate==null)&&(endDate ==null)) {
			if (projectRemote.getAllProjects() == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (projectRemote.getAllProjects().size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("\"No data\"").build();

			else
				return Response.ok(projectRemote.getAllProjects(), MediaType.APPLICATION_JSON).build();
		} else if((idClient != null)&&(startDate==null)&&(endDate ==null) ) {
			if (projectRemote.getAllProjectByClient(Integer.parseInt(idClient)) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (projectRemote.getAllProjectByClient(Integer.parseInt(idClient)).size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("\"No data\"").build();

			else
				return Response.ok(projectRemote.getAllProjectByClient(Integer.parseInt(idClient)), MediaType.APPLICATION_JSON).build();
		}
		else /*if((idClient == null)&&(startDate!=null)&&(endDate !=null))*/{
			if (projectRemote.getProjectsByDate(startDate,endDate) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (projectRemote.getProjectsByDate(startDate,endDate).size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("No data").build();

			else
				return Response.ok(projectRemote.getProjectsByDate(startDate,endDate), MediaType.APPLICATION_JSON).build();
		}

	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/calculMontant")
	public String getsumAmountProjects(@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate)
	{
		return projectRemote.sumAmountProject(startDate, endDate);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String postProject(@QueryParam("idClient") String idClient, @QueryParam("idProject") String idProject,
			Project project) throws ParseException {
		if ((idClient == null) && (idProject == null)) {
			return projectRemote.addProject(project);
		} else {
			return projectRemote.assignProjectToClient(Integer.parseInt(idClient), Integer.parseInt(idProject));
		}
	}

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(Project project) {
		return projectRemote.updateProject(project);
	}

	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{idProject}")
	public String deleteProject(@PathParam("idProject") String idProject) {
		return projectRemote.deleteProject(Integer.parseInt(idProject));
	}

}
