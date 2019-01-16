package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote

public interface StatSideCandidateInterfaceRemote {
	
	public List<Object[]> MostRhLevioByProjectName();//done
	public List<Object[]> NbProjectByRegion();//done
	public List<Object[]> RhRankedBySalary();//done
	public List<Object[]> SkillsRecommended();//done
	public List<Object[]> CountCadidateResultByTypeTest();//done+++
	public List<String> ListSuccedCandidate();//done++





}
