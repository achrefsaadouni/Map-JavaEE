package tn.esprit.webservices;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import tn.esprit.Map.interfaces.StatSideAdminInterfaceRemote;

@Path("StatAdmin")
@ManagedBean
public class StatAdminWebService {
	
	@EJB
	StatSideAdminInterfaceRemote StatAdmin;

	@Path("/CountPersonByRole/{role}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Long CountPersonByRole(@PathParam("role") String role) {
		return StatAdmin.CountNbPersonByRole(role);
	}

	@Path("/CountClientByCategory")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response CountClientByCategory() {
        return Response.ok(StatAdmin.CountNbClientByCategory(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}

	@Path("/CountClientByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response CountClientByType() {
        return Response.ok(StatAdmin.CountNbClientByType(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}

	@Path("/CountPersonByRegion/{role}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response CountPersonByRegion(@PathParam("role") String role) {
        return Response.ok(StatAdmin.CountNbPersonByRegionAndRole(role), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}

	@Path("/CountNbProjectByClient")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountNbProjectByClient() {
		return StatAdmin.CountNbProjectByClient();
	}

	@Path("/CountNbProjectByRegion")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountNbProjectByRegion() {
		return StatAdmin.CountNbProjectByRegion();
	}

	@Path("/CountNbProjectByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountNbProjectByType() {
		return StatAdmin.CountNbProjectByType();
	}

	@Path("/CountNbProjectEnded")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response CountNbProjectEnded() {
        return Response.ok(StatAdmin.CountNbProjectEnded(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}

	@Path("/CountNbProjectNotEnded")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response CountNbProjectNOtEnded() {
        return Response.ok(StatAdmin.CountNbProjectNotEnded(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}

	@Path("/CountNbCandidateByState")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public  Response CountNbCandidateByState() {
        return Response.ok(StatAdmin.CountNbCandidateByState(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	
	@Path("/CountNbRhByAvailability")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public  Response CountNbRhByAvailability() {
        return Response.ok(StatAdmin.CountNbRhByAvailability(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	@Path("/BeneficeByProject")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response BeneficeByProject() {
        return Response.ok(StatAdmin.BeneficeByProject(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	@Path("/BeneficeByClient")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response BeneficeByClient() {
        return Response.ok(StatAdmin.BeneficeByClient(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	@GET
	@Path("/pdf")
	@Produces("application/pdf")
	public javax.ws.rs.core.Response getPdf() throws Exception
	{
	    File file = new File("C:\\Users\\Emir\\Desktop\\test.pdf");
	    FileInputStream fileInputStream = new FileInputStream(file);
	    javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response.ok((Object) fileInputStream);
	    responseBuilder.type("application/pdf");
	    responseBuilder.header("Content-Disposition", "filename=test.pdf");
	    return responseBuilder.build();
	}

}
