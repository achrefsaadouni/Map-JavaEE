package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@IdClass(MandatePK.class)
public class Mandate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Id
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	@Id
	private int projetId;
	@Id
	private int ressourceId;
	@ManyToOne
	@JoinColumn(name = "projetId", referencedColumnName = "id", insertable = false, updatable = false)
	private Project projet;
	@ManyToOne
	@JoinColumn(name = "ressourceId", referencedColumnName = "id", insertable = false, updatable = false)
	private Resource ressource;
	private float montant;
	@OneToOne
	private Resource gps;

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public int getProjetId() {
		return projetId;
	}

	public void setProjetId(int projetId) {
		this.projetId = projetId;
	}

	public int getRessourceId() {
		return ressourceId;
	}

	public void setRessourceId(int ressourceId) {
		this.ressourceId = ressourceId;
	}

	public Resource getGps() {
		return gps;
	}

	public void setGps(Resource gps) {
		this.gps = gps;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
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
