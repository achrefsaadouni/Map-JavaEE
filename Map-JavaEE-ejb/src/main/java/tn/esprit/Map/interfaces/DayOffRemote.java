package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.DayOff;

@Remote
public interface DayOffRemote {
	
	public Boolean AffecterDayOff(int resourceId, DayOff d) ;
	public List<DayOff> listDayOffResource(int resourceId);
	

}
