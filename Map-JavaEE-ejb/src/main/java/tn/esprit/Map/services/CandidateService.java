package tn.esprit.Map.services;

import javax.ejb.Stateless;

import tn.esprit.Map.interfaces.CandidateLocalService;
import tn.esprit.Map.persistences.Candidate;

@Stateless
public class CandidateService implements CandidateLocalService{

	@Override
	public void AddCandidateToDB(Candidate ct) {
		
		
	}

	@Override
	public Boolean ChangeCandidateState(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void AssignCandidateToProject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ChangeCandidateToRessource(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RequestTheMinister() {
		// TODO Auto-generated method stub
		
	}

}
