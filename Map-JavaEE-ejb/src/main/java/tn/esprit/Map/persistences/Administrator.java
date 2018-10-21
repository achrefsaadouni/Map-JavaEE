package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "administrator")
public class Administrator extends Person implements Serializable {
	@OneToMany(mappedBy = "administrator")
	private List<Request> requests;

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}
