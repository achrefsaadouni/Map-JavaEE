package tn.esprit.Map.interfaces;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.Map.persistences.JobRequest;
@Local
public interface JobRequestRemote {

	public String sendJobRequest();
	
}


