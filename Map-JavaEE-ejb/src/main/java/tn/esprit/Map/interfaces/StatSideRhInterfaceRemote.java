package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote

public interface StatSideRhInterfaceRemote {

	public List<Object[]> RhRankedByNote();//done
	public int RhRankedByMaxDayOff(int rhid);//done++++
	public List<Object[]> SkillsRecommended();//done
	public List<Object[]> NbClientByRegion();//done
	public List<Object[]> CountNbProjectByRegion();//done




}
