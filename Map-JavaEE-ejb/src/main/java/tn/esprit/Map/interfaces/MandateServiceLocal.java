package tn.esprit.Map.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.AvailabilityType;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@Local
public interface MandateServiceLocal {
	public boolean addMandate(int requestId,int resourceId);
	public Resource SearchResourceBySkill(Skill skill);
	public boolean isAvailable(int resourceId,Date date);
	public boolean notif(int resourceId,int requestId,String link);
	public boolean notifEndProject(Mandate mandate);
	public List<Mandate> getAll();
	public List<Mandate> getByResource(int resourceId);
	public List<Mandate> getByProject(int projectId);
	public List<Mandate> getByStartDate(Date startDate);
	public List<Mandate> getByEndDate(Date endDate);
	public List<Mandate> getByPeriod(Date startDate,Date endDate);
	public Mandate getMandate(int ressourceId,int projetId,Date dateDebut,Date dateFin);
	public boolean archive();
	public Double calculateCost(int ressourceId, int projetId, Date startDate, Date endDate, int gpsId);
	public boolean addGps(int ressourceId,int projetId,Date dateDebut,Date dateFin, int gpsId);
	public void UpdateAvailability(int resourceId,AvailabilityType availabilityType)
	public void AlertEndMandate(Timer timer);
	public void Suggestion(List<String> listeResources,int clientId);

}
