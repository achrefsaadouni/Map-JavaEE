<<<<<<< HEAD
<<<<<<< HEAD
//package tn.esprit.Map.services;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.ejb.Stateless;
//import javax.persistence.*;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//
//import tn.esprit.Map.interfaces.ClientRemote;
//import tn.esprit.Map.persistences.*;
//
//@Stateless
//public class ClientService implements ClientRemote{
//	@PersistenceContext(unitName = "Map-JavaEE-ejb")
//	private EntityManager em;
////	
//////	@POST
//////	@Consumes(MediaType.APPLICATION_JSON)
//////	@Produces(MediaType.TEXT_PLAIN)
////	@Override
////	public int addClient(Client client) {
////		em.persist(client);
////		System.out.println("added"+client.getId());
////		return client.getId();
////	}
//	
=======
package tn.esprit.Map.services;


import javax.ejb.Stateless;
import javax.persistence.*;


import tn.esprit.Map.interfaces.ClientRemote;
import tn.esprit.Map.persistences.*;

@Stateless
public class ClientService implements ClientRemote{
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
>>>>>>> 4e349fa845099c47a25aac04ea98c011d86e37ce
//	
//	@Override
//	public String getAllClients(int id) {
//		
//		//Query query  = em.createQuery("select p from Person p");
//		//query.setParameter("role", "client");
//		//List<Client> clients = new ArrayList<>();
//		//clients = (List<Client>) em.find(Client.class,id);
//		//clients = query.getResultList();
//		Person client = em.find(Person.class,id);
//		return client.getFirstName();
//	}
//	
//
//	
//
//}
=======
package tn.esprit.Map.services;


import javax.ejb.Stateless;
import javax.persistence.*;


import tn.esprit.Map.interfaces.ClientRemote;
import tn.esprit.Map.persistences.*;

@Stateless
public class ClientService implements ClientRemote{
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
//	
////	@POST
////	@Consumes(MediaType.APPLICATION_JSON)
////	@Produces(MediaType.TEXT_PLAIN)
//	@Override
//	public int addClient(Client client) {
//		em.persist(client);
//		System.out.println("added"+client.getId());
//		return client.getId();
//	}
	
	
	@Override
	public String getAllClients(int id) {
		
		//Query query  = em.createQuery("select p from Person p");
		//query.setParameter("role", "client");
		//List<Client> clients = new ArrayList<>();
		//clients = (List<Client>) em.find(Client.class,id);
		//clients = query.getResultList();
		Person client = em.find(Person.class,id);
		return client.getFirstName();
	}
	

	

}
>>>>>>> branch 'master' of https://github.com/saadouniachref/Map-JavaEE.git
