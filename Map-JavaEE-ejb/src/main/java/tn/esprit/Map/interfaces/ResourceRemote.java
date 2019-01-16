
package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@Remote
public interface ResourceRemote {

	public Resource AddResource(Resource resource);

	public List<tn.esprit.Map.persistences.Resource> listResource();

	public tn.esprit.Map.persistences.Resource getResourceById(int id);

	public void UpdateResource(Resource r);

	public Boolean ArchiveResource(int resourceId);

	public Boolean UnArchiveResource(int resourceId);

	public Boolean AffectResourceToProject(int resourceId, int ProjectId);

	public Boolean UpdateAffectation(int resourceId, int projetId);
	
	public Boolean DeleteAffectation(int resourceId);

	public List<Resource> getResourceArchive();
	public List<Resource> getResourceNoArchive();
	public Boolean noteResource(int resourceId, int clientId , float note);
	
	public float moyenneResource(int resourceId);
	public List<Resource> ListResourceParMoyenne();
	public void Vu(int resourceId);
	


}
