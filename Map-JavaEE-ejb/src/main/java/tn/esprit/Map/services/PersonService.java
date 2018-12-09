package tn.esprit.Map.services;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.Map.interfaces.PersonServiceRemote;
import tn.esprit.Map.persistences.Message;
import tn.esprit.Map.persistences.Person;



@Stateful
public class PersonService implements PersonServiceRemote{

	@PersistenceContext(unitName="MAP")
	EntityManager em;
	

	@Override
	public String test(Message msg) {
		em.persist(msg);
		return msg.getContent();
	}


	@Override
	public Person getPersonById(int id) {
		return em.find(Person.class, id);
	}


	@Override
	public boolean login(String login, String pwd) {
		Query query=em.createQuery("SELECT a FROM Person a WHERE a.login = :login AND a.password = :pwd").setParameter("login", login).setParameter("pwd", pwd);
		if(query.getResultList().size() == 0)
			return false;
		return true;
	}


	@Override
	public Person findPersonByUsername(String username) {
		Query query=em.createQuery("select a from Person a where a.login = :u").setParameter("u", username);
		List<Person>person=query.getResultList();
		if(person.size()>0)
			return person.get(0);
			return null;
	}



	

	

	

	

	

	



}
