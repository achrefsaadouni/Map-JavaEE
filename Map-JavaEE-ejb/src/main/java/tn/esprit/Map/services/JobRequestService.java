package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.JobRequestLocal;
import tn.esprit.Map.persistences.JobRequest;
import tn.esprit.Map.persistences.StateType;

@Stateless
public class JobRequestService implements JobRequestLocal {

	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public JobRequest UpdateJobRequest(JobRequest  jbr) {
		
		int query = em.createQuery("update JobRequest j set j.Cv= :Cv where j.id = :id").
				setParameter("Cv", jbr.getCv()).setParameter("id", jbr.getId())
				.executeUpdate();
		
		return jbr;
	}

	@Override
	public Boolean DeleteJobRequest(int id) {

		JobRequest jbr = em.find(JobRequest.class, id);
		if (jbr != null) {
			em.remove(jbr);
			return true;
		}
		return false;
	}

	@Override
	public List<JobRequest> ViewAllRequested() {
		TypedQuery<JobRequest> query = em.createQuery("SELECT j FROM JobRequest j", JobRequest.class);
		List<JobRequest> results = query.getResultList();
		return results;
	}

	@Override
	public void AddJobRequest(JobRequest st) {
		em.persist(st);
		System.out.println("Job Request successful");

	}

	@Override
	public JobRequest ShowMyRequest(int id) {
		JobRequest jbr = em.find(JobRequest.class, id);
		return jbr;
	}

	@Override
	public Boolean AcceptRequest(int id) {
		JobRequest jbr =em.find(JobRequest.class, id);
		if (jbr != null  && !(jbr.getStateType().equals(StateType.accepted))) {
			jbr.setStateType(StateType.accepted);
			em.flush();
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean DeclineRequest(int id) {
		JobRequest jbr =em.find(JobRequest.class, id);
		if (jbr != null  && !(jbr.getStateType().equals(StateType.refused))) {
			jbr.setStateType(StateType.refused);
			em.flush();
			return true;
		}
		
		return false;
	}

	@Override
	public List<JobRequest> ShowAccepted() {
		StateType state = StateType.accepted ;
		TypedQuery<JobRequest> query = em.createQuery("SELECT j FROM JobRequest j where j.stateType = :state ", JobRequest.class)
				.setParameter("state",state );
		List<JobRequest> results = query.getResultList();
		return results;
	}

	@Override
	public Boolean FixDate(JobRequest jbr) {
		
		int query = em.createQuery("update JobRequest j set j.rdvdate= :rdvdate where j.id = :id").
				setParameter("rdvdate", jbr.getRdvdate()).setParameter("id", jbr.getId())
				.executeUpdate();
		
		return query != 0;
	}

}
