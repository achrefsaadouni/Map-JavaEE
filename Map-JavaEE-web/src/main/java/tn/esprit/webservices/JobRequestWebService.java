package tn.esprit.webservices;

import tn.esprit.Map.services.*;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	public Response ViewRequestdJobs()
	{
		if (JobRequestService.ViewAllRequested() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (JobRequestService.ViewAllRequested().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

		else
			return Response.ok(JobRequestService.ViewAllRequested(), MediaType.APPLICATION_JSON).build();
		
	}


}
