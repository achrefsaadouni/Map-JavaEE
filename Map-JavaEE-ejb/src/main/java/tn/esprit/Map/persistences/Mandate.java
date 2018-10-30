package tn.esprit.Map.persistences;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Mandate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private MandateId mandateId;
	@ManyToOne
	@JoinColumn(name = "projetId", referencedColumnName = "id", insertable = false, updatable = false)
	private Project projet;
	@ManyToOne
	@JoinColumn(name = "ressourceId", referencedColumnName = "id", insertable = false, updatable = false)
	private Resource ressource;
	private Double montant;
	@OneToOne
	private Resource gps;
	private boolean archived;

	public boolean getArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	

	public MandateId getMandateId() {
		return mandateId;
	}

	public void setMandateId(MandateId mandateId) {
		this.mandateId = mandateId;
	}

	public Resource getGps() {
		return gps;
	}

	public void setGps(Resource gps) {
		this.gps = gps;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Project getProjet() {
		return projet;
	}

	public void setProjet(Project projet) {
		this.projet = projet;
	}

	public Resource getRessource() {
		return ressource;
	}

	public void setRessource(Resource ressource) {
		this.ressource = ressource;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
