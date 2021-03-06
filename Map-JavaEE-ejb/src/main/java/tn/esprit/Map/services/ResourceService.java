
package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import tn.esprit.Map.interfaces.ResourceRemote;
import tn.esprit.Map.persistences.AvailabilityType;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.JobType;
import tn.esprit.Map.persistences.Note;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.ResourceSkill;
import tn.esprit.Map.persistences.Role;
import tn.esprit.Map.persistences.Skill;

@Stateless
public class ResourceService implements ResourceRemote {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	
	@Override
	
	public Resource AddResource(Resource resource) {
		resource.setAvailability(AvailabilityType.available);
		resource.setArchived(0);
		resource.setMoyenneSkill(0);
		resource.setNombreAlerte(0);
		resource.setNombreConge(0);
		resource.setRoleT(Role.Resource);
		em.persist(resource);
		return resource;
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
			if(resource.getJobType()==JobType.LevioResource){
				projet.setLevioNumberResource(projet.getLevioNumberResource()+1);
			}
			else{
				projet.setTotalNumberResource(projet.getTotalNumberResource()+1);
				
			}
			em.persist(resource);
			return true;
		}

		return false;

	}

	@Override
	public Boolean UpdateAffectation(int resourceId, int projetId) {
		Resource resource = em.find(Resource.class, resourceId);
		Project projet = em.find(Project.class, projetId);
		if(projet == null){ return false;}
		resource.setProject(projet);
		em.merge(resource);
		return true;
	}
	
	

	@Override
	public List<tn.esprit.Map.persistences.Resource> listResource() {
		String request = "select res from Resource res WHERE res.archived =:val";
		Query query = em.createQuery(request);
		List<Resource> resources = query.setParameter("val", 0).getResultList();
		if (resources.isEmpty()) {
			return null;
		} else
		for(Resource r : resources){
			r.setNoteClient(null);}
			return resources;
	}

	@Override
	public Resource getResourceById(int idResource) {
		Query q = em.createQuery("select res from Resource res WHERE res.id= :id");
		Resource r =(Resource) q.setParameter("id", idResource).getSingleResult();
		//r.setProject(null);
		//r.setDayOffs(null);
		//r.setResourceSkills(null);
		return r;
	}

	@Override
	public List<Resource> getResourceArchive() {
		int valeur = 1;
		String role=Role.Resource.toString();
		String requestJPQL = "select res  from Resource res where res.archived=" + valeur+" AND res.roleT="+role+" ORDER BY res.moyenneSkill DESC";
		Query query = em.createQuery(requestJPQL);
		List<Resource> rs = (List<Resource>) query.getResultList();
		for(Resource r : rs){
			r.setResourceSkills(null);
			}
			
		return rs;
	}

	@Override
	public float moyenneResource(int idResource) {
		float moyenne =0;
		float somme=0;
		Query q = em.createQuery("SELECT rs FROM ResourceSkill rs where rs.resource.id=:id",ResourceSkill.class);
		List<ResourceSkill> listeResourceSkill = q.setParameter("id",idResource).getResultList();
		if(listeResourceSkill.size()==0){
			return 0;
		}
		for(ResourceSkill rs : listeResourceSkill){
			somme = somme+rs.getRateSkill();
		}	
		moyenne =somme/listeResourceSkill.size();
		Resource r = em.find(Resource.class,idResource);
		r.setMoyenneSkill(moyenne);
		return moyenne ;
		
		
	}

	@Override
	public Boolean noteResource(int resourceId, int clientId , float note) {
		Resource resource = em.find(Resource.class, resourceId);
		Client client = em.find(Client.class, clientId);
		Query q = em.createQuery("SELECT n FROM Note n WHERE n.resource =:resource AND n.client=:client");
		List<Note> notes = (List<Note>)q.setParameter("resource", resource).setParameter("client", client).getResultList();
		if(notes.size()!=0){
			return false;
		}
		if(note<0 || note > 20){
			return false;
		}
		Note n = new Note();
		n.setResource(resource);
		n.setClient(client);
		n.setNoteResource(note);
		em.persist(n);
		return true;
	}


	@Override
	public Boolean DeleteAffectation(int resourceId) {
		Resource resource = em.find(Resource.class, resourceId);
		resource.setAvailability(AvailabilityType.available);
		resource.setProject(null);
		em.merge(resource);
		return true;
	}

	@Override
	public List<Resource> ListResourceParMoyenne() {
		Query q = em.createQuery("SELECT r FROM Resource r Where r.archived = :archived AND r.availability = :Availibility AND r.roleT =:role "
				+ "ORDER BY r.moyenneSkill DESC");
		List<Resource> liste =(List<Resource>) q.setParameter("archived", 0).setParameter("Availibility", AvailabilityType.available)
				.setParameter("role", Role.Resource).getResultList();
		if(liste.size()==0){
			return null;
		}
		return liste;
			
	}

	@Override
	public List<Resource> getResourceNoArchive() {
		String role = Role.Resource.toString();
		int valeur = 0;
		String requestJPQL = "select res  from Resource res where res.archived=" + valeur+" AND res.roleT="+role+" ORDER BY res.moyenneSkill DESC";
		Query query = em.createQuery(requestJPQL);
		List<Resource> rs = (List<Resource>) query.getResultList();
		for(Resource r : rs){
			r.setResourceSkills(null);
			}
			
		return rs;
	}

	@Override
	public void Vu(int resourceId) {
	Resource r = em.find(Resource.class, resourceId);
	r.setFirstVisit(1);
	em.merge(r);
	}
}
