package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Skill;

@Remote
public interface SkillRemote {
	
	public int AddSkill(Skill skill);
	public String UpdateSkill(Skill skill);
	public String DeleteSkill(Skill skill);
	

}
