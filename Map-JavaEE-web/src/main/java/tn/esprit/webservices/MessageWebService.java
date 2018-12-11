package tn.esprit.webservices;



import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.MessageServiceRemote;
import tn.esprit.Map.persistences.Message;
import tn.esprit.Map.persistences.Person;


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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	@Path("/addMessage/{idPerson}")
	public int addMessage(Message message,@PathParam("idPerson") String personID) {	
		int MessageId = messgeService.addMessage(message,Integer.parseInt(personID));
		return MessageId;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	@Path("/addMessage2")
	public int addMessage2(Message message) {	
		int MessageId = messgeService.addMessage2(message);
		return MessageId;
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteMessage/{idMessage}")
	public int deleteProject(@PathParam("idMessage") String messageID){
		return messgeService.deleteMessage(Integer.parseInt(messageID));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getMessagesSortedByPerson")
	public Response ViewMessagesSortedByPerson() {
		if (messgeService.allMessageSortedByPerson() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (messgeService.allMessageSortedByPerson().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

		else
			return Response.ok(messgeService.allMessageSortedByPerson(), MediaType.APPLICATION_JSON).build();

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getMessagesById/{id}")
	public Response getMessageByID(@PathParam("id") String messageID) {
		
		return Response.ok(messgeService.getMessageByID(Integer.parseInt(messageID)), MediaType.APPLICATION_JSON).build();

	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	@Path("/updateNotePersonByMessage")
	public String updateNotePersonByMessage(Person personne) {	
		return messgeService.updateMessage(personne);
	}

}
