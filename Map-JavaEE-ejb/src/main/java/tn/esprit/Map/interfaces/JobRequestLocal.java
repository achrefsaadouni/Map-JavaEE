package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;


import tn.esprit.Map.persistences.JobRequest;

@Local
public interface JobRequestLocal {

	public void AddJobRequest(JobRequest jobRequest);
	public String UpdateJobRequest(int id);
	public String DeleteJobRequest(int id);
	public List<JobRequest> ViewAllRequested();
	
	
	
}


