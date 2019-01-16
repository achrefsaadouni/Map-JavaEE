package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.StatSideClientInterfaceRemote;
import tn.esprit.Map.persistences.DayOff;
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Resource;

@Stateless

public class StatClientService implements StatSideClientInterfaceRemote{
	@PersistenceContext(unitName = "MAP"/* , type=PersistenceContextType.EXTENDED */)
	EntityManager em;

	@Override
	public List<String> CandidateSuccedTest() {
		// TODO Auto-generated method stub
		List<String> results = em.createQuery("select candidate.firstName from Test where result='Accepted' group by candidate.id having count(candidate.id)>1 ", String.class).getResultList();
		return results;
	}

	@Override
	public List<Object[]> CandidateRankedByNbJobRequest() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("SELECT count(*) as nb,j.candidate.firstName FROM JobRequest j group by j.candidate.id ORDER by nb desc", Object[].class).getResultList();
		return results;
	}

	@Override
	public List<Object[]> RhAvailableRankedByNote() {
		// TODO Auto-generated method stub
				List<Object[]> results = em.createQuery("SELECT firstName,note FROM Person where availability='available' order by note desc", Object[].class).getResultList();
				return results;		
	}

	@Override
	public List<Object[]> MyProjectsCharges(int idclient) {
		// TODO Auto-generated method stub
			
		String query = "select ROUND(sum(m.montant),2),m.projet.projectName from Mandate m where m.projet.client.id=:idclient group by m.projet.id";
		Query q = em.createQuery(query);
		q.setParameter("idclient",idclient);
		return q.getResultList();	
	}
	 public int daysBetween(java.util.Date date, java.util.Date date2){
         return (int)( (date2.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
 }
	@Override
	public List<Integer> ListRhIdByProject(int idproject) {
		// TODO Auto-generated method stub
		TypedQuery<Integer> q= em.createQuery("SELECT id FROM Person  WHERE role='resource' and project.id=:idproject",Integer.class);
		q.setParameter("idproject",idproject);
		return q.getResultList();
		
	}


	@Override
	public List<Object[]> SkillsRanked() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("SELECT NameSkill,RateSkill FROM Skill order by RateSkill desc", Object[].class).getResultList();
		return results;
		}

	@Override
	public Map<String,Integer> RhRankedByMostNbSkills() {
		// TODO Auto-generated method stub
		List<Integer> listeId=em.createQuery("select  id from Person where role='resource' ",Integer.class).getResultList();
		List<Resource> listerh=new ArrayList<Resource>();
		for(Integer i:listeId) {
			
			listerh.add((Resource) em.find(Person.class, i));
		}
		
		Map<String,Integer> maprh=new HashMap<String,Integer>();
		for(Resource r:listerh) {
			maprh.put(r.getFirstName(), r.getResourceSkills().size());
		}
		return maprh;
	}

	@Override
	public List<Object[]> RhRankedByMostRankedSkills() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
