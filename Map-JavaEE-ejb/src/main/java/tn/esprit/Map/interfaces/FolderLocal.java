package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.CandidateFolder;

@Local 
public interface FolderLocal {
	
	public void AddFolder( CandidateFolder folder );
	public CandidateFolder EditFolder( CandidateFolder folder);
	public List<CandidateFolder> ShowMyFolder( int id );
	public Boolean DeleteFolder(int id );
	public List<CandidateFolder> getAllFolders();

}
