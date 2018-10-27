package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote

public interface StatSideAdminInterfaceRemote {

	public Long CountNbPersonByRole(String role);//done
	public List<Object[]> CountNbClientByCategory();//done
	public List<Object[]> CountNbClientByType();//done
	public List<Object[]> CountNbPersonByRegionAndRole(String role);//done++
	public List<Object[]> CountNbProjectByClient();//done++
	public List<Object[]> CountNbProjectByRegion();//done++
	public List<Object[]> CountNbCandidateByState();//done
	public List<Object[]> CountNbProjectEnded();//done++
	public List<Object[]> CountNbProjectNotEnded();//done
	public List<Object[]> CountNbProjectByType();//done
	public List<Object[]> CountNbRhByAvailability();//done
	public List<Object[]> BeneficeByProject();//done++++
	public List<Object[]> BeneficeByClient();//done+++++

	
}
