package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.ProjectSkill;
import tn.esprit.Map.persistences.Skill;

@Remote
public interface SkillRRemote {
	public int AddSkill(Skill skill);
	public int AddSkillProject (int idProject , int idSkill, int percentage) ;
	public Skill getSkillById (int idSkill) ;
	public List<Skill> getSkillsOfProject(int idProject);
	public int updatePercentageSkills (int idProject , int idSkill , int percentage);

}
