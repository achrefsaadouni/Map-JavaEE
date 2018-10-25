package tn.esprit.webservices;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.JobRequestLocal;
import tn.esprit.Map.persistences.JobRequest;

@Path("/jobrequest")
@ManagedBean
public class JobRequestWebService {
	@EJB
	JobRequestLocal JobRequestService;

	


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewRequestdJobs() {
		if (JobRequestService.ViewAllRequested() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (JobRequestService.ViewAllRequested().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(JobRequestService.ViewAllRequested(), MediaType.APPLICATION_JSON).build();

	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void AddJobRequest(JobRequest st)
	{
		JobRequestService.AddJobRequest(st);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{id}")
	public Response DeleteRequest(@PathParam("id") int id)
	{
		if(JobRequestService.DeleteJobRequest(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you request id ").build();
			
		}
		 return Response.ok(Response.Status.ACCEPTED).entity("your request Has been deleted").build();
		
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ShowMyReq/{id}")
	public Response ShowMyRequest(@PathParam("id") int id)
	{
		if(JobRequestService.ShowMyRequest(id) == null )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you  id ").build();
			
		}
		return Response.ok(JobRequestService.ShowMyRequest(id), MediaType.APPLICATION_JSON).build();

	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Accept/{id}")
	public Response AcceptReq(@PathParam("id") int id)
	{
		if(JobRequestService.AcceptRequest(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you  id or something else ").build();
			
		}
		return Response.ok(Response.Status.ACCEPTED).entity("your request Has been accepted").build();

	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("Decline/{id}")
	public Response DeclineReq(@PathParam("id") int id)
	{
		if(JobRequestService.DeclineRequest(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you  id or something else ").build();
			
		}
		return Response.ok(Response.Status.ACCEPTED).entity("your request Has been accepted").build();

	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("accepted")
	public Response AccptedRequest()
	{
		if(JobRequestService.ShowAccepted() == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		else if (JobRequestService.ShowAccepted().size() == 0)
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no accepted Request ").build();
			
		}
		else 
		return Response.ok(JobRequestService.ShowAccepted(), MediaType.APPLICATION_JSON).build();

	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response UpdateRequest(JobRequest st)
	{
		if(JobRequestService.UpdateJobRequest(st) == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(JobRequestService.UpdateJobRequest(st), MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("fixdate")
	public Response FixRDV(JobRequest st)
	{
		if(JobRequestService.FixDate(st) != false)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(st, MediaType.APPLICATION_JSON).build();
	}
	
	


}
