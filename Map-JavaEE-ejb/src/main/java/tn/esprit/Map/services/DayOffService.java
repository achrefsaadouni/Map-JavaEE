package tn.esprit.Map.services;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.Map.interfaces.DayOffRemote;

import tn.esprit.Map.persistences.DayOff;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.StateType;

@Stateless
public class DayOffService implements DayOffRemote{

	
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public Boolean AffecterDayOff(int resourceId, DayOff d) {
	   Resource resource = em.find(Resource.class, resourceId);
	   if(d.getStateType()== StateType.accepted){d.setColor("green");}
	   
	   if(d.getStateType()== StateType.refused){d.setColor("red");}
	   
	   if(d.getStateType()== StateType.onHold){d.setColor("blue");}
	   em.persist(d);
	   int x = d.getId();
	  
	   System.out.println(x);
	   DayOff d2 = new DayOff();
	   d2 = em.find(DayOff.class,x);
	   resource.getDayOffs().add(d2);
		//DayOff dayoff = em.find(DayOff.class,dayOffId);
		
		//resource.getDayOffs().add(dayoff);
		return true;
	}

	@Override
	public List<DayOff> listDayOffResource(int resourceId) {
		Query requete = em.createQuery("SELECT r.dayOffs FROM Resource r where r.id = :id");
		List<DayOff> dayOffs = (List<DayOff>) requete.setParameter("id", resourceId).getResultList();
		if(dayOffs.size()==0){
			return null;
		}
		return dayOffs;
	}

	
	
	
	
	
	
}
