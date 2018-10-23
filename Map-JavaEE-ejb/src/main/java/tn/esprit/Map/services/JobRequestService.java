package tn.esprit.Map.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.interfaces.JobRequestRemote;
import tn.esprit.Map.persistences.JobRequest;

@Path("/jobrequest")
@Stateless
public class JobRequestService implements JobRequestRemote {
	
	@PersistenceContext(unitName = "Map-JavaEE-ejb")
	private EntityManager em;


	@Produces(MediaType.TEXT_PLAIN)
	@GET
	@Override
	public String sendJobRequest() {
		
		return "done";
	}
	

}
