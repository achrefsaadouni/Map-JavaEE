package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.Qcm;
import tn.esprit.Map.persistences.Test;
import tn.esprit.Map.persistences.TestScore;

@Local
public interface TestLocalService {
	
	public Test AddTest(Test test , int idc);
	public void affectModuleToTest(int idTest, int idModule);
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
	public void AddTestScore(int idTest, int IdCandidate);
	public List<Qcm> Create_Test(int idmodule);
	public List<TestScore> getTestScore(int idc);
	public int setScoreToTest(int idc);
	

}
