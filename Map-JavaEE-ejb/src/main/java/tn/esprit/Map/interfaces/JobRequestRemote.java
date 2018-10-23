package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.JobRequest;

@Remote
public interface JobRequestRemote {

	public String sendJobRequest();
	
}


