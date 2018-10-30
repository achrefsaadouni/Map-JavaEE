package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.Map.interfaces.ContractRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Contract;

@Stateless
public class ContractService implements ContractRemote {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	
	
	@Override
	public String addContract(Contract contract, int clientId) {
		if(contract.getEndDate().compareTo(contract.getStartDate())<0){
			return "End date must be after start date";
		}
		else{
		Client client = em.find(Client.class, clientId);
		contract.setClient(client);
		em.persist(contract);
		}
		return "Contract has been saved into data base with this id : "+contract.getId();
	}
	
	public Contract arrayToContract(Object[] array) {
		Contract contract = new Contract();
		contract.setId((int) array[0]);
		contract.setStartDate((Date) array[1]);
		contract.setEndDate((Date) array[2]);
		contract.setClient((Client) array[3]);
		return contract;
	}

	@Override
	public List<Contract> getContractByClient(int clientId) {
		Client client = em.find(Client.class, clientId);
		Query queryContract = em.createQuery("select c.id,c.startDate,c.endDate,c.client from Contract c where c.client= :client and c.archived= :archived");
		queryContract.setParameter("client", client);
		queryContract.setParameter("archived", 0);
		List<Object[]> res = queryContract.getResultList();
		List<Contract> contracts = new ArrayList<Contract>();
		res.forEach(array -> {
			Contract contract = arrayToContract(array);
			Client c =contract.getClient();
			c.setProjects(null);
			c.setRequests(null);
			c.setInBoxs(null);
			contracts.add(contract);
		});
		return contracts;
	}

}
