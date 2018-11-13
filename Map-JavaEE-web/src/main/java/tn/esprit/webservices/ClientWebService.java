package tn.esprit.webservices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.ClientRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Person;
import tn.esprit.utlities.AuthenticatedUser;
import tn.esprit.utlities.Secured;

@Path("/clients")
@ManagedBean
public class ClientWebService {
	@EJB
	ClientRemote clientRemote;

//	@Inject
//	@AuthenticatedUser
//	Person authenticatedUser;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClients() {
		if (clientRemote.getAllClients() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (clientRemote.getAllClients().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("\"No data\"").build();

		else
			return Response.ok(clientRemote.getAllClients(), MediaType.APPLICATION_JSON).build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getById")
	public Client getClientById(@QueryParam("idClient") String idClient) {
		return clientRemote.getClientById(Integer.parseInt(idClient));


	}
	
	//@Secured
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addClient(Client client) {
//		if (authenticatedUser.getRoleT().equals("Admin"))
//		{
		return clientRemote.addClient(client);
		//}
		//return "Access denied";

	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String archiveClient(@QueryParam("idClient") String idClient) {
		return clientRemote.archiveClient(Integer.parseInt(idClient));
	}
	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateClient(Client client) {
		return clientRemote.updateClientByAdmin(client);
	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteClient(@QueryParam("idClient") String idClient) {
		return clientRemote.deleteClient(Integer.parseInt(idClient));
	}

	


}
