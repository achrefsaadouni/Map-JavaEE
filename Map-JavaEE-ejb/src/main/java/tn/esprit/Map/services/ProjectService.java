package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.ProjectRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.JobRequest;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.Project;

@Stateless
public class ProjectService implements ProjectRemote {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public List<Project> getAllProjects() {
		// Query query = em.createQuery("SELECT p FROM Project p");
		// List<Project> results = query.getResultList();
		// return results;
		Query query = em.createQuery("SELECT p.id ,p.projectName , p.startDate"
				+ " , p.endDate , p.address , p.totalNumberResource ,"
				+ " p.levioNumberResource,p.picture ,"
				+ " p.projectType FROM Project p");
		List<Project> results = query.getResultList();
//		Client c = new Client();
//		for (Project p : results) {
//			c = p.getClient();
//			//c.setProjects(results);
//		}

		//c.setProjects(null);
		return results;
	}

	@Override
	public int addProject(Project project) {
		em.persist(project);
		return project.getId();
	}

	@Override
	public String assignProjectToClient(int projectId, Client client) {
		Query query = em.createQuery("update Project p set client= :client where p.id= :projectId");
		query.setParameter("client", client);
		query.setParameter("projectId", projectId);
		int modified = query.executeUpdate();
		if (modified == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@Override
	public Project getProjectById(int projectId) {
		Project project = em.find(Project.class, projectId);
		return project;
	}

	@Override
	public String updateProject(Project project) {
		Query query = em.createQuery("update Project p set p.projectName= :projectName , p.startDate= :startDate"
				+ " , p.endDate= :endDate , p.address= :address , p.totalNumberResource= :totalNumberResource ,"
				+ " p.levioNumberResource= :levioNumberResource , p.picture= :picture ,"
				+ " p.projectType= :projectType where p.id= :projectId");
		query.setParameter("projectId", project.getId());
		query.setParameter("projectName", project.getProjectName());
		query.setParameter("startDate", project.getStartDate());
		query.setParameter("endDate", project.getEndDate());
		query.setParameter("address", project.getAddress());
		query.setParameter("totalNumberResource", project.getTotalNumberResource());
		query.setParameter("levioNumberResource", project.getLevioNumberResource());
		query.setParameter("picture", project.getPicture());
		query.setParameter("projectType", project.getProjectType());
		int modified = query.executeUpdate();
		if (modified == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	@Override
	public String deleteProject(int projectId) {
		Project project = em.find(Project.class,projectId);
		if(project.getId()!=-1){
			em.remove(project);
			return "deleted";
		}
		
		return "error";
	}


	@Override
	public List<Project> getAllProjectByClient(int clientId) {
		List<Project> listProject = new ArrayList<>();
		Client client = em.find(Client.class,clientId);
		Query query=em.createQuery("SELECT p.id ,p.projectName , p.startDate"
				+ " , p.endDate , p.address , p.totalNumberResource ,"
				+ " p.levioNumberResource,p.picture ,"
				+ " p.projectType from Project p  where p.client= :client");
		query.setParameter("client",client);
		listProject =(List<Project>) query.getResultList();
		//failed to lazily initialize a collection of role: tn.esprit.Map.persistences.Client.projects, could not initialize proxy - no Session
		return listProject;
	}
	
//	@Override
//	public List<String> getAllProjectsNameByClient(int clientId) {
//		List<String> listProject= new ArrayList<>();
//		Client client = em.find(Client.class, clientId);
//		for (Project project : client.getProjects()){
//			listProject.add(project.getProjectName());
//		}
//		return listProject;
//	}
}
