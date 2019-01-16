package tn.esprit.webservices;

import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.StatSideClientInterfaceRemote;
import tn.esprit.Map.persistences.Resource;

@Path("StatClient")
@ManagedBean
public class StatClientWebService {
	@EJB
StatSideClientInterfaceRemote StatClient;
	
	@Path("/CandidateSuccedTest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> CandidateSuccedTest() {
		return StatClient.CandidateSuccedTest();
	}
	
	@Path("/CandidateRankedByNbJobRequest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response  CandidateRankedByNbJobRequest() {
        return Response.ok(StatClient.CandidateRankedByNbJobRequest(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	
	@Path("/RhAvailableRankedByNote")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response  RhAvailableRankedByNote() {
        return Response.ok(StatClient.RhAvailableRankedByNote(), MediaType.APPLICATION_JSON).header("Access-Control-Allow-Origin", "*").header("Content-Type", "application/json;charset=UTF-8").build();

	}
	
	@Path("/MyProjectsCharges/{idclient}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]>  MyProjectsCharges(@PathParam("idclient")int idclient) {
		return StatClient.MyProjectsCharges(idclient);
	}
	
	//On appelle chaque rh par sumdayoff
	@Path("/ListRhIdByProject/{idproject}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> ListRhIdByProject(@PathParam("idproject")int idproject) {
		return StatClient.ListRhIdByProject(idproject);
	}
	
	@Path("/SkillsRanked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> SkillsRanked() {
		return StatClient.SkillsRanked();
	}
	@Path("/RhRankedByMostNbSkills")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Integer> RhRankedByMostNbSkills() {
		return StatClient.RhRankedByMostNbSkills();
	}
}
