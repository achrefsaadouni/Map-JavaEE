package tn.esprit.webservices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.ResourceRemote;
import tn.esprit.Map.interfaces.SkillRemote;
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Role;
import tn.esprit.Map.persistences.Skill;
import tn.esprit.utlities.AuthenticatedUser;
import tn.esprit.utlities.Secured;

@ManagedBean
@Path("/skill")
public class SkillWebService {

	@EJB
	SkillRemote skillRemote;

	@Inject
	@AuthenticatedUser
	Person authenticatedUser;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("addSkill")
	@Secured
	public String AddSkill(Skill s) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		skillRemote.AddSkill(s);
		return "Add avec Succ";}
		return "Access Denied";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listSkills")
	public Response ListSkills() {
		if (skillRemote.ListSkills() == null) {
			return javax.ws.rs.core.Response.status(Response.Status.BAD_REQUEST).entity("No data").build();
		}
		return Response.ok(skillRemote.ListSkills(), MediaType.APPLICATION_JSON).build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("updateSkill")
	@Secured
	public String UpdateSkill(Skill s) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		if (skillRemote.UpdateSkill(s) == false) {
			return "impossible";
		}
		return "updated";}
		return "Access Denied";
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("RateSkill")
	@Secured
	public String RateSkill(@QueryParam("resourceId") String resourceId , @QueryParam("skillId") String skillId , @QueryParam("rate") String Rate) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		if (skillRemote.RateSkill(Integer.parseInt(resourceId) , Integer.parseInt(skillId),Float.parseFloat(Rate)) == false) {
			return "impossible";
		}
		return "Rated";}
		return "Access denied";
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("DeleteSkill")
	@Secured
	public String DeleteSkill(@QueryParam("skillId") String skillId) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		skillRemote.DeleteSkill(Integer.parseInt(skillId));
		return "Deleted";}
		return "Access denied";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("addSkillResource")
	@Secured
	public String AddSkillResource(@QueryParam("skillId") String skillId, @QueryParam("resourceId") String resourceId) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		
		Boolean insert = skillRemote.AddSkillResource(Integer.parseInt(skillId), Integer.parseInt(resourceId));
		if (insert == false) {
			return "déjà existe";
		}
		return "added";}
		return "Access denied";
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("deleteSkillResource")
	@Secured
	public String DeleteSkillResource(@QueryParam("skillId") String skillId,
			@QueryParam("resourceId") String resourceId) {
		if (authenticatedUser.getRoleT() == Role.Admin)
		{
		Boolean insert = skillRemote.DeleteSkillResource(Integer.parseInt(skillId), Integer.parseInt(resourceId));
		if (insert == false) {
			return "n'existe pas déjà";
		}
		return "deleted";}
		return "Access denied";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("orderSkillsOfResource")
	public Response orderSkillsOfResource(@QueryParam("resourceId") String resourceId) {
		if (skillRemote.orderSkillsOfResource(Integer.parseInt(resourceId)).size() == 0) {
			return javax.ws.rs.core.Response.status(Response.Status.BAD_REQUEST).entity("No data").build();
		}
		return Response.ok(skillRemote.orderSkillsOfResource(Integer.parseInt(resourceId)), MediaType.APPLICATION_JSON)
				.build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("orderResourcesOfSkill")
	public Response orderResourcesOfSkill(@QueryParam("skillId") String skillId) {
		if (skillRemote.orderResourcesOfSkill(Integer.parseInt(skillId)).size() == 0) {
			return javax.ws.rs.core.Response.status(Response.Status.BAD_REQUEST).entity("No data").build();
		}
		return Response.ok(skillRemote.orderResourcesOfSkill(Integer.parseInt(skillId)), MediaType.APPLICATION_JSON)
				.build();

	}
}
