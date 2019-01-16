package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.Map.interfaces.SkillRRemote;
import tn.esprit.Map.persistences.PK_ProjectSkill;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectSkill;
import tn.esprit.Map.persistences.Skill;

@Stateless
public class SkillRService implements SkillRRemote{
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public int AddSkill(Skill skill) {
		em.persist(skill);
		return skill.getIdSkill();
	}
	
	@Override
	public int AddSkillProject(int idProject , int idSkill , int percentage) {
		ProjectSkill ps = new ProjectSkill();
		PK_ProjectSkill pKey = new PK_ProjectSkill() ;
		Skill skill = em.find(Skill.class, idSkill) ;
		Project project = em.find(Project.class, idProject) ;
		pKey.setIdProject(idProject);
		pKey.setIdSkill(idSkill);
		
		ps.setPk_ProjectSkill(pKey);
		ps.setProject(project);
		ps.setSkill(skill);
		ps.setPercentage(percentage);
		em.persist(ps);
		return ps.getPercentage();
	}

	@Override
	public List<Skill> getSkillsOfProject(int idProject) {
		Query query = em.createQuery("SELECT distinct  s from Skill s , Project p , ProjectSkill ps where ps.project=p and ps.skill=s and p.id= :idProject");
		query.setParameter("idProject",idProject);
		List<Skill> skills = query.getResultList();
		return skills;
	}

	@Override
	public int updatePercentageSkills(int idProject, int idSkill , int percentage) {
		Project project = em.find(Project.class, idProject);
		Skill skill = em.find(Skill.class, idSkill);
		Query query = em.createQuery("update ProjectSkill ps set  ps.percentage= :percentage  where ps.project= :project and ps.skill= :skill");
		query.setParameter("project", project);
		query.setParameter("skill", skill);
		query.setParameter("percentage", percentage);
		int modified = query.executeUpdate();
		if (modified == 1) {
			return 0;
		} else {
			return -1 ;
		}

	}

	@Override
	public Skill getSkillById(int idSkill) {
		Skill skill = em.find(Skill.class,idSkill) ;
		return skill;
	}


	
}
