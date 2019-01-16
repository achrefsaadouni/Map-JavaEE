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

import tn.esprit.Map.interfaces.CategoriesService;
import tn.esprit.Map.persistences.Category;

@Path("/categories")
public class CategoryWebService {
	
	@EJB
	CategoriesService CategoriesService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("new")
	@Produces(MediaType.APPLICATION_JSON)
	public Category AddCat(Category c)
	{
		 return CategoriesService.AddCategory(c);
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewRequestdJobs() {
		if (CategoriesService.showCategory() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (CategoriesService.showCategory().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(CategoriesService.showCategory(), MediaType.APPLICATION_JSON).build();

	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{id}")
	public Response DeleteRequest(@PathParam("id") int id)
	{
		if(CategoriesService.deleteCategory(id) == false )
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("verify you request id ").build();
			
		}
		 return Response.ok(Response.Status.ACCEPTED).entity("your cateogry Has been deleted").build();
		
	}
	
	
	
	
	
	
	
}
