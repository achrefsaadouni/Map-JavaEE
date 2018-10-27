package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote

public interface StatSideCandidateInterfaceRemote {
	
	public List<Object[]> MostRhLevioByProjectName();
	public List<Object[]> NbProjectByRegion();
	public List<Object[]> RhRankedBySalary();
	public List<Object[]> SkillsRecommended();
	public List<Object[]> MoyNoteByTypeTest();





}
