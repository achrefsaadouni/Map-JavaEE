package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Map.interfaces.StatSideAdminInterfaceRemote;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless

public class StatAdminService implements StatSideAdminInterfaceRemote{
	@PersistenceContext(unitName = "MAP"/* , type=PersistenceContextType.EXTENDED */)
	EntityManager em;

	@Override
	public Long CountNbPersonByRole(String role) {
		// TODO Auto-generated method stub
		
		 String query = "SELECT DISTINCT count(*) from Person where role=:rol";
			TypedQuery<Long> q = em.createQuery(query,Long.class);
			q.setParameter("rol", role);

			return q.getSingleResult();
	}

	@Override
	public List<Object[]> CountNbClientByCategory() {
		// TODO Auto-generated method stub
		
		 List<Object[]> results = em.createQuery("SELECT DISTINCT p.clientCategory,count(*) from Person p where p.clientCategory is not null group by p.clientCategory", Object[].class).getResultList();
		return results;
	}

	@Override
	public List<Object[]> CountNbClientByType() {
		// TODO Auto-generated method stub
		 List<Object[]> results = em.createQuery("SELECT DISTINCT p.clientType,count(*) from Person p where p.clientType is not null group by p.clientType", Object[].class).getResultList();
		 return results;
	}

	@Override
	public List<Object[]> CountNbPersonByRegionAndRole(String role) {
		// TODO Auto-generated method stub
		String query = "SELECT DISTINCT count(*),address from Person WHERE role=:rol group by address ";
		Query q = em.createQuery(query);
		q.setParameter("rol", role);

		return q.getResultList();
	}

	@Override
	public List<Object[]> CountNbProjectByClient() {
		// TODO Auto-generated method stub
		
		 List<Object[]> results = em.createQuery("SELECT DISTINCT count(*),client.firstName from Project group by clientId ", Object[].class).getResultList();
		 return results;
	}

	@Override
	public List<Object[]> CountNbProjectByRegion() {
		// TODO Auto-generated method stub
		
		 List<Object[]> results = em.createQuery("SELECT DISTINCT count(*),address from Project group by address", Object[].class).getResultList();
		 return results;
	}

	@Override
	public List<Object[]> CountNbCandidateByState() {
		// TODO Auto-generated method stub
		 List<Object[]> results = em.createQuery("SELECT DISTINCT count(*),candidateState from Person WHERE candidateState is NOT NULL group by candidateState", Object[].class).getResultList();
		 return results;
	}

	@Override
	public List<Object[]> CountNbProjectEnded() {
		// TODO Auto-generated method stub
		 List<Object[]> results = em.createQuery("select count(*),'Expired' from Project where endDate < CURRENT_DATE", Object[].class).getResultList();
		 return results;
		}

	@Override
	public List<Object[]> CountNbProjectNotEnded() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("select count(*),'NotExpired' from Project where endDate > CURRENT_DATE", Object[].class).getResultList();
		 return results;	}

	@Override
	public List<Object[]> CountNbProjectByType() {
		// TODO Auto-generated method stub
		 List<Object[]> results = em.createQuery("SELECT DISTINCT count(*),projectType from Project group by projectType", Object[].class).getResultList();
		 return results;
		
	}

	@Override
	public List<Object[]> CountNbRhByAvailability() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("SELECT DISTINCT count(*),availability from Person where availability is not NULL group by availability", Object[].class).getResultList();
		 return results;
	}

	@Override
	public List<Object[]> BeneficeByProject() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("select ROUND("
				+ "sum("
				+ " ( m.montant/DATEDIFF(m.mandateId.dateFin,m.mandateId.dateDebut)"
				+ " -(m.ressource.salary/30) )"
				+ "*DATEDIFF(m.mandateId.dateFin,m.mandateId.dateDebut) ),2),"
				+ "m.projet.projectName "
				+ "from Mandate m "
				+ "group by m.projet.id", Object[].class).getResultList();
		return results;
	}

	@Override
	public List<Object[]> BeneficeByClient() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("select ROUND(sum( ( m.montant/DATEDIFF(m.mandateId.dateFin,m.mandateId.dateDebut) -(m.ressource.salary/30) )*DATEDIFF(m.mandateId.dateFin,m.mandateId.dateDebut) ),2),m.projet.client.firstName from Mandate m group by m.projet.client.id", Object[].class).getResultList();
		return results;	}

}
