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

import tn.esprit.Map.interfaces.MessageChatServiceLocal;
import tn.esprit.Map.persistences.Message;
import tn.esprit.Map.persistences.MessageChat;

@Path("/messagesChat")
@ManagedBean
public class MessageChatWebService {

	@EJB
	MessageChatServiceLocal messageChatService;
	MessageChat messageChat;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getMessagesByDisscussion/{id}")
	public Response getAllMessageByDiscussion(@PathParam("id") String discussionID) {
		if (messageChatService.getMessageByDiscussion(Integer.parseInt(discussionID)) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (messageChatService.getMessageByDiscussion(Integer.parseInt(discussionID)).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

		else
			return Response.ok(messageChatService.getMessageByDiscussion(Integer.parseInt(discussionID)), MediaType.APPLICATION_JSON).build();

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	@Path("/addMessageChat")
	public int addMessage(MessageChat message) {	
		int MessageId = messageChatService.addMessage(message);
		return MessageId;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("/getLastMsg/{id}")
	public MessageChat getLastMessage(@PathParam("id") String discussionID) {	
		int discussionId = Integer.parseInt(discussionID); 
		return messageChatService.getLastMsg(discussionId);
	}
	
}
