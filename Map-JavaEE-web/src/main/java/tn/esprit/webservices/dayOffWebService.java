package tn.esprit.webservices;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import tn.esprit.Map.interfaces.ResourceRemote;
import tn.esprit.Map.persistences.DayOff;
import tn.esprit.Map.services.DayOffService;

@Path("/DayOff")
public class dayOffWebService {
	
	@EJB
	DayOffService IdayOffService;
	
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("affecter")
public String Affecter(@QueryParam("resourceId") String resourceId , DayOff d) {
		
		if(IdayOffService.AffecterDayOff(Integer.parseInt(resourceId),d) == false){
			return "Impossible";
		}
		return " done";
	}


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("listDayOff")
public Response ListDayOff(@QueryParam("resourceId") String resourceId ) {
		
		if(IdayOffService.listDayOffResource(Integer.parseInt(resourceId)) == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("No data").build();
		}
		return Response.ok(IdayOffService.listDayOffResource(Integer.parseInt(resourceId)), MediaType.APPLICATION_JSON).build();
	}

}
