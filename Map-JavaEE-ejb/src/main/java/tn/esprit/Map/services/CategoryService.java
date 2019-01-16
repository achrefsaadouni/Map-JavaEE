package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.CandidateLocalService;
import tn.esprit.Map.interfaces.CategoriesService;
import tn.esprit.Map.persistences.Candidate;
import tn.esprit.Map.persistences.Category;
import tn.esprit.Map.persistences.JobRequest;

@Stateless
public class CategoryService implements CategoriesService{
	
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public Category AddCategory(Category c) {
		em.persist(c);
		return c;
		
	}

	@Override
	public List<Category> showCategory() {
		TypedQuery<Category> query = em.createQuery("SELECT C FROM Category C", Category.class);
		List<Category> results = query.getResultList();
		return results;
	}

	@Override
	public Boolean deleteCategory(int id) {
		Category cat = em.find(Category.class, id);
		if (cat != null) {
			em.remove(cat);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateCategory(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
