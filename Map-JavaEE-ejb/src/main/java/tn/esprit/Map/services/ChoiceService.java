package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.ChoiceServices;
import tn.esprit.Map.persistences.Choices;
import tn.esprit.Map.persistences.Qcm;

@Stateless
public class ChoiceService implements ChoiceServices {
	
	@PersistenceContext(unitName = "MAP")
	private EntityManager em;
	
	@Override
	public Choices addChoiceToQuestion(int idQuestion, Choices choice) {
		
		Qcm question = em.find(Qcm.class, idQuestion);
		choice.setQcm(question);
		em.persist(choice);
		return choice;
		
	}

	@Override
	public List<Choices> ShowChoicesByQuestion(int idQuestion) {
		Qcm Choice = em.find(Qcm.class, idQuestion);
		TypedQuery<Choices> query = em.createQuery("SELECT c FROM Choices c where c.qcm= :Choice", Choices.class).setParameter("Choice", Choice);
		List<Choices> results = query.getResultList();
		return results;
	}

	@Override
	public Choices updateChoice(Choices choice) {
		int query = em.createQuery("update Choices c set c.Title= :title ,c.correct= :correct where c.id = :id").
				setParameter("title", choice.getTitle()).setParameter("correct", choice.isCorrect()).setParameter("id", choice.getId())
				.executeUpdate();
		
		return choice;
	}

	@Override
	public Boolean DeleteChoice(int id) {
		Choices choice = em.find(Choices.class, id);
		if (choice != null)
		{
			em.remove(choice);
			return true;
		}
		return false;
	}

}
