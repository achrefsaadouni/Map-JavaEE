package tn.esprit.webservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Map.interfaces.ProjectRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectType;
import tn.esprit.utlities.AuthenticatedUser;
import tn.esprit.utlities.Secured;


@Path("/projects")
@ManagedBean
public class ProjectWebService {
	@EJB
	ProjectRemote projectRemote;
	@Inject
	@AuthenticatedUser
	Person authenticatedUser;

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(@QueryParam("idClient") String idClient,@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate,@QueryParam("projectId") String projectId) throws ParseException {
		
		if ((idClient == null)&&(startDate==null)&&(endDate ==null)&&(projectId ==null)) {
			if (projectRemote.getAllProjects() == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (projectRemote.getAllProjects().size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("\"No data\"").build();

			else
				return Response.ok(projectRemote.getAllProjects(), MediaType.APPLICATION_JSON).build();
		} else if((idClient != null)&&(startDate==null)&&(endDate ==null)&& (projectId ==null)) {
			if (projectRemote.getAllProjectByClient(Integer.parseInt(idClient)) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (projectRemote.getAllProjectByClient(Integer.parseInt(idClient)).size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("\"No data\"").build();

			else
				return Response.ok(projectRemote.getAllProjectByClient(Integer.parseInt(idClient)), MediaType.APPLICATION_JSON).build();
		}
		else if ((idClient == null)&&(startDate!=null)&&(endDate !=null)&& (projectId ==null)){
			if (projectRemote.getProjectsByDate(startDate,endDate) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (projectRemote.getProjectsByDate(startDate,endDate).size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("\"No data\"").build();

			else
				return Response.ok(projectRemote.getProjectsByDate(startDate,endDate), MediaType.APPLICATION_JSON).build();
		}
		else{
			if (projectRemote.getProjectById(Integer.parseInt(projectId)) == null)
				return Response.status(Response.Status.NOT_FOUND).build();
			if (projectRemote.getProjectById(Integer.parseInt(projectId)) == null)
				return Response.status(Response.Status.BAD_REQUEST).entity("\"No data\"").build();
			else
				return Response.ok(projectRemote.getProjectById(Integer.parseInt(projectId)), MediaType.APPLICATION_JSON).build();
			
		}


	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getClientByProject")
	public List<Client> getProjects(@QueryParam("projectId") String projectId){
		return projectRemote.getClientByProject(Integer.parseInt(projectId));
	}
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/calculMontant")
	public String getsumAmountProjects(@QueryParam("startDate") String startDate,@QueryParam("endDate") String endDate)
	{
		return projectRemote.sumAmountProject(startDate, endDate);
	}
	
	//@Secured
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int postProject(@QueryParam("idClient") String idClient, @QueryParam("idProject") String idProject,Project project) throws ParseException {
//		if (authenticatedUser.getRoleT().equals("Admin"))
//		{
		if ((idClient != null) && (idProject == null)) {
			
			 return  projectRemote.addProject(project,Integer.parseInt(idClient)) ; }
//		} else if((idClient != null) && (idProject != null)) {
//			return projectRemote.assignProjectToClient(Integer.parseInt(idClient), Integer.parseInt(idProject));
//		}
	
		//}
	//	return "Access denied";
		return -1 ;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/assignPtoCAngular")
	public String assignProjectToClientAngular(@QueryParam("idClient") String idClient, @QueryParam("idProject") String idProject) {
		return projectRemote.assignProjectToClientAngular( Integer.parseInt(idClient), Integer.parseInt(idProject) );

	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/addProjectAngular")
	public int addProjectAngular(Project project ,@QueryParam("idClient") String idClient )  { 
		return projectRemote.addProjectAngular(project , Integer.parseInt(idClient)) ;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/ArchiveProject")
	public String archiveProject(Project project){
		return projectRemote.archiveOneProject(project);
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
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getProjectsByAddress")
	public List<Project> getProjectsByAddress(@QueryParam("address") String address){
		return projectRemote.getProjectsByAdress(address);
	}

}
