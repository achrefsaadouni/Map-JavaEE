package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.ModulesServices;
import tn.esprit.Map.persistences.Category;
import tn.esprit.Map.persistences.Modules;

@Stateless
public class ModulesService implements ModulesServices{
	
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	
	@Override
	public Modules AddModule(int idCategory, Modules Module) {
		Category cat = em.find(Category.class, idCategory);
		Module.setCategory(cat);
		em.persist(Module);
		return Module;
		
	}

	@Override
	public List<Modules> ShowAll() {
		TypedQuery<Modules> query = em.createQuery("SELECT m FROM Modules m", Modules.class);
		List<Modules> results = query.getResultList();
		return results;
		
	}

	@Override
	public Boolean deleteModule(int id) {
		Modules mod = em.find(Modules.class, id);
		if (mod != null) {
			em.remove(mod);
			return true;
		}
		return false;
	}

	@Override
	public Modules UpdateModule(Modules Module) {
		int query = em.createQuery("update Modules m set m.title= :title where m.id = :id").
				setParameter("title", Module.getTitle()).setParameter("id", Module.getId())
				.executeUpdate();
		
		return Module;
	}

	@Override
	public List<Modules> ShowByCategory(int id) {
		Category cat = em.find(Category.class, id);
		TypedQuery<Modules> query = em.createQuery("SELECT m FROM Modules m where m.category = :cat", Modules.class)
				.setParameter("cat", cat);
		List<Modules> results = query.getResultList();
		return results;
	}

}
