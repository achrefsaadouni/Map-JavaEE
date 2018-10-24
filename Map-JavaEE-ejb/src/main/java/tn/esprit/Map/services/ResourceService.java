package tn.esprit.Map.services;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.Map.interfaces.ResourceRemote;

@Stateless
public class ResourceService implements ResourceRemote {

	
	@PersistenceContext
	private EntityManager em;		
	
	@Override
	public int AddResource(tn.esprit.Map.persistences.Resource resource) {
		em.persist(resource);
		return resource.getId();
	}

	@Override
	public String UpdateResource(tn.esprit.Map.persistences.Resource resource) {
		em.merge(resource);
		return "Update avec succée";
	}

	@Override
	public String ArchiveResource(tn.esprit.Map.persistences.Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AffectResourceToProject(int resourceId, int ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String UpdateAffectation(int resourceId, int ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
