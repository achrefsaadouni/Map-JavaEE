package tn.esprit.Map.interfaces;


import javax.ejb.Remote;

import tn.esprit.Map.persistences.Resource;


@Remote
public interface ResourceRemote {
	
	public int AddResource(Resource resource);
	public String UpdateResource(Resource resource);
	public String ArchiveResource(Resource resource);
	public String AffectResourceToProject(int resourceId , int ProjectId);
	public String UpdateAffectation(int resourceId , int ProjectId);

}
