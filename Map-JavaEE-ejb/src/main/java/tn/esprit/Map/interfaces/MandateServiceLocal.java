package tn.esprit.Map.interfaces;

import java.util.Date;
import java.util.List;
import javax.ejb.Timer;

import javax.ejb.Local;

import tn.esprit.Map.persistences.AvailabilityType;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.MapContent;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;
import tn.esprit.Map.persistences.Suggestion;

@Local
public interface MandateServiceLocal {
	public boolean addMandate(int requestId,int resourceId);
	public Suggestion SearchResourceBySkill(int requestId);
	public boolean isAvailable(int resourceId,Date date);
	public boolean notif(int resourceId,int requestId,String link);
	public boolean notifSummon(String email , String date , int requestId,String link);
	public boolean notifEndProject(Mandate mandate);
	public List<Mandate> getAll();
	public List<Mandate> getAllTypeMandate();
	public List<Mandate> getByResource(int resourceId);
	public List<Mandate> getByProject(int projectId);
	public List<Mandate> getByStartDate(Date startDate);
	public List<Mandate> getByEndDate(Date endDate);
	public List<Mandate> getByPeriod(Date startDate,Date endDate);
	public List<Mandate> getByClient(int clientId);
	public Mandate getMandate(int ressourceId,int projetId,Date dateDebut,Date dateFin);
	public void archive(Timer timer);
	public boolean restore(int ressourceId, int projetId, Date startDate, Date endDate);
	public Double calculateCost(int ressourceId, int projetId, Date startDate, Date endDate);
	public boolean addGps(int ressourceId,int projetId,Date dateDebut,Date dateFin, int gpsId);
	public void UpdateAvailability(int resourceId,AvailabilityType availabilityType);
	public void AlertEndMandate(Timer timer);
	public double ScoreSkill(Resource resource,List<Skill>skills);
	public double CostProject(int projectId);
	public List<Resource> getGps();
	public void cancelRequest(int id);
	public void traiter(int requestId);
	public List<Project> getprojects();
	public List<MapContent> getContent();
	public String addSuggestion(int resourceId,int requestId);
	
}
