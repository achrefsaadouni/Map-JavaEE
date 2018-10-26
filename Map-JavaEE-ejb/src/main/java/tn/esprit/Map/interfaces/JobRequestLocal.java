package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.json.Json;

import tn.esprit.Map.persistences.JobRequest;

@Local
public interface JobRequestLocal {

	public void AddJobRequest(JobRequest st);
	public JobRequest UpdateJobRequest(JobRequest  id);
	public Boolean DeleteJobRequest(int id);
	public List<JobRequest> ViewAllRequested();
	public JobRequest ShowMyRequest(int id);
	public Boolean AcceptRequest(int id);
	public Boolean DeclineRequest(int id);
	public List<JobRequest> ShowAccepted();
	public Boolean FixDate(JobRequest jbr);
	
}
