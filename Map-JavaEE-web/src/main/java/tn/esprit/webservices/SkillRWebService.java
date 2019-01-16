package tn.esprit.webservices;

import java.text.ParseException;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.interfaces.ProjectRemote;
import tn.esprit.Map.interfaces.SkillRRemote;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.Skill;

@Path("/skills")
@ManagedBean
public class SkillRWebService {
	@EJB
	SkillRRemote skillRemote;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Skill> getSkillsProject(@QueryParam("idProject") String idProject)
	{
		return skillRemote.getSkillsOfProject(Integer.parseInt(idProject));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getSkillById")
	public Skill getSkillById(@QueryParam("idSkill") String idSkill)
	{
		return skillRemote.getSkillById(Integer.parseInt(idSkill));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public int addSkill(Skill skill) { 
		return skillRemote.AddSkill(skill);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/addSkillProject")
	public int addSkillProject(@QueryParam("idProject") String idProject , @QueryParam("idSkill") String idSkill , @QueryParam("percentage") String percentage) { 
		return skillRemote.AddSkillProject(Integer.parseInt(idProject) , Integer.parseInt(idSkill) , Integer.parseInt(percentage));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/updateSkillProject")
	public int updatePercentageSkills(@QueryParam("idProject") String idProject , @QueryParam("idSkill") String idSkill , @QueryParam("percentage") String percentage) { 
		return skillRemote.updatePercentageSkills(Integer.parseInt(idProject) , Integer.parseInt(idSkill) , Integer.parseInt(percentage));
	}

}
