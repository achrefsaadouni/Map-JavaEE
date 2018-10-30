package tn.esprit.Map.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.ProjectLocal;
import tn.esprit.Map.persistences.ArchivedProjects;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectType;
@Stateless
@LocalBean
public class ProjectServiceLocal extends TimerTask implements ProjectLocal  {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	
	
	public List<Project> getAllProjects() {
		Query query = em.createQuery(
				"SELECT p.id ,p.projectName , p.startDate" + " , p.endDate , p.address , p.totalNumberResource ,"
						+ " p.levioNumberResource,p.picture, p.projectType ,p.client  FROM Project p");
		List<Object[]> res = query.getResultList();
		List<Project> projects = new ArrayList<Project>();
		res.forEach(array -> {
			Project project = arrayToProject(array);
			Client c = project.getClient();
			c.setProjects(null);
			c.setRequests(null);
			c.setInBoxs(null);
			projects.add(project);
		});
		return projects;
	}

	public Project arrayToProject(Object[] array) {
		Project project = new Project();
		project.setId((int) array[0]);
		project.setProjectName((String) array[1]);
		project.setStartDate((Date) array[2]);
		project.setEndDate((Date) array[3]);
		project.setAddress((String) array[4]);
		project.setTotalNumberResource((int) array[5]);
		project.setLevioNumberResource((int) array[6]);
		project.setPicture((String) array[7]);
		project.setProjectType((ProjectType) array[8]);
		project.setClient((Client) array[9]);
		return project;
	}
	
	@Override
	public String archiveProject() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		List<Project> projects = this.getAllProjects();
		ArchivedProjects archivedProjects = new ArchivedProjects();
		
		// detached entity passed to persist: don't send id --> Generated value
		// archivedProjects.setId(project.getId());
		for (Project project : projects) {
			archivedProjects.setId(project.getId());
			archivedProjects.setProjectName(project.getProjectName());
			archivedProjects.setProjectType(project.getProjectType());
			archivedProjects.setAddress(project.getAddress());
			archivedProjects.setClient(project.getClient().getId());
			archivedProjects.setStartDate(project.getStartDate());
			archivedProjects.setEndDate(project.getEndDate());
			archivedProjects.setLevioNumberResource(project.getLevioNumberResource());
			archivedProjects.setTotalNumberResource(project.getTotalNumberResource());
			archivedProjects.setPicture(project.getPicture());
			if ((dateFormat.format(date).compareTo(project.getEndDate().toString()) > 0)) {
				// java.lang.IllegalArgumentException: Removing a detached
				// instance com.test.Project#3 got this error
				// em works only on entities which are managed in the current
				// transaction/context
				// retrieving the entity in an earlier transaction, storing it
				// in the HTTP session and then attempting to remove it in a
				// different transaction/context
				// This why i have to check if entity is still managed
				 //em.remove(project);
				em.remove(em.contains(project) ? project : em.merge(project));
				//em.persist(archivedProjects);
				em.persist(em.contains(archivedProjects) ? archivedProjects : em.merge(archivedProjects));
			}

		}
		return "Archived";

	}

	@Override
	public void run() {
		try {
			this.archiveProject();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
