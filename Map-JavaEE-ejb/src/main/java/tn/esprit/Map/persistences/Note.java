package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Entity
@JsonRootName("Note")
public class Note implements Serializable {
	
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@JsonProperty("noteId")
	private int noteId;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_Client" , referencedColumnName="id")
	@JsonProperty("client")
	
	private Client client;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_Resource" , referencedColumnName="id")
	@JsonProperty("resource")
	
	private Resource resource;
	
	@JsonProperty("noteResource")
	private float noteResource;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public float getNoteResource() {
		return noteResource;
	}

	public void setNoteResource(float noteResource) {
		this.noteResource = noteResource;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	
	

	
	
	
	
	
	
}
