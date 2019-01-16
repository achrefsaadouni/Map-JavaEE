package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Choices;

@Remote
public interface ChoiceServices {
	
	public Choices addChoiceToQuestion( int idQuestion , Choices choice);
	public List<Choices> ShowChoicesByQuestion( int idQuestion);
	public Choices updateChoice(Choices choice);
	public Boolean DeleteChoice(int id);

}
