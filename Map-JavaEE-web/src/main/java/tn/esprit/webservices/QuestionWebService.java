package tn.esprit.webservices;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.QuestionService;
import tn.esprit.Map.persistences.Modules;
import tn.esprit.Map.persistences.Qcm;

@Path("/question")
public class QuestionWebService {

	@EJB
	QuestionService QuestionService;
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ShowQuestions() {
		if (QuestionService.ShowAll() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (QuestionService.ShowAll().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(QuestionService.ShowAll(), MediaType.APPLICATION_JSON).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("module/{id}")
	public Response ShowQuestionsByid(@PathParam("id") int id) {
		if (QuestionService.ShowByModule(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (QuestionService.ShowByModule(id).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(QuestionService.ShowByModule(id), MediaType.APPLICATION_JSON).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Qcm QuestionsByid(@PathParam("id") int id) {
		return QuestionService.GetQuestionById(id);

	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("new/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Qcm AddModuleService(@PathParam("id") int id,Qcm Question)
	{
		return QuestionService.addQuestionToModule(id, Question);
	}
	
	
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{id}")
	public Response DeleteModule(@PathParam("id") int id)
	{
		if(QuestionService.DeleteQuestion(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you request id ").build();
			
		}
		 return Response.ok(Response.Status.ACCEPTED).entity("your question Has been deleted").build();
		
	}
}
