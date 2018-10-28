package tn.esprit.webservices;



import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.MessageServiceRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Message;
import tn.esprit.Map.services.MessageService;


@Path("/messages")
@ManagedBean
public class MessageWebService {
	@EJB
	MessageServiceRemote messgeService;
	Message message =new Message();
	    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewMessages() {
		if (messgeService.AllMessage() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (messgeService.AllMessage().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

		else
			return Response.ok(messgeService.AllMessage(), MediaType.APPLICATION_JSON).build();

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/addMessage")
	public int addMessage(Message message) {	
		int MessageId = messgeService.addMessage(message);
		return MessageId;
	}

}
