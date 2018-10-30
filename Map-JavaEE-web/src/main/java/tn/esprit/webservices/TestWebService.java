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

import tn.esprit.Map.interfaces.TestLocalService;
import tn.esprit.Map.persistences.Test;

@Path("/test")
@ManagedBean
public class TestWebService {

	@EJB
	TestLocalService TestLocalService;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewTests() {
		
		if (TestLocalService.ShowAllTests() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (TestLocalService.ShowAllTests().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(TestLocalService.ShowAllTests(), MediaType.APPLICATION_JSON).build();
		

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("technical")
	public String AddTechTest(Test test) {
		TestLocalService.TechAddTest(test);

		return "successful test Add";
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("french")
	public String FrenchAddTest(Test test) {
		TestLocalService.FrenchAddTest(test);

		return "successful test Add";
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{id}")
	public Response DeleteTest(@PathParam("id") int id) {
		if (TestLocalService.deletetest(id) == false) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Verify the id or something else").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("the test HAs been deleted Nigga").build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("assign/{id}/{idt}")
	public void Assign(@PathParam("id") int id, @PathParam("idt") int idt) {
		TestLocalService.AffectTestToCandidate(id, idt);
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getMyResult(@PathParam("id") int id) {
		
		if(TestLocalService.CheckMyTestResult(id) == null)
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("Verify the id or something else").build();
		}
		return Response.ok(TestLocalService.CheckMyTestResult(id), MediaType.APPLICATION_JSON).build();
		
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("response")
	public Response AddResponse(Test test)
	{
		if(TestLocalService.ResponseToTest(test) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("Sorry You have passed the upload deadline deadline").build();
		}
		return Response.ok(test.getTestResponseFile(), MediaType.APPLICATION_JSON).build();
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("response/letter")
	public Response AddLetter(Test test)
	{
		if(TestLocalService.AddEmploymentLetter(test) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("Verify the id or something else").build();
		}
		return Response.ok(test.getEmployment_Letter(), MediaType.APPLICATION_JSON).build();
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("accept/{id}")
	public Response AcceptTest(@PathParam("id") int id )
	{
		if(TestLocalService.AcceptTest(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("Verify the id or something else").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("you have been accepted nigga").build();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("refuse/{id}")
	public Response RefuseTest(@PathParam("id") int id )
	{
		if(TestLocalService.RefuseTest(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("Verify the id or something else").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("you have been refused nigga").build();
	}
	

	
	
	
}
