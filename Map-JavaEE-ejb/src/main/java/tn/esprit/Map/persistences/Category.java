package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category  implements Serializable{
	
	@Id
	@GeneratedValue
	private int id ;
	private String Name ;
	
	@OneToMany (mappedBy="category" , fetch = FetchType.EAGER)
	private Set<Modules> modules;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Set<Modules> getModules() {
		return modules;
	}

	public void setModules(Set<Modules> modules) {
		this.modules = modules;
	}


	

}
