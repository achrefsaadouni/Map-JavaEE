package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Map.interfaces.StatSideCandidateInterfaceRemote;

@Stateless

public class StatCandidateService implements StatSideCandidateInterfaceRemote {
	@PersistenceContext(unitName = "MAP"/* , type=PersistenceContextType.EXTENDED */)
	EntityManager em;

	@Override
	public List<Object[]> MostRhLevioByProjectName() {
		// TODO Auto-generated method stub
		List<Object[]> results = em
				.createQuery("SELECT projectName,levioNumberResource FROM Project order by levioNumberResource DESC",
						Object[].class)
				.getResultList();
		return results;
	}

	@Override
	public List<Object[]> NbProjectByRegion() {
		// TODO Auto-generated method stub
		List<Object[]> results = em
				.createQuery("SELECT DISTINCT count(*),address FROM Project group by address", Object[].class)
				.getResultList();
		return results;
	}

	@Override
	public List<Object[]> RhRankedBySalary() {
		// TODO Auto-generated method stub
				List<Object[]> results = em.createQuery("SELECT firstName,salary FROM Person WHERE role='resource' ORDER by salary desc ", Object[].class).getResultList();
				return results;	
	}

	@Override
	public List<Object[]> SkillsRecommended() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("SELECT NameSkill,RateSkill FROM Skill order by RateSkill desc", Object[].class).getResultList();
		return results;	
	}

	@Override
	public List<Object[]> CountCadidateResultByTypeTest() {
		// TODO Auto-generated method stub
		List<Object[]> results = em.createQuery("Select round(avg(case when result='Accepted' then 100.0 else 0.0 end),2) as accepted,round(avg(case when result='Refused'\r\n" + 
				" then 100.0 else 0.0 end),2) as refused,TestType From Test group by TestType", Object[].class).getResultList();
		return results;
	}
	//

	@Override
	public List<String> ListSuccedCandidate() {
		// TODO Auto-generated method stub
		List<String> results = em.createQuery("select candidate.firstName from Test where result='Accepted' group by candidate.id having count(candidate.id)>1 ", String.class).getResultList();
		return results;
	}
}
