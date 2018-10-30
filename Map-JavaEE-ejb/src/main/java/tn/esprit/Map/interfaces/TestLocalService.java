package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.Test;

@Local
public interface TestLocalService {
	
	public void TechAddTest(Test test);
	public void FrenchAddTest(Test test);
	public void EditTest();
	public Boolean deletetest(int id );
	public void AffectTestToCandidate(int id , int idt);
	public List<Test> CheckMyTestResult(int id);
	public Boolean ResponseToTest(Test test );
	public Boolean AddEmploymentLetter(Test test );
	public Boolean AcceptTest( int id );
	public Boolean RefuseTest( int id );
	public List<Test> ShowAllTests();
	//public void NotifyCandidate(  );
	public Boolean CheckTestUpload(int id);
	
	

}
