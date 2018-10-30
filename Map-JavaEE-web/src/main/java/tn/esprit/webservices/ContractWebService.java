package tn.esprit.webservices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.ContractRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Contract;

@Path("/contracts")
@ManagedBean
public class ContractWebService {
	@EJB
	ContractRemote contractRemote;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{idClient}")
	public Response getContracs(@PathParam("idClient") String idClient) {
		if (contractRemote.getContractByClient(Integer.parseInt(idClient)) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (contractRemote.getContractByClient(Integer.parseInt(idClient)).size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("No data").build();

		else
			return Response.ok(contractRemote.getContractByClient(Integer.parseInt(idClient)), MediaType.APPLICATION_JSON).build();

	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{idClient}")
	public String addContract(Contract contract,@PathParam("idClient") String idClient) {	
		return contractRemote.addContract(contract, Integer.parseInt(idClient));

	}

}
