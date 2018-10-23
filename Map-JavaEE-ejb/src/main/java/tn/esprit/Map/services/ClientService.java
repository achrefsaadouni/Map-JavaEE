package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.interfaces.ClientRemote;
import tn.esprit.Map.persistences.*;

@Stateless
public class ClientService implements ClientRemote{
	@PersistenceContext(unitName = "Map-JavaEE-ejb")
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
