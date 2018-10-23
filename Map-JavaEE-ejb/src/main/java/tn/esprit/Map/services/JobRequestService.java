package tn.esprit.Map.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.interfaces.JobRequestLocal;
import tn.esprit.Map.persistences.JobRequest;

@Stateless
public class JobRequestService implements JobRequestLocal{

	@Override
	public String AddJobRequest(JobRequest jobRequest) {
		// TODO Auto-generated method stub
		return null;
	}

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

}
