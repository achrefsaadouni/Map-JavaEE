package tn.esprit.Map.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.Map.interfaces.MandateServiceLocal;
import tn.esprit.Map.persistences.AvailabilityType;
import tn.esprit.Map.persistences.Client;
import tn.esprit.Map.persistences.DayOff;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.Request;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.Skill;

@Stateless
public class MandateService implements MandateServiceLocal {
	@PersistenceContext(unitName = "MAP")
	EntityManager em;
	@EJB
	MailService mail;


	@Override
	public boolean isAvailable(int resourceId, Date date) {
		List<DayOff> dayOffs = new ArrayList<DayOff>();
		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", resourceId);
		Resource resource;
		try {
			resource = query1.getSingleResult();

		} catch (Exception e) {
			return false;
		}

		if (resource.getAvailability() == AvailabilityType.available)
			return true;
		else if (resource.getAvailability() == AvailabilityType.availableSoon) {

			TypedQuery<Mandate> query = em
					.createQuery("SELECT m FROM Mandate m where m.ressourceId=:rId ORDER BY m.dateFin", Mandate.class);
			query.setParameter("rId", resourceId);
			try {
				Mandate mandate = query.getSingleResult();
				dayOffs.addAll(resource.getDayOffs());
				Collections.sort(dayOffs, new Comparator<DayOff>() {
					@Override
					public int compare(DayOff d1, DayOff d2) {

						return d2.getEndDate().compareTo(d1.getEndDate());
					}
				});
				if (mandate.getDateFin().compareTo(date) >= 0) {
					return false;
				} else {
					if (dayOffs.isEmpty())
						return true;
					else if (dayOffs.get(0).getEndDate().compareTo(date) >= 0)
						return false;
					else
						return true;
				}
			} catch (Exception e) {
				dayOffs.addAll(resource.getDayOffs());
				Collections.sort(dayOffs, new Comparator<DayOff>() {
					@Override
					public int compare(DayOff d1, DayOff d2) {

						return d2.getEndDate().compareTo(d1.getEndDate());
					}
				});
				if (dayOffs.isEmpty())
					return true;
				else if (dayOffs.get(0).getEndDate().compareTo(date) >= 0)
					return false;
				else
					return true;
			}
		}
		return false;
	}

	@Override
	public boolean notif(int resourceId, int requestId, String link) {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r where r.id=:rId", Request.class);
		query.setParameter("rId", requestId);
		Request request;
		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", resourceId);
		Resource resource;
		try {
			request = query.getSingleResult();
			resource = query1.getSingleResult();
		} catch (Exception e) {
			return false;
		}
		mail.send(resource.getEmail(), "New Mandate", "You were Appointed to a new request",
				"following the acceptance of your profile by our client", request.getClient().getNameSociety(),
				"you are assigned to a new project", "Project Name : " + request.getProject().getProjectName(), "",
				"Address : " + request.getProject().getAddress() + " <br>Start Date : " + request.getStartDateMondate()
						+ " <br>End Date : " + request.getEndDateMondate(),
				link);
		return true;
	}

