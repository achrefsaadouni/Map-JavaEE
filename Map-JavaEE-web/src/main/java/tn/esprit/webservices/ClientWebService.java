//package tn.esprit.webservices;
//
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//
//import tn.esprit.Map.persistences.Client;
//import tn.esprit.Map.services.ClientService;
//
//@Path("/clients")
//public class ClientWebService {
//	ClientService clientService = new ClientService();
//
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public String getAllClients() {
//		return clientService.getAllClients(1);
//	}
//}
