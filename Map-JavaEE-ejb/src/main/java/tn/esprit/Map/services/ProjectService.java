package tn.esprit.Map.services;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.Map.interfaces.ProjectRemote;
import tn.esprit.Map.persistences.Project;

@Stateless
public class ProjectService implements ProjectRemote{
	@PersistenceContext
	private EntityManager em;		
	
	
	@Override
	public String getProjectById(int projectId) {
		Project project = em.find(Project.class,projectId);
		return project.getProjectName();
	}

}
