package tn.esprit.Map.interfaces;

import java.util.List;
import java.util.Map;

import tn.esprit.Map.persistences.ProjectSkillModel;

public interface ProjectSkillRemote {
	public Map<String,String> getSkillsProject(int idProject);
	public List<ProjectSkillModel> getSkillsProjectModel(int idProject);
}
