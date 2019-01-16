package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Qcm;

@Remote
public interface QuestionService {
	
	public Qcm addQuestionToModule(int idModule , Qcm Question);
	public List<Qcm> ShowAll();
	public List<Qcm> ShowByModule(int id);
	public Qcm GetQuestionById(int id);
	public Boolean DeleteQuestion(int id );
	public Qcm UpdateQuestion(Qcm Question);
	public Qcm getQuestionById(int id);

}
