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

import tn.esprit.Map.interfaces.ModulesServices;
import tn.esprit.Map.persistences.JobRequest;
import tn.esprit.Map.persistences.Modules;

@Path("/modules")

public class ModulesWebServices {
	
	@EJB
	ModulesServices ModulesServices;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("new/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Modules AddModuleService(@PathParam("id") int id,Modules Module)
	{
		return ModulesServices.AddModule(id,Module);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewModules() {
		if (ModulesServices.ShowAll() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (ModulesServices.ShowAll().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(ModulesServices.ShowAll(), MediaType.APPLICATION_JSON).build();

	}
	@GET
	@Path("show/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewModulesByCat(@PathParam("id") int id) {
		if (ModulesServices.ShowByCategory(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (ModulesServices.ShowByCategory(id).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(ModulesServices.ShowByCategory(id), MediaType.APPLICATION_JSON).build();

	}
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{id}")
	public Response DeleteModule(@PathParam("id") int id)
	{
		if(ModulesServices.deleteModule(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you request id ").build();
			
		}
		 return Response.ok(Response.Status.ACCEPTED).entity("your module Has been deleted").build();
		
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response UpdateRequest(Modules m)
	{
		if(ModulesServices.UpdateModule(m) == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(ModulesServices.UpdateModule(m), MediaType.APPLICATION_JSON).build();
	}
	
}
