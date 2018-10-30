package tn.esprit.webservices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.CandidateLocalService;
import tn.esprit.Map.persistences.Candidate;
import tn.esprit.Map.persistences.CandidateState;

@Path("/candidate")
@ManagedBean
public class CandidateWebSerice {

	@EJB
	CandidateLocalService CandidateLocalService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ViewCands() {
		
		if (CandidateLocalService.GetAllCandidates() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (CandidateLocalService.GetAllCandidates().size() == 0)
			return Response.status(Response.Status.BAD_REQUEST).entity("Aint got no data").build();

		else
			return Response.ok(CandidateLocalService.GetAllCandidates(), MediaType.APPLICATION_JSON).build();
		

	}
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String AddJobRequest(Candidate candidate) {
		candidate.setCandidateState(CandidateState.Candidate);
		CandidateLocalService.AddCandidateToDB(candidate);
		return "add succ";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("changeState/{id}")
	public Response ChangeCandidateState(@PathParam("id") int id) {
		if (CandidateLocalService.ChangeCandidateState(id) == false) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Verify the id").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("the Candidate HAs been Updated Nigga").build();
	}
	
	@GET
	@Path("assign")
	@Produces(MediaType.TEXT_PLAIN)
	public String SetProject(@QueryParam(value="idc") int idc,@QueryParam(value="idp") int idp)
	{
		if(CandidateLocalService.AssignCandidateToProject(idc, idp) == false)
		{
			return"error";
		}
		return "Successfull";
	}
	
	@GET
	@Path("remove")
	@Produces(MediaType.TEXT_PLAIN)
	public String RemoveProject(@QueryParam(value="idc") int idc)
	{
		if(CandidateLocalService.RemoveCandidateFromProject(idc) == false)
		{
			return"error while removing";
		}
		return "Removing Successfull";
	}
	
	
	@GET
	@Path("minister/{id}")	
	public void RequestMinister(@PathParam("id") int id )
	{
		
		CandidateLocalService.RequestTheMinister();
	}
	
	
	

	@GET
	@Path("change/{id}")
	public void ChangrToResource(@PathParam("id") int id)
	{
		CandidateLocalService.ChangeCandidateToRessource(id);
	}
	
	@GET
	@Path("notify/{id}")
	public void notify(@PathParam("id") int id)
	{
		CandidateLocalService.NotifyCandidate(id);
	}
	
	
	
	}
	
	

