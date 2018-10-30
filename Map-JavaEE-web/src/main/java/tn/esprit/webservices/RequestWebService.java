package tn.esprit.webservices;

import java.util.List;

import javax.annotation.ManagedBean;
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

import tn.esprit.Map.interfaces.RequestServiceRemote;
import tn.esprit.Map.persistences.Request;


@Path("/requestss")
@ManagedBean
public class RequestWebService {
	@EJB
	RequestServiceRemote requestService;
	Request request = new Request();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getRequests")
	public Response ViewMessages() {
		if (requestService.AllRequest() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (requestService.AllRequest().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

		else
			return Response.ok(requestService.AllRequest(), MediaType.APPLICATION_JSON).build();

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN) 
	@Path("/addRequest")
	public int addRequest(Request request) {	
		int RequestId = requestService.addRequest(request);
		return RequestId;
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteRequest/{idRequest}")
	public int deleteRequest(@PathParam("idRequest") String requestID){
		return requestService.deleteRequest(Integer.parseInt(requestID));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)   
	@Path("/getRequestById/{idRequest}")
	public Response getRequestById(@PathParam("idRequest") String requestID) {
		
		return Response.ok(requestService.getRequestById(Integer.parseInt(requestID)), MediaType.APPLICATION_JSON).build();

	}
	

}
