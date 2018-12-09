package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.CandidateLocalService;
import tn.esprit.Map.persistences.Candidate;
import tn.esprit.Map.persistences.CandidateState;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.Resource;

@Stateless
public class CandidateService implements CandidateLocalService {

	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@EJB
	MailService mail;

	@Override
	public void AddCandidateToDB(Candidate ct) {
		em.persist(ct);

	}

	@Override
	public Boolean ChangeCandidateState(int id) {
		Candidate candidate = em.find(Candidate.class, id);
		if (candidate != null) {
			candidate.setCandidateState(CandidateState.Candidate.Pre_employment);
			em.flush();
			return true;
		}

		return false;
	}

	@Override
	public Boolean AssignCandidateToProject(int idc, int idp) {
		Candidate candidate = em.find(Candidate.class, idc);
		Project project = em.find(Project.class, idp);
		if (candidate != null || project != null) {
			candidate.setProject(project);
			em.flush();
			return true;
		}

		return false;

	}

	@Override
	public void ChangeCandidateToRessource(int id) {

		Candidate cand = em.find(Candidate.class, id);
		Resource res = new Resource();
		res.setId(cand.getId());
		res.setFirstName(cand.getFirstName());
		res.setLastName(cand.getLastName());
		em.merge(res);
		em.remove(cand);
		em.flush();
	}

	@Override
	public void RequestTheMinister() {
		// mail.send("majdmn6@gmail.com", " Ask For a candidate Acceptation ", "
		// we levio .... ");

	}

	@Override
	public Boolean RemoveCandidateFromProject(int idc) {

		int query = em.createQuery("update Candidate c set c.project= :null where c.id = :id")
				.setParameter("null", null).setParameter("id", idc).executeUpdate();
		return (query != 0);

	}

	@Override
	public List<Candidate> GetAllCandidates() {

		TypedQuery<Candidate> query = em.createQuery("SELECT c FROM Candidate c", Candidate.class);

		List<Candidate> results = (List<Candidate>) query.getResultList();
		return results;

	}

	@Override
	public void NotifyCandidate(int id) {
		Candidate candidate = em.find(Candidate.class, id);
		mail.send(candidate.getEmail(), "Alert About Test",
				"We Levio company inform you Mr" + candidate.getFirstName()
						+ " that you have to pass your test before the deadline please check your application"
						+ " to know more",
				"", "", "", "", "", "", "");

	}

}
