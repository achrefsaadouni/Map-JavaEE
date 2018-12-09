package tn.esprit.Map.persistences;

import java.util.List;

public class Suggestion {
	private Request request;
	private List<Resource> resources;
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
}
