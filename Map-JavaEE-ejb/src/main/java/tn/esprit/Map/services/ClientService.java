package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.ClientRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.utilities.Mail_API;
import tn.esprit.Map.utilities.RandomPassword;


@Stateless
public class ClientService implements ClientRemote {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	private Mail_API mailAPI = new Mail_API();
	private RandomPassword randomPassword = new RandomPassword();

	@Override
	public List<Client> getAllClients() {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c where c.archived= :archvied", Client.class);
		query.setParameter("archvied",false);
		List<Client> results = query.getResultList();
		for (Client c : results) {
			c.setProjects(null);
			c.setRequests(null);
		}
		return results;
	}

	@Override
	public int addClient(Client c) {
		c.setPassword(randomPassword.generateRandomPassword());
		em.persist(c);
		c.setLogin(c.getFirstName()+" "+c.getLastName());
		mailAPI.sendEmail(c.getEmail(), "rahmabasly20@gmail.com", "Username and Password", "Login : "+c.getLogin()+" Password : "+c.getPassword());
		return c.getId();
	}

	@Override
	public String updateClientByAdmin(Client client) {
		Query query = em.createQuery("update Client c set c.clientType= :clientType, c.clientCategory= :clientCategory,c.nameSociety= :nameSociety,c.logo= :logo,c.address= :address,c.firstName= :firstName,c.lastName= :lastName,c.login= :login where c.id= :clientId");	
		query.setParameter("clientType", client.getClientType());
		query.setParameter("firstName", client.getFirstName());
		query.setParameter("lastName", client.getLastName());
		query.setParameter("login", client.getLogin());
		query.setParameter("address", client.getAddress());
		query.setParameter("nameSociety", client.getNameSociety());
		query.setParameter("logo", client.getLogo());
		query.setParameter("clientCategory", client.getClientCategory());
		query.setParameter("clientId", client.getId());
		int modified = query.executeUpdate();
		if (modified == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
			
	@Override
	public String deleteClient(int clientId) {
		Client client = em.find(Client.class,clientId);
		Query queryGetProjects = em.createQuery("select p from Project p where p.client = :client");
		Query queryUpdateProject = em.createQuery("update Project p set client= :client where p.id= :projectId");
		queryGetProjects.setParameter("client",client);
		List<Project> projectsClient =queryGetProjects.getResultList();
		for(Project project : projectsClient)
		{
			//project.setClient(null); --> didn't work
			//cause of insertable = false, updatable = false
			queryUpdateProject.setParameter("client", null);
			queryUpdateProject.setParameter("projectId", project.getId());
			queryUpdateProject.executeUpdate();		
		}
		if(client.getId()!=-1){
			em.remove(client);
			return "deleted";
		}
		return "error";
	}

	@Override
	public void testSendMail(String to, String from, String subject, String bodyText) {
		mailAPI.sendEmail(to, from, subject, bodyText);
		
	}

	@Override
	public String archiveClient(int clientId) {
		Client client = em.find(Client.class,clientId);
		client.setArchived(1);
		return "archived done";
	}

}
