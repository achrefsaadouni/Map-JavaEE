package tn.esprit.Map.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.Map.interfaces.ProjectSkillRemote;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.ProjectSkillModel;
import tn.esprit.Map.persistences.ProjectType;
@Stateless
public class ProjectSkillService implements ProjectSkillRemote{
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public Map<String, String> getSkillsProject(int idProject) {
		Map<String, String> listSkills =new HashMap<String, String>(); ;
		Query query = em.createQuery("select s.NameSkill , ps.percentage from Project p , Skill s , ProjectSkill ps where p.id= :id and ps.project = p  and ps.skill = s");
		query.setParameter("id", idProject);
		List<Object[]> list = query.getResultList();
		   for (Object[] ob : list){
			   listSkills.put((String)ob[0], (String)ob[1].toString()) ;
		   	}
				
		return listSkills;
	}
	@Override
	public List<ProjectSkillModel> getSkillsProjectModel(int idProject) {
		Query query = em.createQuery("select s.NameSkill , ps.percentage from Project p , Skill s , ProjectSkill ps where p.id= :id and ps.project = p  and ps.skill = s");
		query.setParameter("id", idProject);
		List<Object[]> res = query.getResultList();
		List<ProjectSkillModel> listProjectSkill = new ArrayList<ProjectSkillModel>();
		res.forEach(array -> {
			ProjectSkillModel projectSkill = arrayToProjectSkill(array);
			listProjectSkill.add(projectSkill);
		});
		return listProjectSkill;
	}
	public ProjectSkillModel arrayToProjectSkill(Object[] array) {
		ProjectSkillModel projectSkill = new ProjectSkillModel();
		projectSkill.setSkillName((String) array[0]);
		projectSkill.setPercentage((int) array[1]);
		return projectSkill;
	}

}
