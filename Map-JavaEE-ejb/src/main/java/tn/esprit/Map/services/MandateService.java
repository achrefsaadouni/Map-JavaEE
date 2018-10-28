package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.MandateServiceLocal;
import tn.esprit.Map.persistences.MailService;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.Request;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@Stateless
public class MandateService implements MandateServiceLocal {
	@PersistenceContext(unitName = "MAP")
	EntityManager em;
	@EJB
	MailService mail ;
	@Override
	public Resource SearchResourceBySkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAvailable(int resourceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void notify(String receiver,String subject,String body) {
		mail.send(receiver, subject, body);
	}

	@Override
	public List<Mandate> getAll() {
		List<Mandate> results;
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m", Mandate.class);
		try {
			results = query.getResultList();
		} catch (Exception e) {
			results = new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<Mandate> getByResource(int resourceId) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.ressourceId=:rId", Mandate.class);
		query.setParameter("rId", resourceId);
		List<Mandate> results = new ArrayList<>();
		results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByProject(int projectId) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.projetId = :pId", Mandate.class);
		query.setParameter("pId", projectId);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByStartDate(Date startDate) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.dateDebut = :startDate",
				Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByEndDate(Date endDate) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.dateFin = :endDate", Mandate.class);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByPeriod(Date startDate, Date endDate) {
		TypedQuery<Mandate> query = em
				.createQuery("SELECT m FROM Mandate m where m.dateFin BETWEEN :endDate AND :startDate", Mandate.class);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		List<Mandate> results = new ArrayList<>();
		results = query.getResultList();
		return results;
	}

	@Override
	public boolean archive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float calculateCost(Mandate mandate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void AlertEndMandate(Mandate mandate) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean addGps(int ressourceId, int projetId, Date startDate, Date endDate, int gpsId) {
		Mandate results;
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.dateDebut = :startDate AND m.dateFin = :endDate AND m.projetId = :pId AND  m.ressourceId=:rId",Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("pId", projetId);
		query.setParameter("rId", ressourceId);
		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", gpsId);
		Resource resourceGPS;
		try {
			resourceGPS = query1.getSingleResult();
			results = query.getSingleResult();
			results.setGps(resourceGPS);
			
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean addMandate(int requestId, int resourceId) {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r where r.id=:rId", Request.class);
		query.setParameter("rId", requestId);
		Request request;
		try {
			request = query.getSingleResult();
		} catch (Exception e)

		{
			return false;
		}

		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", resourceId);
		Resource resource;
		try {
			resource = query1.getSingleResult();
		} catch (Exception e) {

			return false;
		}

		Mandate mandate = new Mandate();
		mandate.setDateDebut(request.getStartDateMondate());
		mandate.setDateFin(request.getEndDateMondate());
		mandate.setProjetId(request.getProject().getId());
		mandate.setRessourceId(resource.getId());
		em.persist(mandate);
		return true;
	}

}
