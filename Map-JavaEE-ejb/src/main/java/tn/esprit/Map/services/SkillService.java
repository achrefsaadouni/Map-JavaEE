package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.Map.interfaces.ResourceRemote;
import tn.esprit.Map.interfaces.SkillRemote;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.ResourceSkill;
import tn.esprit.Map.persistences.Skill;

@Stateless
public class SkillService implements SkillRemote {

	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	ResourceRemote resourceRemote;
	@Override
	public void AddSkill(Skill skill) {
		em.persist(skill);

	}

	@Override
	public Boolean UpdateSkill(Skill skill) {
		Query q = em.createQuery("SELECT s FROM Skill s WHERE s.IdSkill= :id", Skill.class);
		List<Skill> skills = (List<Skill>) q.setParameter("id", skill.getIdSkill()).getResultList();
		if (skills.size() == 0) {
			return false;
		}
		Skill s = skills.get(0);
		s.setIdSkill(skill.getIdSkill());
		s.setNameSkill(skill.getNameSkill());
		em.merge(s);
		return true;
	}

	@Override
	public void DeleteSkill(int IdSkill) {
		Query q = em.createQuery("SELECT s FROM Skill s WHERE s.IdSkill= :id", Skill.class);
		List<Skill> skills = (List<Skill>) q.setParameter("id", IdSkill).getResultList();
		em.remove(skills.get(0));

	}

	@Override
	public Boolean AddSkillResource(int skillId, int resourceId) {
		Skill s = em.find(Skill.class, skillId);
		Resource r = em.find(Resource.class, resourceId);
		ResourceSkill rsExist = ExistResourceSkill(skillId, resourceId);
		if (rsExist != null) {
			return false;
		}
		ResourceSkill rs = new ResourceSkill();
		rs.setResource(r);
		rs.setSkill(s);
		em.persist(rs);
		return true;

	}

	@Override
	public String UpdateSkillResource(int skillId, int resourceId) {
		return null;
	}

	@Override
	public Boolean DeleteSkillResource(int skillId, int resourceId) {
		Skill s = em.find(Skill.class, skillId);
		Resource r = em.find(Resource.class, resourceId);
		ResourceSkill rs = ExistResourceSkill(skillId, resourceId);
		if (rs == null) {
			return false;
		}
		em.remove(rs);
		return true;
	}

	@Override
	public List<Skill> orderSkillsOfResource(int ResourceId) {
		Resource r = em.find(Resource.class, ResourceId);
		Query q = em.createQuery(
				"SELECT DISTINCT rs.skill FROM ResourceSkill rs where rs.resource=:r ORDER BY rs.rateSkill desc");
		List<Skill> listeSkill = (List<Skill>) q.setParameter("r", r).getResultList();

		for (Skill s : listeSkill) {
			s.setSkillResources(null);

		}
		return listeSkill;
	}

	@Override
	public List<Resource> orderResourcesOfSkill(int SkillId) {
		Skill s = em.find(Skill.class, SkillId);
		Query q = em.createQuery(
				"SELECT DISTINCT rs.resource FROM ResourceSkill rs where rs.skill=:s ORDER BY rs.rateSkill ");
		List<Resource> listeResource = (List<Resource>) q.setParameter("s", s).getResultList();

		for (Resource r : listeResource) {
			r.setResourceSkills(null);
			;

		}
		return listeResource;

	}

	@Override
	public List<Skill> ListSkills() {
		Query q = em.createQuery("SELECT s FROM Skill s");
		List<Skill> skills = q.getResultList();
		if (skills.size() == 0) {
			return null;
		}
		for (Skill s : skills) {
			s.setSkillResources(null);
			
		}
		return skills;
	}

	@Override
	public ResourceSkill ExistResourceSkill(int skillId, int resourceId) {
		Skill s = em.find(Skill.class, skillId);
		Resource r = em.find(Resource.class, resourceId);
		Query q = em.createQuery("SELECT Rs FROM ResourceSkill Rs WHERE Rs.skill= :skill AND Rs.resource = :resource");
		List<ResourceSkill> rs = (List<ResourceSkill>) q.setParameter("skill", s).setParameter("resource", r)
				.getResultList();
		if (rs.size() == 0) {
			return null;
		}
		return rs.get(0);
	}

	@Override
	public Boolean RateSkill(int ResourceId, int SkillId, float rate) {
		ResourceSkill resourceSkill = ExistResourceSkill(SkillId, ResourceId);
		if(resourceSkill != null){
			if(rate > 0 && rate < 100){
			resourceSkill.setRateSkill(rate);
			return true;}
		}
		return false;
	}

	@Override
	public List<Skill> orderSkillsOfProjecte(int ProjectId) {
		Project r = em.find(Project.class, ProjectId);
		Query q = em.createQuery(
				"SELECT DISTINCT rs.skill FROM ProjectSkill rs where rs.project=:r ORDER BY rs.percentage desc");
		List<Skill> listeSkill = (List<Skill>) q.setParameter("r", r).getResultList();
		for (Skill s : listeSkill) {
			s.setSkillResources(null);
		}
		return listeSkill;
	}


	/*
	 * @Override public List<ResourceSkill> SkillsParResource(int idResource) {
	 * Resource r = em.find(Resource.class, idResource); Query q =
	 * em.createQuery(
	 * "SELECT DISTINCT rs FROM ResourceSkill rs where rs.resource=:r ORDER BY rs.rateSkill"
	 * ); List<ResourceSkill> listeSkill =
	 * (List<ResourceSkill>)q.setParameter("r", r).getResultList();
	 * 
	 * for(ResourceSkill s : listeSkill){
	 * s.getResource().setResourceSkills(null);
	 * s.getSkill().setSkillResources(null);
	 * 
	 * } return listeSkill; }
	 */
}
