
package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.Map.interfaces.ResourceRemote;
import tn.esprit.Map.persistences.AvailabilityType;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@Stateless
public class ResourceService implements ResourceRemote {

	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public void AddResource(Resource resource) {
		resource.setAvailability(AvailabilityType.available);
		resource.setArchived(0);
		resource.setMoyenneSkill(0);
		em.persist(resource);

	}

	@Override
	public void UpdateResource(Resource r) {
		em.merge(r);

	}

	@Override
	public Boolean ArchiveResource(int ResourceId) {
		Query requete = em.createQuery("SELECT r FROM Resource r where r.id = :id");
		List<Resource> resources = (List<Resource>) requete.setParameter("id", ResourceId).getResultList();
		if (resources.size() == 0) {
			return false;
		}
		resources.get(0).setArchived(1);
		resources.get(0).setAvailability(AvailabilityType.unavailable);
		em.merge(resources.get(0));
		return true;

	}

	@Override
	public Boolean UnArchiveResource(int ResourceId) {
		Query requete = em.createQuery("SELECT r FROM Resource r where r.id = :id");
		List<Resource> resources = (List<Resource>) requete.setParameter("id", ResourceId).getResultList();
		if (resources.size() == 0) {
			return false;
		}
		resources.get(0).setArchived(0);
		resources.get(0).setAvailability(AvailabilityType.available);
		em.merge(resources.get(0));
		return true;

	}

	@Override
	public Boolean AffectResourceToProject(int resourceId, int ProjectId) {
		Resource resource = em.find(Resource.class, resourceId);
		Project projet = em.find(Project.class, ProjectId);
		if (projet == null || resource == null) {
			return false;
		}
		if (resource.isArchived() == 0 && resource.getAvailability() == AvailabilityType.available) {
			resource.setAvailability(AvailabilityType.unavailable);
			resource.setProject(projet);
			em.merge(resource);
			return true;
		}

		return false;

	}

	@Override
	public String UpdateAffectation(int resourceId, int ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<tn.esprit.Map.persistences.Resource> listResource() {
		String request = "select res from Resource res";
		Query query = em.createQuery(request);
		List<Resource> resources = query.getResultList();
		if (resources.isEmpty()) {
			return null;
		} else
			return resources;
	}

	@Override
	public Resource getResourceById(int idResource) {
		Query q = em.createQuery("select res from Resource res WHERE res.id= :id");
		return (Resource) q.setParameter("id", idResource).getSingleResult();

	}

	@Override
	public List<Resource> getResourceArchive() {
		int valeur = 1;
		String requestJPQL = "select res.id , res.firstName , res.lastName , "
				+ "res.login , res.password  , res.availability , res.businessSector , "
				+ "res.cv , res.jobType , res.note , res.picture , res.salary, res.seniority , "
				+ "res.workProfil , res.archived , res.email  from Resource res where res.archived=" + valeur;
		Query query = em.createQuery(requestJPQL);
		List<Resource> rs = (List<Resource>) query.getResultList();
		return rs;
	}

}
