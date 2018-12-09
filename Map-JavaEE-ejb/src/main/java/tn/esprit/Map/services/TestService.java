package tn.esprit.Map.services;

import java.sql.Timestamp;
import java.util.List;
import tn.esprit.Map.persistences.Candidate;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.TestLocalService;
import tn.esprit.Map.persistences.Test;
import tn.esprit.Map.persistences.TestResult;
import tn.esprit.Map.persistences.TestType;

@Stateless
public class TestService implements TestLocalService{
	@PersistenceContext(unitName = "MAP")
	

	
	private EntityManager em;
	@Override
	public void TechAddTest(Test test) {
		test.setTestType(TestType.TechnicalTest.TechnicalTest);
		test.setTestUploadTime(null);
		em.persist(test);
		
	}

	@Override
	public void FrenchAddTest(Test test) {
		test.setTestType(TestType.TechnicalTest.FrenchTest);
		test.setTestUploadTime(null);
		em.persist(test);
		
	}

	@Override
	public void EditTest() {
		
	}

	@Override
	public Boolean deletetest( int id) {
	
		Test test = em.find(Test.class, id);
		if(test != null )
		{
			em.remove(test);
			em.flush();
			return true ;
		}
		return false ;
		
	}

	@Override
	public void AffectTestToCandidate(int id , int idt) {
		Test test = em.find(Test.class,idt);
		Candidate candidate = em.find(Candidate.class, id);
		if(test != null && candidate != null)
		{
			test.setCandidate(candidate);
			em.flush();
			System.out.println("done");
		}
		System.out.println("someting wrong");
		
	}

	@Override
	public List<Test> CheckMyTestResult(int id ) {
		Candidate candidate = em.find(Candidate.class, id);
		TypedQuery<Test> query = em.createQuery("SELECT t  FROM Test t where t.candidate = :candidate",Test.class)
				.setParameter("candidate", candidate);		
		List<Test> test =  query.getResultList();
		
		return test;
		
		
	}


	@Override
	public Boolean ResponseToTest(Test test ) {
		  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		  while(timestamp.compareTo(test.getTestDeadLine() ) == -1)
		  {
		int query = em.createQuery("update Test t set t.TestResponseFile= :TestResponseFile , t.testUploadTime = :testUploadTime"
				+ "  where t.id = :id").
				setParameter("TestResponseFile",test.getTestResponseFile()).setParameter("testUploadTime", timestamp).setParameter("id",test.getId())
				.executeUpdate();
		return true;
				
		  }
	
		return false ;
	}

	@Override
	public Boolean AddEmploymentLetter(Test test ) {
		
		int query = em.createQuery("update Test t set t.Employment_Letter= :Employment_Letter  where t.id = :id").
				setParameter("Employment_Letter",test.getEmployment_Letter()).setParameter("id",test.getId())
				.executeUpdate();
	
	
		return query != 0 ;
		
	}

	@Override
	public Boolean AcceptTest( int id) {
		Test test = em.find(Test.class, id);
		if(test != null)
		{
			test.setResult(TestResult.Accepted);
			return true;
		}
		return false ;
	}

	@Override
	public Boolean RefuseTest( int id ) {
		Test test = em.find(Test.class, id);
		if(test != null)
		{
			test.setResult(TestResult.Refused);
			return true;
		}
		return false ;
		
	}

	@Override
	public List<Test> ShowAllTests() {
		TypedQuery<Test> query = em.createQuery("SELECT t FROM Test t", Test.class);
		
			List<Test> results = query.getResultList();
			return results;	
	
		
	}

	/*@Override
	public void NotifyCandidate( ) {

	}*/

	@Override
	public Boolean CheckTestUpload(int id) {
		Test test = em.find(Test.class, id);
		return test.getTestResponseFile() != null ; 
		
	}

	

	

}
