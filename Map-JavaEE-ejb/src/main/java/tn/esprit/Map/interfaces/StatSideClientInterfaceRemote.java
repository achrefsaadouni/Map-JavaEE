package tn.esprit.Map.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Resource;

@Remote

public interface StatSideClientInterfaceRemote {
	
	public List<String> CandidateSuccedTest();//done
	public List<Object[]> CandidateRankedByNbJobRequest();//done++
	public List<Object[]> RhAvailableRankedByNote();//done
	public List<Object[]> MyProjectsCharges(int idprject);//done+++
	public List<Integer>  ListRhIdByProject(int idproject);//done
	public List<Object[]> SkillsRanked();//done
	public Map<String,Integer> RhRankedByMostNbSkills();//done++++
	public List<Object[]> RhRankedByMostRankedSkills();



	



}
