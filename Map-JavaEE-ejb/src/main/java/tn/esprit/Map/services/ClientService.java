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

@Stateless
public class ClientService implements ClientRemote {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public List<Client> getAllClients() {
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c", Client.class);
		List<Client> results = query.getResultList();
		for (Client c : results) {
			c.setProjects(null);
			c.setRequests(null);
		}
		return results;
	}

	@Override
	public int addClient(Client c) {
		em.persist(c);
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
		if(client.getId()!=-1){
			em.remove(client);
			return "delete success";
		}
		return "error";
	}

}
