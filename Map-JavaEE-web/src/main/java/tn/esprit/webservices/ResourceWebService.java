package tn.esprit.webservices;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import tn.esprit.Map.interfaces.ResourceRemote;
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Role;
import tn.esprit.Map.persistences.Skill;
import tn.esprit.Map.services.ResourceService;
import tn.esprit.utlities.AuthenticatedUser;
import tn.esprit.utlities.Secured;

@ManagedBean
@Path("/Resources")
public class ResourceWebService {

	// ResourceService resourceService = new ResourceService();

	@EJB
	ResourceRemote resourceRemote;
	@Inject
	@AuthenticatedUser
	Person authenticatedUser;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("test")
	public String testWs() {
		return "hello test";
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("add")
	@Secured
	public String AddResource(Resource r) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		resourceRemote.AddResource(r);
		return "Add avec Succ";}
		return "access denied";
		
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("update")
	@Secured
	public String UpdateResource(Resource newResource) {
			if (authenticatedUser.getRoleT() == Role.Admin)
			{
		
	Resource resource = resourceRemote.getResourceById(newResource.getId());
	resource.setLastName(newResource.getLastName());
	resource.setFirstName(newResource.getFirstName());
	resource.setLogin(newResource.getLogin());
	resource.setPassword(newResource.getPassword());
	resource.setAvailability(newResource.getAvailability());
	resource.setBusinessSector(newResource.getBusinessSector());
	resource.setCv(newResource.getCv());
	resource.setJobType(newResource.getJobType());
	resource.setNote(newResource.getNote());
	resource.setPicture(newResource.getPicture());
	resource.setSalary(newResource.getSalary());
	resource.setSeniority(newResource.getSeniority());
	resource.setWorkProfil(newResource.getWorkProfil());
	resource.setProject(newResource.getProject());
	resource.setInBox(newResource.getInBox());
	resource.setArchived(newResource.isArchived());
	resourceRemote.UpdateResource(newResource);
	return "updated";}
	return "Access Denied";

	}
	
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("archive")
	@Secured
	public String ArchiveResource(@QueryParam("resourceId") String resourceId) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		if(resourceRemote.ArchiveResource(Integer.parseInt(resourceId)) == false){
			return "il n'existe pas";
		}
		return "Archie done";
	}
		return "Access Denied";}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("Unarchive")
	public String UnArchiveResource(@QueryParam("resourceId") String resourceId) {
		if(resourceRemote.UnArchiveResource(Integer.parseInt(resourceId)) == false){
			return "il n'existe pas";
		}
		return "UnArchie done";
	}
	

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("AffectResourceToProject")
	@Secured
	public String AffectResourceToProject(@QueryParam("resourceId") String resourceId , @QueryParam("projectId") String projectId) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		if(resourceRemote.AffectResourceToProject(Integer.parseInt(resourceId), Integer.parseInt(projectId)) == true)
		return "Affected"; 
		else
		return "impossible";}
		return "Access denied";
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listeResourceArchive")
	public Response getResourceArchive() {
		return Response.ok(resourceRemote.getResourceArchive(), MediaType.APPLICATION_JSON).build();
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("liste")
	public Response ListeResource() {
		return Response.ok(resourceRemote.listResource(), MediaType.APPLICATION_JSON).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listeParMoyenne")
	public Response ListResourceParMoyenne() {
		return Response.ok(resourceRemote.ListResourceParMoyenne(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("resourceById")
	public Response getResourceById(@QueryParam("resourceId") String resourceId) {
		if(resourceRemote.getResourceById(Integer.parseInt(resourceId)) == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("No data").build();
		}
		return Response.ok(resourceRemote.getResourceById(Integer.parseInt(resourceId)), MediaType.APPLICATION_JSON).build();
	}
	
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("moyenneResource")
	public String moyenneResource(@QueryParam("resourceId") String resourceId) {
		if(resourceRemote.moyenneResource(Integer.parseInt(resourceId)) == 0){
			return "cette resource ne poséde pas de note";
		}
		resourceRemote.moyenneResource(Integer.parseInt(resourceId));
		return String.valueOf(resourceRemote.moyenneResource(Integer.parseInt(resourceId)));
	}

	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("noteResource")
	@Secured
	public String noteResource(@QueryParam("resourceId") String resourceId , @QueryParam("clientId") String clientId ,@QueryParam("note") String note) {
		if (authenticatedUser.getRoleT() == Role.Client)
		{
		if(resourceRemote.noteResource(Integer.parseInt(resourceId),Integer.parseInt(clientId) , Float.parseFloat(note)) == false){
			return "pas de note";
		}
		resourceRemote.noteResource(Integer.parseInt(resourceId) ,Integer.parseInt(clientId), Float.parseFloat(note));
		return "note ajoutée";}
		return "Access denied";
	}

	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("UpdateAffectation")
	@Secured
	public String UpdateAffectation(@QueryParam("resourceId") String resourceId ,@QueryParam("projetId") String projetId ) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		if(resourceRemote.UpdateAffectation(Integer.parseInt(resourceId) ,Integer.parseInt(projetId)) == false){
			return "pas de note";
		}
		resourceRemote.UpdateAffectation(Integer.parseInt(resourceId) ,Integer.parseInt(projetId));
		return "Affectation updated";}
		return "Access denied";
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("DeleteAffectation")
	@Secured
	public String DeleteAffectation(@QueryParam("resourceId") String resourceId ) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		if(resourceRemote.DeleteAffectation(Integer.parseInt(resourceId)) == false){
			return "pas de projet";
		}
		resourceRemote.DeleteAffectation(Integer.parseInt(resourceId));
		return "Affectation deleted";}
		return "Access denied";
	}

}
