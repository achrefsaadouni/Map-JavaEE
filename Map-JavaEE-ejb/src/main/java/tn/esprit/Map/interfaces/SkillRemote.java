package tn.esprit.Map.interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.ResourceSkill;
import tn.esprit.Map.persistences.Skill;

@Remote
public interface SkillRemote {

	public void AddSkill(Skill skill);

	public Boolean UpdateSkill(Skill skill);

	public void DeleteSkill(int skillId);

	public List<Skill> ListSkills();

	public ResourceSkill ExistResourceSkill(int skillId, int resourceId);

	public Boolean AddSkillResource(int skillId, int resourceId);

	public String UpdateSkillResource(int skillId, int resourceId);

	public Boolean DeleteSkillResource(int skillId, int resourceId);

	public List<Skill> orderSkillsOfResource(int ResourceId);
	
	public List<Skill> orderSkillsOfProjecte(int ProjectId);
	
	//public List<ResourceSkill> SkillsParResource(int idResource);

	public List<Resource> orderResourcesOfSkill(int SkillId);
	
	public Boolean RateSkill(int ResourceId , int SkillId , float rate);
	

}