	@Override
	public List<Mandate> getAll() {
		List<Mandate> results;
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m", Mandate.class);
		try {
			results = query.getResultList();
		} catch (Exception e) {
			results = new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<Mandate> getByResource(int resourceId) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.ressourceId=:rId", Mandate.class);
		query.setParameter("rId", resourceId);
		List<Mandate> results = new ArrayList<>();
		results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByProject(int projectId) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.projetId = :pId", Mandate.class);
		query.setParameter("pId", projectId);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByStartDate(Date startDate) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.dateDebut = :startDate",
				Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByEndDate(Date endDate) {
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.dateFin = :endDate", Mandate.class);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByPeriod(Date startDate, Date endDate) {
		TypedQuery<Mandate> query = em
				.createQuery("SELECT m FROM Mandate m where m.dateFin BETWEEN :endDate AND :startDate", Mandate.class);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		List<Mandate> results = new ArrayList<>();
		results = query.getResultList();
		return results;
	}

	

	@Override
	public Double calculateCost(int ressourceId, int projetId, Date startDate, Date endDate, int gpsId) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.dateDebut = :startDate AND m.dateFin = :endDate AND m.projetId = :pId AND  m.ressourceId=:rId",
				Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("pId", projetId);
		query.setParameter("rId", ressourceId);
		try {
			if (query.getSingleResult().getMontant() == 0)
				;
			{
				query.getSingleResult().setMontant(query.getSingleResult().getRessource().getSalary()
						* query.getSingleResult().getRessource().getTaux());
			}
			return query.getSingleResult().getMontant();
		} catch (Exception e) {
			return 0.0;
		}
	}

	@Override
	public boolean addGps(int ressourceId, int projetId, Date startDate, Date endDate, int gpsId) {
		Mandate results;
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.dateDebut = :startDate AND m.dateFin = :endDate AND m.projetId = :pId AND  m.ressourceId=:rId",
				Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("pId", projetId);
		query.setParameter("rId", ressourceId);
		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", gpsId);
		Resource resourceGPS;
		try {
			resourceGPS = query1.getSingleResult();
			results = query.getSingleResult();
			results.setGps(resourceGPS);

		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean addMandate(int requestId, int resourceId) {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r where r.id=:rId", Request.class);
		query.setParameter("rId", requestId);
		Request request;
		try {
			request = query.getSingleResult();
		} catch (Exception e)

		{
			return false;
		}

		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", resourceId);
		Resource resource;
		try {
			resource = query1.getSingleResult();
		} catch (Exception e) {

			return false;
		}

		Mandate mandate = new Mandate();
		mandate.setDateDebut(request.getStartDateMondate());
		mandate.setDateFin(request.getEndDateMondate());
		mandate.setProjetId(request.getProject().getId());
		mandate.setRessourceId(resource.getId());
		mandate.setMontant(0.0);
		try {
			em.persist(mandate);
			UpdateAvailability(mandate.getRessourceId(), AvailabilityType.unavailable);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Schedule(second = "00", minute = "31", hour = "21")
	public void execute(Timer timer) throws ParseException {
		System.out.println(getDate());
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		TypedQuery<Mandate> query = em.createQuery("SELECT  m FROM Mandate m where m.dateFin = :d", Mandate.class);

		try {
			query.setParameter("d", simpleDateFormat.parse(getDate()), TemporalType.DATE);
			List<Mandate> mandates = query.getResultList();
			if (!mandates.isEmpty()) {
				mandates.forEach(e -> {
					notifEndProject(e);
					UpdateAvailability(e.getRessourceId(), AvailabilityType.availableSoon);
				});

			} else
				System.out.println("liste vide");

		} catch (Exception e) {
			System.out.println("liste vide");
		}

	}

	public String getDate() {
		String DATE_FORMAT = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		// Get current date
		Date currentDate = new Date();
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		// plus one
		localDateTime = localDateTime.plusDays(40);

		// convert LocalDateTime to date
		Date currentDatePlus = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		return dateFormat.format(currentDatePlus);

	}

	@Override
	public boolean notifEndProject(Mandate mandate) {
		mail.send(mandate.getRessource().getEmail(), "End Mandate", "Alert End Of Mandate",
				"Levio Administration want to Inform you that the project", mandate.getProjet().getProjectName(),
				"will end in just 40 days",
				"Mr/Ms " + mandate.getRessource().getLastName() + " " + mandate.getRessource().getFirstName(), "",
				"We want to thank you for your hard work and always remember Levio is  proud of your work <br> you can check further information in the link",
				"http://localhost:18080/Map-JavaEE-web/MAP/mandate?dateFin=" + mandate.getDateFin() + "&dateDebut="
						+ mandate.getDateDebut() + "&ressourceId=" + mandate.getRessourceId() + "&projetId="
						+ mandate.getProjetId());
		mail.send(mandate.getProjet().getClient().getEmail(), "End Mandate", "Alert End Of Mandate",
				"Levio Administration want to Inform you that the project", mandate.getProjet().getProjectName(),
				"will end in just 40 days",
				"Mr/Ms " + mandate.getProjet().getClient().getLastName() + " "
						+ mandate.getProjet().getClient().getFirstName(),
				"",
				"We want to thank you for your hard work and always remember Levio is  proud of your work <br> you can check further information in the link",
				"http://localhost:18080/Map-JavaEE-web/MAP/mandate?dateFin=" + mandate.getDateFin() + "&dateDebut="
						+ mandate.getDateDebut() + "&ressourceId=" + mandate.getRessourceId() + "&projetId="
						+ mandate.getProjetId());
		return true;
	}

	@Override
	public Mandate getMandate(int ressourceId, int projetId, Date startDate, Date endDate) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.dateDebut = :startDate AND m.dateFin = :endDate AND m.projetId = :pId AND  m.ressourceId=:rId",
				Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("pId", projetId);
		query.setParameter("rId", ressourceId);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void UpdateAvailability(int resourceId, AvailabilityType availabilityType) {
		TypedQuery<Resource> query = em.createQuery("SELECT m FROM Resource m where m.id=:rId", Resource.class);
		query.setParameter("rId", resourceId);
		try {
			Resource resource = query.getSingleResult();
			resource.setAvailability(availabilityType);
		} catch (Exception e) {

		}
	}
	
	

	@Override
	public Resource SearchResourceBySkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public boolean archive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Suggestion(List<String> listeResource, int clientId) {

		TypedQuery<Client> query1 = em.createQuery("SELECT c FROM Client c where c.id=:rId", Client.class);
		query1.setParameter("rId", clientId);
		Client client;
		try {
			client = query1.getSingleResult();
			listeResource.forEach(e -> {
				TypedQuery<Resource> query2 = em.createQuery("SELECT c FROM Client c where c.id=:rId", Resource.class);
				query2.setParameter("rId", clientId);
				mail.sendSuggestion(query2.getSingleResult(), client.getEmail());
			});
		} catch (Exception e) {

		}

	}

}
