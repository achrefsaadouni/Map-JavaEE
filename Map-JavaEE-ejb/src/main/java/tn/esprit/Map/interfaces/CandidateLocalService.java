package tn.esprit.Map.interfaces;
import javax.ejb.Local;

import tn.esprit.Map.persistences.Candidate;

@Local
public interface CandidateLocalService {

	public void AddCandidateToDB(Candidate ct);
	public Boolean ChangeCandidateState( int id);
	public void AssignCandidateToProject();
	public void ChangeCandidateToRessource( int id);
	public void RequestTheMinister();
	
	
}
