package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Project projet;
	private List<Resource> resources;
	public Project getProjet() {
		return projet;
	}
	public void setProjet(Project projet) {
		this.projet = projet;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public MapContent() {
		this.resources = new ArrayList<>();
	}
	
	
}
