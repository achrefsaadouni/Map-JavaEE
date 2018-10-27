package tn.esprit.webservices;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	public List<Object[]> CountClientByCategory() {
		return StatAdmin.CountNbClientByCategory();
	}

	@Path("/CountClientByType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountClientByType() {
		return StatAdmin.CountNbClientByType();
	}

	@Path("/CountPersonByRegion/{role}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountPersonByRegion(@PathParam("role") String role) {
		return StatAdmin.CountNbPersonByRegionAndRole(role);
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
	public List<Object[]> CountNbProjectEnded() {
		return StatAdmin.CountNbProjectEnded();
	}

	@Path("/CountNbProjectNotEnded")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountNbProjectNOtEnded() {
		return StatAdmin.CountNbProjectNotEnded();
	}

	@Path("/CountNbCandidateByState")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountNbCandidateByState() {
		return StatAdmin.CountNbCandidateByState();
	}
	
	@Path("/CountNbRhByAvailability")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountNbRhByAvailability() {
		return StatAdmin.CountNbRhByAvailability();
	}
	@Path("/BeneficeByProject")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> BeneficeByProject() {
		return StatAdmin.BeneficeByProject();
	}
	@Path("/BeneficeByClient")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> BeneficeByClient() {
		return StatAdmin.BeneficeByClient();
	}
}
