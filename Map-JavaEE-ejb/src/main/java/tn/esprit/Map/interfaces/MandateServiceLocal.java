package tn.esprit.Map.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.Request;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@Local
public interface MandateServiceLocal {
	public boolean addMandate(Request request);
	public Resource SearchResourceBySkill(Skill skill);
	public boolean isAvailable(int resourceId);
	public void notify(String receiver ,String topic, String textMessage);
	public List<Mandate> getAll();
	public List<Mandate> getByResource(int resourceId);
	public List<Mandate> getByProject(int projectId);
	public List<Mandate> getByStartDate(Date startDate);
	public List<Mandate> getByEndDate(Date endDate);
	public List<Mandate> getByPeriod(Date startDate,Date endDate);
	public boolean archive();
	public float calculateCost(Mandate mandate);
	public void AlertEndMandate(Mandate mandate);
	public boolean addGps(int ressourceId,int projetId,Date dateDebut,Date dateFin, int gpsId);
}
