package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CanTestPk implements Serializable{
	private int Id_test ;
	private int id_Candidate;
	public int getId_test() {
		return Id_test;
	}
	public void setId_test(int id_test) {
		Id_test = id_test;
	}
	public int getId_Candidate() {
		return id_Candidate;
	}
	public void setId_Candidate(int id_Candidate) {
		this.id_Candidate = id_Candidate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id_test;
		result = prime * result + id_Candidate;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CanTestPk other = (CanTestPk) obj;
		if (Id_test != other.Id_test)
			return false;
		if (id_Candidate != other.id_Candidate)
			return false;
		return true;
	}

}
