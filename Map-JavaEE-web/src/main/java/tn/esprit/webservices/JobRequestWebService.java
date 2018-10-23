package tn.esprit.webservices;

import tn.esprit.Map.services.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.persistences.JobRequest;

@Path("/jobrequest")
public class JobRequestWebService {
	
	JobRequestService JobRequestService = new JobRequestService();
	JobRequest jb = new JobRequest();
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test()
	{
		return "test";
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddReq()
	{
		JobRequestService.AddJobRequest(jb);
		return "succ";
	}

}
