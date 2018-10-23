package tn.esprit.Map.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.MandateServiceLocal;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@Stateless
public class MandateService implements MandateServiceLocal {
	@PersistenceContext(unitName = "MAP")
	EntityManager em;

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
	public void notify(String receiver) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Mandate> getAll() {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m", Mandate.class);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByResource(int resourceId) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m", Mandate.class);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByProject(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mandate> getByStartDate(Date startDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mandate> getByEndDate(Date endDate) {
		// TODO Auto-generated method stub
		return null;
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

}
