package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.FolderLocal;
import tn.esprit.Map.persistences.Candidate;
import tn.esprit.Map.persistences.CandidateFolder;

@Stateless
public class CandidateFolderService implements FolderLocal {
	
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	@Override
	public void AddFolder(CandidateFolder folder) {
		em.persist(folder);
	}

	@Override
	public CandidateFolder EditFolder(CandidateFolder folder) {
		
		int query = em.createQuery("update CandidateFolder c set c.Section_3= :Section_3 ,c.Motivation_Letter =:Motivation_Letter , c.medical_folder = :medical_folder,"
				+ "c.Passport = :Passport"
				+ " where c.id = :id").
				setParameter("Section_3", folder.getSection_3()).setParameter("Motivation_Letter", folder.getMotivation_Letter() ).
				setParameter("medical_folder", folder.getMedical_folder()).setParameter("Passport", folder.getPassport())
				.setParameter("id", folder.getId())
				.executeUpdate();
		
		return folder;

	}

	@Override
	public List<CandidateFolder> ShowMyFolder(int id) {
		Candidate idc = em.find(Candidate.class, id);
		TypedQuery<CandidateFolder> query = em.createQuery("SELECT c FROM CandidateFolder c where c.Candidate = :id", CandidateFolder.class)
				.setParameter("id", idc);
		List<CandidateFolder> results = query.getResultList();
		return results;
	}

	@Override
	public Boolean DeleteFolder(int id) {
		CandidateFolder folder = em.find(CandidateFolder.class, id);
		if(folder != null )
		{
			em.remove(folder);
			return true;
		}
		return false ;
	}

	@Override
	public List<CandidateFolder> getAllFolders() {
		TypedQuery<CandidateFolder> query = em.createQuery("SELECT c FROM CandidateFolder c", CandidateFolder.class);
		List<CandidateFolder> results = query.getResultList();
		return results;
	}

}
