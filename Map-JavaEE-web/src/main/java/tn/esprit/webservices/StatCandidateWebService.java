package tn.esprit.webservices;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.StatSideCandidateInterfaceRemote;

@Path("StatCandidate")
@ManagedBean
public class StatCandidateWebService {
	@EJB
	StatSideCandidateInterfaceRemote StatCandidate;
	
	@Path("/MostRhLevioByProjectName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> MostRhLevioByProjectName() {
		return StatCandidate.MostRhLevioByProjectName();
	}
	
	@Path("/NbProjectByRegion")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> NbProjectByRegion() {
		return StatCandidate.NbProjectByRegion();
	}
	
	@Path("/RhRankedBySalary")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response RhRankedBySalary() {
        return Response.ok(StatCandidate.RhRankedBySalary(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	
	@Path("/SkillsRecommended")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response SkillsRecommended() {
        return Response.ok(StatCandidate.SkillsRecommended(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	
	@Path("/CountCadidateResultByTypeTest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response CountCadidateResultByTypeTest() {
		//return StatCandidate.CountCadidateResultByTypeTest();
	
        return Response.ok(StatCandidate.CountCadidateResultByTypeTest(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	@Path("/ListSuccedCandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response ListSuccedCandidate() {

		
	
        return Response.ok(StatCandidate.ListSuccedCandidate(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
}
