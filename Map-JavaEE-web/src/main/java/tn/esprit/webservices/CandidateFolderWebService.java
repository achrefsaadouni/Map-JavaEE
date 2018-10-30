package tn.esprit.webservices;

import tn.esprit.Map.interfaces.FolderLocal;
import tn.esprit.Map.persistences.CandidateFolder;
import tn.esprit.Map.persistences.JobRequest;

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

@Path("folder")
public class CandidateFolderWebService {

	@EJB
	FolderLocal FolderLocal;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("add")
	public String UploadFolder(CandidateFolder folder) {
		FolderLocal.AddFolder(folder);
		return "succ";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewTests() {
		if (FolderLocal.getAllFolders() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (FolderLocal.getAllFolders().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else

			return Response.ok(FolderLocal.getAllFolders(), MediaType.APPLICATION_JSON).build();

	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("delete/{id}")
	public Response Deletefolder(@PathParam("id") int id) {
		if (FolderLocal.DeleteFolder(id) == false) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Verify the id or something else").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("the folder HAs been deleted Nigga").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("show/{id}")
	public Response showfodler(@PathParam("id") int id) {
		if (FolderLocal.ShowMyFolder(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (FolderLocal.ShowMyFolder(id).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else

			return Response.ok(FolderLocal.ShowMyFolder(id), MediaType.APPLICATION_JSON).build();

	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response UpdateRequest(CandidateFolder folder)
	{
		if(FolderLocal.EditFolder(folder) == null)
		{
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(FolderLocal.EditFolder(folder), MediaType.APPLICATION_JSON).build();
	}
	
	
	
	
	
	
	}

