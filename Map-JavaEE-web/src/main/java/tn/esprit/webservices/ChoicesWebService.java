package tn.esprit.webservices;

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

import tn.esprit.Map.interfaces.ChoiceServices;
import tn.esprit.Map.persistences.Choices;
import tn.esprit.Map.persistences.Modules;

@Path("/choices")

public class ChoicesWebService {
	
	@EJB
	ChoiceServices ChoiceServices;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("question/{id}")
	public Response ShowQuestionsByid(@PathParam("id") int idQuestion) {
		if (ChoiceServices.ShowChoicesByQuestion(idQuestion) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (ChoiceServices.ShowChoicesByQuestion(idQuestion).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(ChoiceServices.ShowChoicesByQuestion(idQuestion), MediaType.APPLICATION_JSON).build();

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("new/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Choices Addchoices(@PathParam("id") int idQuestion,Choices choice)
	{
		return ChoiceServices.addChoiceToQuestion(idQuestion, choice);
	}
	
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{id}")
	public Response DeleteModule(@PathParam("id") int id)
	{
		if(ChoiceServices.DeleteChoice(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you request id ").build();
			
		}
		 return Response.ok(Response.Status.ACCEPTED).entity("your question Has been deleted").build();
		
	}
	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response UpdateRequest(Choices choice )
	{
		if(ChoiceServices.updateChoice(choice) == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(ChoiceServices.updateChoice(choice), MediaType.APPLICATION_JSON).build();
	}
	
	
	
}
