package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Project;

@Remote
public interface ClientRemote {
	public List<Client> getAllClients();
	public int addClient(Client c);
	public String updateClientByAdmin(Client client);
	public String deleteClient(int clientId);
	public String archiveClient(int clientId);
	public  void testSendMail(String to,String from,String subject,String bodyText);
}
