package tn.esprit.Map.interfaces;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.Candidate;

@Local
public interface CandidateLocalService {
	

	public void AddCandidateToDB(Candidate ct);
	public Boolean ChangeCandidateState( int id);
	public Boolean AssignCandidateToProject(int idc , int idp);
	public void ChangeCandidateToRessource( int id);
	public void RequestTheMinister();
	public Boolean RemoveCandidateFromProject(int idc );
	public List<Candidate> GetAllCandidates();
	public void NotifyCandidate(int id );
	
	
}
