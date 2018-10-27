package tn.esprit.Map.persistences;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Embeddable;

public class MandatePK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateDebut;
	private Date dateFin;
	private int projetId;
	private int ressourceId;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateDebut == null) ? 0 : dateDebut.hashCode());
		result = prime * result + ((dateFin == null) ? 0 : dateFin.hashCode());
		result = prime * result + projetId;
		result = prime * result + ressourceId;
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
		MandatePK other = (MandatePK) obj;
		if (dateDebut == null) {
			if (other.dateDebut != null)
				return false;
		} else if (!dateDebut.equals(other.dateDebut))
			return false;
		if (dateFin == null) {
			if (other.dateFin != null)
				return false;
		} else if (!dateFin.equals(other.dateFin))
			return false;
		if (projetId != other.projetId)
			return false;
		if (ressourceId != other.ressourceId)
			return false;
		return true;
	}
	
}
