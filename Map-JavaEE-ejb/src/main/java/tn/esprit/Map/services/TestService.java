package tn.esprit.Map.services;

import java.sql.Timestamp;
import java.util.List;

import tn.esprit.Map.persistences.CanTestPk;
import tn.esprit.Map.persistences.Candidate;
import tn.esprit.Map.persistences.Modules;
import tn.esprit.Map.persistences.Qcm;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.TestLocalService;
import tn.esprit.Map.persistences.Test;
import tn.esprit.Map.persistences.TestResult;
import tn.esprit.Map.persistences.TestScore;
import tn.esprit.Map.persistences.TestType;

@Stateless
public class TestService implements TestLocalService{
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	


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

	@Override
	public Test AddTest(Test test, int idc) {
			Candidate candidate = em.find(Candidate.class, idc);
			test.setCandidate(candidate);
			em.persist(test);
			return test;
			
	}

	@Override
	public void affectModuleToTest(int idTest, int idModule) {
		
		Test test = em.find(Test.class, idTest);
		Modules module = em.find(Modules.class, idModule);
		test.getModules().add(module);
		module.getTest().add(test);
		em.flush();
	}

	@Override
	public void AddTestScore(int idTest, int IdCandidate) {
	//	Test test = em.find(Test.class, idTest);
		//Candidate candidate = em.find(Candidate.class, IdCandidate);
		CanTestPk TestCandidateId = new CanTestPk();
		TestCandidateId.setId_Candidate(IdCandidate);
		TestCandidateId.setId_test(idTest);
		TestScore testScoreCandidate = new TestScore();
		testScoreCandidate.setIdTestScore(TestCandidateId);
		em.persist(testScoreCandidate);
		
	}

	@Override
	public List<Qcm> Create_Test(int idmodule) {
		Modules module = em.find(Modules.class, idmodule);
		
		TypedQuery<Qcm> query = em.createQuery("SELECT t FROM Qcm t where t.modules= :module ORDER BY RAND()", Qcm.class)
				.setParameter("module", module).setMaxResults(5);
		List<Qcm> results = query.getResultList();
		return results;
	}

	@Override
	public List<TestScore> getTestScore(int idc) {
		Candidate candidate = em.find(Candidate.class, idc);
		TypedQuery<TestScore> query = em.createQuery("SELECT t FROM TestScore t where t.candidate= :candidate", TestScore.class)
				.setParameter("candidate", candidate);
		
		List<TestScore> results = query.getResultList();
		return results;	
		
	}

	@Override
	public int setScoreToTest(int idc) {
		Candidate candidate = em.find(Candidate.class, idc);
		int query = em.createQuery("update TestScore t set t.score= t.score + 20  where t.candidate= :candidate ").
				setParameter("candidate",candidate)
				.executeUpdate();
		return query;
		
	}

	

	

}
