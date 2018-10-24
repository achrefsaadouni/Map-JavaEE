package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.JobRequestLocal;
import tn.esprit.Map.persistences.JobRequest;

@Stateless
public class JobRequestService implements JobRequestLocal{
	
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	

	@Override
	public String UpdateJobRequest(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DeleteJobRequest(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobRequest> ViewAllRequested() {
		TypedQuery<JobRequest> query = em.createQuery("SELECT j FROM JobRequest j", JobRequest.class);
		List<JobRequest> results = query.getResultList();
		return results;
	}

	@Override
	public void AddJobRequest(JobRequest jobRequest) {
		// TODO Auto-generated method stub
		
	}

	




}
