package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.QuestionService;
import tn.esprit.Map.persistences.Category;
import tn.esprit.Map.persistences.Modules;
import tn.esprit.Map.persistences.Qcm;

@Stateless
public class QuestionsService  implements QuestionService {
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;

	@Override
	public Qcm addQuestionToModule(int idModule, Qcm Question) {
		
			Modules modules = em.find(Modules.class, idModule);
			Question.setModules(modules);
			em.persist(Question);
			return Question;
		
	}

	@Override
	public Qcm GetQuestionById(int id) {
		Qcm qcm = em.find(Qcm.class, id);
		return qcm;
	}

	@Override
	public Boolean DeleteQuestion(int id) {
		Qcm qcm = em.find(Qcm.class, id);
		if (qcm != null) {
			em.remove(qcm);
			return true;
		}
		return false;
	}

	@Override
	public Qcm UpdateQuestion(Qcm Question) {
		int query = em.createQuery("update Qcm m set m.title= :title ,m.Description= :desc where m.id = :id").
				setParameter("title", Question.getTitle()).setParameter("desc", Question.getDescription()).setParameter("id", Question.getId())
				.executeUpdate();
		
		return Question;
	}

	@Override
	public List<Qcm> ShowAll() {
		TypedQuery<Qcm> query = em.createQuery("SELECT q FROM Qcm q", Qcm.class);
		List<Qcm> results = query.getResultList();
		return results;
	}

	@Override
	public List<Qcm> ShowByModule(int id) {
		Modules modules = em.find(Modules.class, id);
		TypedQuery<Qcm> query = em.createQuery("SELECT q FROM Qcm q where q.modules= :modules", Qcm.class).setParameter("modules", modules);
		List<Qcm> results = query.getResultList();
		return results;
		
	}

	@Override
	public Qcm getQuestionById(int id) {
		Qcm question = em.find(Qcm.class, id);
	
		return question;
	}
	
	

}
