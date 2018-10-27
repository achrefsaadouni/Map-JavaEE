package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;

import tn.esprit.Map.interfaces.StatSideCandidateInterfaceRemote;

@Stateless
public class StatCandidateService implements StatSideCandidateInterfaceRemote {

	@Override
	public List<Object[]> MostRhLevioByProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> NbProjectByRegion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> RhRankedBySalary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> SkillsRecommended() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> MoyNoteByTypeTest() {
		// TODO Auto-generated method stub
		return null;
	}

}
