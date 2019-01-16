package tn.esprit.webservices;

import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.interfaces.ProjectSkillRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.ProjectSkillModel;

@Path("/projectSkills")
@ManagedBean
public class ProjectSkillWebService {
	@EJB
	ProjectSkillRemote projectSkillRemote;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getProjectSkills(@QueryParam("idProject") String idProject){
		return projectSkillRemote.getSkillsProject(Integer.parseInt(idProject));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getProjectSkills")
	public List<ProjectSkillModel> getProjectSkillsModel(@QueryParam("idProject") String idProject){
		return projectSkillRemote.getSkillsProjectModel(Integer.parseInt(idProject));
	}
}
