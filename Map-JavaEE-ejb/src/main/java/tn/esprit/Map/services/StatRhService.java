package tn.esprit.Map.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.Map.interfaces.StatSideRhInterfaceRemote;
import tn.esprit.Map.persistences.DayOff;
import tn.esprit.Map.persistences.Resource;
@Stateless

public class StatRhService implements StatSideRhInterfaceRemote{
	@PersistenceContext(unitName = "MAP"/* , type=PersistenceContextType.EXTENDED */)
	EntityManager em;
	
	@Override
	public List<Object[]> RhRankedByNote() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("SELECT firstName,note FROM Person WHERE role='resource' ORDER by note desc ", Object[].class).getResultList();
		return results;	
		}

	 public int daysBetween(java.util.Date date, java.util.Date date2){
         return (int)( (date2.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
 }
	@Override
	public int RhRankedByMaxDayOff(int rhid) {
		// TODO Auto-generated method stub
		Resource rh=em.find(Resource.class, rhid);
		
		/*List<Object[]> results = em.createQuery("select sum(DATEDIFF(m.mandateId.dateFin,m.mandateId.dateDebut)) from Resource r", Object[].class).getResultList();
		return results;*/
		ArrayList<DayOff> list1=new ArrayList<DayOff>();
		int days=0;
		for(DayOff d:rh.getDayOffs()) {
			 days += daysBetween(d.getStartDate(),d.getEndDate());
			//list1.add(d);
			
		}
		return days;
	}

	

	@Override
	public List<Object[]> SkillsRecommended() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("SELECT NameSkill,RateSkill FROM Skill order by RateSkill desc", Object[].class).getResultList();
		return results;	
	}

	@Override
	public List<Object[]> NbClientByRegion() {
		// TODO Auto-generated method stub
		String query = "SELECT DISTINCT count(*),address from Person WHERE role='Client' group by address ";
		Query q = em.createQuery(query);

		return q.getResultList();
	}
	@Override
	public List<Object[]> CountNbProjectByRegion() {
		// TODO Auto-generated method stub
		
		 List<Object[]> results = em.createQuery("SELECT DISTINCT count(*),address from Project group by address", Object[].class).getResultList();
		 return results;
	}

}
