package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.Map.interfaces.ClientRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.ClientType;
import tn.esprit.Map.persistences.Contract;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectType;
import tn.esprit.Map.persistences.Role;
import tn.esprit.Map.utilities.CryptPasswordMD5;
import tn.esprit.Map.utilities.Mail_API;
import tn.esprit.Map.utilities.RandomPassword;


@Stateless
public class ClientService implements ClientRemote {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	private Mail_API mailAPI = new Mail_API();
	private RandomPassword randomPassword = new RandomPassword();
	private CryptPasswordMD5 cryptPasswordMD5 = new CryptPasswordMD5();
	
	@Override
	public List<Client> getAllClients() {
		Query query = em.createQuery("SELECT c.id ,c.nameSociety FROM Client c where c.archived= :archvied and c.roleT = :roleT");
		query.setParameter("archvied",0);
		query.setParameter("roleT",Role.valueOf("Client"));
		List<Object[]> res  = query.getResultList();
		
		List<Client> clients = new ArrayList<Client>();
		res.forEach(array -> {
			Client client = arrayToClient(array);
			client.setProjects(null);
			client.setRequests(null);
			clients.add(client);
		});
		return clients;

	}
	public Client arrayToClient(Object[] array) {
		Client client = new Client();
		client.setId((int) array[0]);
		client.setNameSociety((String) array[1]);
		return client;
	}

	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	
	@Override
	public String addClient(Client client) {
		String password ;
		String cryptedPassword;
		if(!isValidEmailAddress(client.getEmail()))
		{
			return "You must enter a valid mail";
		}
		else
		{
			password =randomPassword.generateRandomPassword();
			//cryptedPassword=cryptPasswordMD5.cryptWithMD5(password);
		client.setPassword(password);
		client.setLogin(client.getFirstName()+" "+client.getLastName());
		//client.setClientType(ClientType.newClient);
		client.setRoleT(Role.Client);
		em.persist(client);
		//mailAPI.sendEmail(client.getEmail(), "rahmabasly20@gmail.com", "Username and Password", "Login : "+client.getLogin()+" Password : "+password);
		}
		return "Client has been saved into data base with this id :"+ client.getId();
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
		Query query =  em.createQuery("Select c from Contract c where c.client= :client");
	    query.setParameter("client", client);
	    Contract contract = (Contract) query.getSingleResult();
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
			em.remove(contract);
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

	@Override
	public String testDecrypt(int IdClient) {
		Client client  = em.find(Client.class, IdClient);
		 String crypt = "It0uwhYF";
		 String clientPassword =client.getPassword(); 
		String hash =  cryptPasswordMD5.cryptWithMD5(crypt);
		if(hash.equals(clientPassword)){
			return"EQUUUUUUUAAALS";
		}
		else{
			return"Noooooooot equal" ;
		}
		 
	}

	@Override
	public Client getClientById(int idClient) {
		return em.find(Client.class,idClient);
	}
	
	@Override
	public String updateClientPassword(Client client) {
		Query query = em.createQuery("update Client c set c.password= :password  where c.id= :clientId");	
		query.setParameter("password", client.getPassword());
		query.setParameter("clientId", client.getId());
		int modified = query.executeUpdate();
		if (modified == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
	@Override
	public List<Client> getAllClientsAngular() {
		Query query = em.createQuery("SELECT c.id,c.firstName,c.lastName,c.email,c.nameSociety,c.logo,c.address,c.clientType FROM Client c where c.archived= :archvied and c.roleT = :roleT");
		query.setParameter("archvied",0);
		query.setParameter("roleT",Role.valueOf("Client"));
		List<Object[]> res  = query.getResultList();
		
		List<Client> clients = new ArrayList<Client>();
		res.forEach(array -> {
			Client client = arrayToClientAngular(array);
			client.setProjects(null);
			client.setRequests(null);
			clients.add(client);
		});
		return clients;
	}
	public Client arrayToClientAngular(Object[] array) {
		Client client = new Client();
		client.setId((int) array[0]);
		client.setFirstName((String) array[1]);
		client.setLastName((String) array[2]);
		client.setEmail((String) array[3]);
		client.setNameSociety((String) array[4]);
		client.setLogo((String) array[5]);
		client.setAddress((String) array[6]);
		client.setClientType((ClientType) array[7]);
		return client;
	}



	

}
