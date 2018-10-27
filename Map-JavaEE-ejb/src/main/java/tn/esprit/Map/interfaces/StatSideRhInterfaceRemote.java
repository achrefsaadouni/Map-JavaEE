package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote

public interface StatSideRhInterfaceRemote {

	public List<Object[]> RhRankedByNote();
	public List<Object[]> RhRankedByMaxDayOff();
	public List<Object[]> RhRankedByMinDayOff();
	public List<Object[]> SkillsRecommended();
	public List<Object[]> NbClientByRegion();




}
