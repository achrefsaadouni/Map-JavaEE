package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TestScore implements Serializable{
	
	@EmbeddedId
	private CanTestPk idTestScore;
	private int score = 0;
	@ManyToOne
	@JoinColumn(name="Id_test" , referencedColumnName="id",insertable=false,updatable=false)
	private Test test ;
	@ManyToOne
	@JoinColumn(name="id_Candidate" , referencedColumnName="id",insertable=false,updatable=false)
	private Candidate candidate ;
	
	
	
	public CanTestPk getIdTestScore() {
		return idTestScore;
	}
	public void setIdTestScore(CanTestPk idTestScore) {
		this.idTestScore = idTestScore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	} 
	

}
