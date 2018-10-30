package tn.esprit.webservices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
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
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@ManagedBean
@Path("/skill")
public class SkillWebService {

	@EJB
	SkillRemote skillRemote;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("addSkill")
	public String AddSkill(Skill s) {
		skillRemote.AddSkill(s);
		return "Add avec Succ";
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
	public String UpdateSkill(Skill s) {
		if (skillRemote.UpdateSkill(s) == false) {
			return "impossible";
		}
		return "updated";
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("RateSkill")
	public String RateSkill(@QueryParam("resourceId") String resourceId , @QueryParam("skillId") String skillId , @QueryParam("rate") String Rate) {
		if (skillRemote.RateSkill(Integer.parseInt(resourceId) , Integer.parseInt(skillId),Float.parseFloat(Rate)) == false) {
			return "impossible";
		}
		return "Rated";
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("DeleteSkill")
	public String DeleteSkill(@QueryParam("skillId") String skillId) {
		skillRemote.DeleteSkill(Integer.parseInt(skillId));
		return "Deleted";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("addSkillResource")
	public String AddSkillResource(@QueryParam("skillId") String skillId, @QueryParam("resourceId") String resourceId) {
		Boolean insert = skillRemote.AddSkillResource(Integer.parseInt(skillId), Integer.parseInt(resourceId));
		if (insert == false) {
			return "déjà existe";
		}
		return "added";
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("deleteSkillResource")
	public String DeleteSkillResource(@QueryParam("skillId") String skillId,
			@QueryParam("resourceId") String resourceId) {
		Boolean insert = skillRemote.DeleteSkillResource(Integer.parseInt(skillId), Integer.parseInt(resourceId));
		if (insert == false) {
			return "n'existe pas déjà";
		}
		return "deleted";
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
