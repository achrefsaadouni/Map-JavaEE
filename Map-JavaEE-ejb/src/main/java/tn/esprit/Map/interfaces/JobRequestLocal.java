package tn.esprit.Map.interfaces;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.esprit.Map.persistences.JobRequest;
@Local
public interface JobRequestLocal {
	public String AddJobRequest(JobRequest jobRequest);
	public String UpdateJobRequest(int id);
	public String DeleteJobRequest(int id);
	public String ViewAllRequested();
}


