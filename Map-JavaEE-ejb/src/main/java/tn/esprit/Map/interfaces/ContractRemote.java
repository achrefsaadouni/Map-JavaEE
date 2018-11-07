package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Contract;
import tn.esprit.Map.persistences.Project;

@Remote
public interface ContractRemote {
public String addContract(Contract contract,int clientId);
public List<Contract> getContractByClient(int clientId);
public String updateContract(Contract contract);

}
