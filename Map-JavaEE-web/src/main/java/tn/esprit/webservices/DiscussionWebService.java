package tn.esprit.webservices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.DiscussionServiceLocal;
import tn.esprit.Map.persistences.DiscussionChat;
import tn.esprit.Map.persistences.MessageChat;

@Path("/DiscussionChat")
@ManagedBean
public class DiscussionWebService {
	
	@EJB
	DiscussionServiceLocal discussionService;
	DiscussionChat discussionChat;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	@Path("/addDiscussionChat")
	public int addDiscussion(DiscussionChat disc) {	
		int discId = discussionService.addDiscussion(disc);
		return discId;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDiscussionByPerson/{person1ID}")
	public Response getDiscussions(@PathParam("person1ID") String personID) {
		if (discussionService.getListDiscussionByPerson(Integer.parseInt(personID)) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (discussionService.getListDiscussionByPerson(Integer.parseInt(personID)).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

		else
			return Response.ok(discussionService.getListDiscussionByPerson(Integer.parseInt(personID)), MediaType.APPLICATION_JSON).build();

	}
}
