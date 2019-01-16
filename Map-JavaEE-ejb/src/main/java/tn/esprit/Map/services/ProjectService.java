package tn.esprit.Map.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Map.interfaces.ProjectRemote;
import tn.esprit.Map.persistences.Administrator;
import tn.esprit.Map.persistences.ArchivedProjects;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.ClientType;
import tn.esprit.Map.persistences.Contract;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.OrganizationalChart;
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectType;

@Stateless
public class ProjectService implements ProjectRemote {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	
	@Override
	public List<Project> getProjectsByAdress(String address) {
		Query query = em.createQuery(
				"SELECT p.id ,p.projectName , p.startDate" + " , p.endDate , p.address , p.totalNumberResource ,"
						+ " p.levioNumberResource,p.picture, p.projectType ,p.client  FROM Project p where p.address= :address");
		query.setParameter("address",address);
		List<Object[]> res = query.getResultList();
		List<Project> projects = new ArrayList<Project>();
		res.forEach(array -> {
			Project project = arrayToProject(array);
			Client c = project.getClient();
			c.setProjects(null);
			c.setInBoxs(null);
			c.setRequests(null);
			projects.add(project);
		});
		return projects;
	}

	@Override
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
			c.setInBoxs(null);
			c.setRequests(null);
			projects.add(project);
		});
		return projects;
	}
	@Override
	public Project getProjectById(int projectId) {
		return em.find(Project.class, projectId);
//		Query query = em.createQuery(
//				"SELECT p.id ,p.projectName , p.startDate" + " , p.endDate , p.address , p.totalNumberResource ,"
//						+ " p.levioNumberResource,p.picture, p.projectType ,p.client  FROM Project p p.id= :projectId");
//		query.setParameter("projectId", projectId);
//		List<Object[]> res = query.getResultList();
//		List<Project> projects = new ArrayList<Project>();
//		res.forEach(array -> {
//			Project project = arrayToProject(array);
//			Client c = project.getClient();
//			c.setProjects(null);
//			c.setInBoxs(null);
//			c.setRequests(null);
//			projects.add(project);
//		});
//		return projects;
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
	

	public Contract arrayToContract(Object[] array) {
		Contract contract = new Contract();
		contract.setId((int) array[0]);
		contract.setStartDate((Date) array[1]);
		contract.setEndDate((Date) array[2]);
		return contract;
	}
	
	
	@Override
	public int addProject(Project project,int clientId) {
		
			
//		if (project.getEndDate().compareTo(project.getStartDate()) < 0) {
//			return "End date must be after start date";
		
//		}
//		else
//		{
			//project.setOrganizationalChart(null);
//			project.setTotalNumberResource(0);
//			project.setLevioNumberResource(0);
	
		

		
		em.persist(project);
		em.flush();
		Client client = em.find(Client.class, clientId);
		Query query = em.createQuery("update Project p set p.client= :client where p.id= :projectId");
		query.setParameter("client",client);
		query.setParameter("projectId",project.getId());
		query.executeUpdate();
		
		//}
		return project.getId();
	}
	@Override
	public int addProjectAngular(Project project , int clientId) {

		em.persist(project);
		em.flush();
		Client client = em.find(Client.class, clientId);
		Query query = em.createQuery("update Project p set p.client= :client where p.id= :projectId");
		query.setParameter("client",client);
		query.setParameter("projectId",project.getId());
		query.executeUpdate();
		
		//}
		return project.getId();
	}

	
	@Override
	public String assignProjectToClient(int clientId, int projectId) {

		int modified = 0;
		
		Client client = em.find(Client.class, clientId);

		TypedQuery<Long> queryCount = em.createQuery("select count(p) from Project p where p.client= :client1",
				Long.class);
		queryCount.setParameter("client1", client);
		long projectCount = queryCount.getSingleResult();

		
		Query queryContract = em.createQuery(
				"select c.id,c.startDate,c.endDate from Contract c where c.client= :client2 and c.archived= :archived");
		queryContract.setParameter("client2", client);
		queryContract.setParameter("archived", 0);
		List<Object[]> res = queryContract.getResultList();
		List<Contract> contracts = new ArrayList<Contract>();
		res.forEach(array -> {
			Contract contract = arrayToContract(array);
			contracts.add(contract);
		});
		
		if(contracts.size() == 0){
			return "client must have a contract";
		}
		
		for (Contract contract : contracts) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			if ((dateFormat.format(date).compareTo(contract.getEndDate().toString()) > 0)) {
				System.out.println("Contract ended");
				client.setClientType(ClientType.endContract);
				contract.setArchived(1);
				// client became null so reinserted the client
				contract.setClient(client);
				em.persist(em.contains(contract) ? contract : em.merge(contract));
				return "Contract ended can not assign project";
			}
		}
		
		Query query = em.createQuery("update Project p set p.client= :client where p.id= :projectId");
		query.setParameter("client", client);
		query.setParameter("projectId", projectId);
		
		if (projectCount > 1) {
			client.setClientType(ClientType.currentClient);
			modified = query.executeUpdate();
		} else {
			modified = query.executeUpdate();
		}
		if (modified == 1) {
			return "success";
		} else {
			return "faiiil";
		}
		

	}
	@Override
	public String assignProjectToClientAngular(int clientId, int projectId) {
		Client client = em.find(Client.class, clientId);
		Query query = em.createQuery("update Project p set p.client= :client where p.id= :projectId");
		query.setParameter("client", client);
		query.setParameter("projectId", projectId);
		int modified = query.executeUpdate();
		if (modified == 1) {
			return "success";
		} else {
			return "faiiil";
		}
	}

	

	

	

	@Override
	public List<Project> getAllProjectByClient(int clientId) {
		Client client = em.find(Client.class, clientId);
		Query query = em.createQuery("SELECT p.id ,p.projectName , p.startDate"
				+ " , p.endDate , p.address , p.totalNumberResource ," + " p.levioNumberResource,p.picture ,"
				+ " p.projectType , p.client from Project p  where p.client= :client");
		query.setParameter("client", client);
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
	public String updateProject(Project project) {
	
		Query query = em.createQuery("update Project p set p.projectName= :projectName , p.startDate= :startDate"
				+ " , p.endDate= :endDate , p.address= :address , p.totalNumberResource= :totalNumberResource ,"
				+ " p.levioNumberResource= :levioNumberResource , p.picture= :picture ,"
				+ " p.projectType= :projectType , p.client = :client where p.id= :projectId");
		query.setParameter("projectId", project.getId());
		query.setParameter("projectName", project.getProjectName());
		query.setParameter("startDate", project.getStartDate());
		query.setParameter("endDate", project.getEndDate());
		query.setParameter("address", project.getAddress());
		query.setParameter("totalNumberResource", project.getTotalNumberResource());
		query.setParameter("levioNumberResource", project.getLevioNumberResource());
		query.setParameter("picture", project.getPicture());
		query.setParameter("projectType", project.getProjectType());
		query.setParameter("client", project.getClient());
		int modified = query.executeUpdate();
		if (modified == 1) {
			return "success";
		} else {
			return "fail";
		}
		}
	

	
	@Override
	public String deleteProject(int projectId) {

		Project project = em.find(Project.class, projectId);
		if (project.getId() != -1) {
			em.remove(project);
			return "deleted";
		}

		return "error";
		}
	
	public Project arrayToProjectWithoutClient(Object[] array) {
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
		return project;
	}

	public List<Project> getProjectsByDate(String startDate, String endDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDateConvert = new Date();
		Date endDateConvert = new Date();
		try {
			startDateConvert = sdf.parse(startDate);
			endDateConvert = sdf.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Query query = em.createQuery(
				"select  p.id ,p.projectName , p.startDate , p.endDate , p.address , p.totalNumberResource ," + " p.levioNumberResource,p.picture , p.projectType  from Project p where    p.startDate >= :startDate AND p.endDate   <= :endDate");
		query.setParameter("startDate", startDateConvert);
		query.setParameter("endDate", endDateConvert);
		List<Object[]> res = query.getResultList();
		List<Project> projects = new ArrayList<Project>();
		res.forEach(array -> {
			Project project = arrayToProjectWithoutClient(array);
			projects.add(project);
		});
		return projects;
	
	}

	@Override
	public String sumAmountProject(String startDate, String endDate) {
		List<Project> projects = getProjectsByDate(startDate,endDate);
		
		Query query = em.createQuery("select m from Mandate m where m.projet = :project");
		double sum = 0 ;
		Mandate mandate = new Mandate();
		for(Project project : projects){
			query.setParameter("project", project);
			
			try {
			 mandate = (Mandate) query.getSingleResult();
			 mandate.setRessource(null);}
			catch(javax.persistence.NoResultException ex){
				return "No project affected to mandate" ;
			}
			sum +=mandate.getMontant();
		}
		
		return "you have spent between "+startDate+" and " +endDate+ " : "+sum;
	}
	@Override
	public List<Client> getClientByProject(int projectId) {
		Query query = em.createQuery("select p.client from Project p where p.id= :projectId");
		query.setParameter("projectId", projectId);
		return query.getResultList();
	}
	@Override
	public String archiveOneProject(Project project) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		ArchivedProjects archivedProjects = new ArchivedProjects();
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
			em.remove(em.contains(project) ? project : em.merge(project));
			em.persist(em.contains(archivedProjects) ? archivedProjects : em.merge(archivedProjects));		
		return "Archived";
	}

	






}
