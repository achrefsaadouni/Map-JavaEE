package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote

public interface StatSideClientInterfaceRemote {
	
	public List<Object[]> CandidateRankedByNoteTest();
	public List<Object[]> CandidateRankedByNbJobRequest();
	public List<Object[]> RhAvailableRankedByNote();
	public List<Object[]> MyProjectsCharges(int idprject);
	public List<Object[]> RhRankedBymaxDayOff(int idproject);
	public List<Object[]> RhRankedByminDayOff(int idproject);
	public List<Object[]> SkillsRanked();
	public List<Object[]> RhRankedByMostNbSkills();
	public List<Object[]> RhRankedByMostRankedSkills();



	



}
