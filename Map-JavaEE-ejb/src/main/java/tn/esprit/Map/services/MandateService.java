package tn.esprit.Map.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.ejb.Timer;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import tn.esprit.Map.interfaces.MandateServiceLocal;
import tn.esprit.Map.interfaces.SkillRemote;
import tn.esprit.Map.persistences.AvailabilityType;
import tn.esprit.Map.persistences.DayOff;
import tn.esprit.Map.persistences.Mandate;
import tn.esprit.Map.persistences.MandateId;
import tn.esprit.Map.persistences.MapContent;
import tn.esprit.Map.persistences.Project;
import tn.esprit.Map.persistences.Request;
import tn.esprit.Map.persistences.Resource;
import tn.esprit.Map.persistences.ResourceSkill;
import tn.esprit.Map.persistences.SeniorityType;
import tn.esprit.Map.persistences.Skill;
import tn.esprit.Map.persistences.Suggestion;

@Stateless
public class MandateService implements MandateServiceLocal {
	@PersistenceContext(unitName = "MAP")
	EntityManager em;

	@EJB
	MailService mail;

	@EJB
	SkillRemote skillremote;

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

		if (resource.getAvailability() == AvailabilityType.available) {
			System.out.println("available");
			return true;
		} else if (resource.getAvailability() == AvailabilityType.availableSoon) {

			TypedQuery<Request> query3 = em.createQuery("SELECT r FROM Request r where r.suggessedResource.id=:rId",
					Request.class);
			query3.setParameter("rId", resourceId);
			try {
				if (!query3.getResultList().isEmpty()) {
					return false;
				}

			} catch (Exception e) {
				return false;
			}

			TypedQuery<Mandate> query = em.createQuery(
					"SELECT m FROM Mandate m where m.mandateId.ressourceId=:rId  AND m.archived = false ORDER BY m.mandateId.dateFin",
					Mandate.class);
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
				if (mandate.getMandateId().getDateFin().compareTo(date) >= 0) {
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
			mail.send(resource.getEmail(), "New Mandate", "You were Appointed to a new request",
					"following the acceptance of your profile by our client", request.getClient().getNameSociety(),
					"you are assigned to a new project", "Project Name : " + request.getProject().getProjectName(), "",
					"Address : " + request.getProject().getAddress() + " <br>Start Date : "
							+ request.getStartDateMondate() + " <br>End Date : " + request.getEndDateMondate(),
					link);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	@Override
	public List<Mandate> getAll() {
		List<Mandate> results;
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.archived = false", Mandate.class);
		try {
			results = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			results = new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<Mandate> getByResource(int resourceId) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.mandateId.ressourceId=:rId AND m.archived = false", Mandate.class);
		query.setParameter("rId", resourceId);
		List<Mandate> results;
		try {
			results = query.getResultList();
			return results;
		} catch (Exception e) {
			return new ArrayList<Mandate>();
		}

	}

	@Override
	public List<Mandate> getByProject(int projectId) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.mandateId.projetId = :pId AND m.archived = false", Mandate.class);
		query.setParameter("pId", projectId);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByStartDate(Date startDate) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.mandateId.dateDebut = :startDate AND m.archived = false",
				Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByEndDate(Date endDate) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.mandateId.dateFin = :endDate AND m.archived = false", Mandate.class);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		List<Mandate> results = query.getResultList();
		return results;
	}

	@Override
	public List<Mandate> getByPeriod(Date startDate, Date endDate) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.mandateId.dateFin BETWEEN :endDate AND :startDate AND m.archived = false",
				Mandate.class);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		List<Mandate> results = new ArrayList<>();
		results = query.getResultList();
		return results;
	}

	@Override
	public Double calculateCost(int ressourceId, int projetId, Date startDate, Date endDate) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.mandateId.dateDebut = :startDate AND m.mandateId.dateFin = :endDate AND m.mandateId.projetId = :pId AND  m.mandateId.ressourceId=:rId AND m.archived = false",
				Mandate.class);
		query.setParameter("startDate", startDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("pId", projetId);
		query.setParameter("rId", ressourceId);
		try {
			if (query.getSingleResult().getMontant() == 0.0)

			{
				final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
				long delta = endDate.getTime() - startDate.getTime();

				Double montant = query.getSingleResult().getRessource().getSalary()
						* query.getSingleResult().getRessource().getTaux() * (delta / (MILLISECONDS_PER_DAY));
				System.out.println(montant);
				query.getSingleResult().setMontant(montant);
			}
			return query.getSingleResult().getMontant();
		} catch (Exception e) {
			return 0.0;
		}
	}

	@Override
	public boolean addGps(int ressourceId, int projetId, Date startDate, Date endDate, int gpsId) {
		Mandate results;
		TypedQuery<Mandate> query = em
				.createQuery("SELECT m FROM Mandate m where m.mandateId = :m AND m.archived = false", Mandate.class);
		MandateId mandateId = new MandateId();
		mandateId.setDateDebut(startDate);
		mandateId.setDateFin(endDate);
		mandateId.setProjetId(projetId);
		mandateId.setRessourceId(ressourceId);
		query.setParameter("m", mandateId);
		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", gpsId);
		Resource resourceGPS;
		try {
			resourceGPS = query1.getSingleResult();
			resourceGPS.setAvailability(AvailabilityType.unavailable);
			results = query.getSingleResult();
			results.setGps(resourceGPS);

		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean addMandate(int requestId, int resourceId) {
		notif(resourceId, requestId, "http://localhost:8993/Mandate/resourceMandate");
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
		MandateId mandateId = new MandateId();

		mandateId.setDateDebut(request.getStartDateMondate());
		mandateId.setDateFin(request.getEndDateMondate());
		mandateId.setProjetId(request.getProject().getId());
		mandateId.setRessourceId(resource.getId());
		mandate.setMontant(0.0);
		mandate.setMandateId(mandateId);
		mandate.setArchived(false);
		try {
			em.persist(mandate);
			UpdateAvailability(mandate.getMandateId().getRessourceId(), AvailabilityType.unavailable);

			em.remove(request);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Schedule(second = "00", minute = "00", hour = "00")
	public void AlertEndMandate(Timer timer) {
		System.out.println(getDate());
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT  m FROM Mandate m where m.mandateId.dateFin = :d AND m.archived = false", Mandate.class);

		try {
			query.setParameter("d", simpleDateFormat.parse(getDate()), TemporalType.DATE);
			List<Mandate> mandates = query.getResultList();
			if (!mandates.isEmpty()) {
				mandates.forEach(e -> {
					notifEndProject(e);
					UpdateAvailability(e.getMandateId().getRessourceId(), AvailabilityType.availableSoon);
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

	public String convertDate(Date date) {
		String DATE_FORMAT = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}

	@Override
	public boolean notifEndProject(Mandate mandate) {
		mail.send(mandate.getRessource().getEmail(), "End Mandate", "Alert End Of Mandate",
				"Levio Administration want to Inform you that the project", mandate.getProjet().getProjectName(),
				"will end in just 40 days",
				"Mr/Ms " + mandate.getRessource().getLastName() + " " + mandate.getRessource().getFirstName(), "",
				"We want to thank you for your hard work and always remember Levio is  proud of your work <br> you can check further information in the link",
				"http://localhost:18080/Map-JavaEE-web/MAP/mandate?dateFin=" + mandate.getMandateId().getDateFin()
						+ "&dateDebut=" + mandate.getMandateId().getDateDebut() + "&ressourceId="
						+ mandate.getMandateId().getRessourceId() + "&projetId="
						+ mandate.getMandateId().getProjetId());
		mail.send(mandate.getProjet().getClient().getEmail(), "End Mandate", "Alert End Of Mandate",
				"Levio Administration want to Inform you that the project", mandate.getProjet().getProjectName(),
				"will end in just 40 days",
				"Mr/Ms " + mandate.getProjet().getClient().getLastName() + " "
						+ mandate.getProjet().getClient().getFirstName(),
				"",
				"We want to thank you for your hard work and always remember Levio is  proud of your work <br> you can check further information in the link",
				"http://localhost:18080/Map-JavaEE-web/MAP/mandate?dateFin=" + mandate.getMandateId().getDateFin()
						+ "&dateDebut=" + mandate.getMandateId().getDateDebut() + "&ressourceId="
						+ mandate.getMandateId().getRessourceId() + "&projetId="
						+ mandate.getMandateId().getProjetId());
		return true;
	}

	@Override
	public Mandate getMandate(int ressourceId, int projetId, Date startDate, Date endDate) {
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.mandateId.dateDebut = :startDate AND m.mandateId.dateFin = :endDate AND m.mandateId.projetId = :pId AND  m.mandateId.ressourceId=:rId AND m.archived = false",
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
	public Suggestion SearchResourceBySkill(int requestId) {
		Suggestion sug = new Suggestion();
		List<Skill> listSkillsRequired = new ArrayList<>();
		List<Resource> listeRecourceNeeded = new ArrayList<>();
		TypedQuery<Request> query = em.createQuery("SELECT m FROM Request m where m.id=:rId", Request.class);
		query.setParameter("rId", requestId);
		TypedQuery<Resource> query1 = em.createQuery("SELECT m FROM Resource m where m.archived = 0", Resource.class);
		try {
			sug.setRequest(query.getSingleResult());
			List<Resource> resources = query1.getResultList();
			listSkillsRequired.addAll(skillremote.orderSkillsOfProjecte(sug.getRequest().getProject().getId()));
			resources.forEach(e -> {
				if (isAvailable(e.getId(), sug.getRequest().getStartDateMondate())
						&& e.getWorkProfil() == sug.getRequest().getRequestedProfil()) {
					System.out.println("here");
					if ((sug.getRequest().getExperienceYear() >= 3 && e.getSeniority() == SeniorityType.Senior)
							|| (sug.getRequest().getExperienceYear() < 3 && e.getSeniority() == SeniorityType.Junior)) {
						List<Skill> resourceskills = skillremote.orderSkillsOfResource(e.getId());
						if (resourceskills.containsAll(listSkillsRequired)) {
							listeRecourceNeeded.add(e);
						}

					}
				}
			});
			Collections.sort(listeRecourceNeeded, new Comparator<Resource>() {
				@Override
				public int compare(Resource r1, Resource r2) {

					if (ScoreSkill(r1, listSkillsRequired) > ScoreSkill(r2, listSkillsRequired))
						return -1;
					else if (ScoreSkill(r2, listSkillsRequired) == ScoreSkill(r2, listSkillsRequired))
						return 0;
					else
						return 1;

				}
			});
			sug.setResources(listeRecourceNeeded);
			return sug;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	@Override
	@Schedule(second = "00", minute = "37", hour = "20")
	public void archive(Timer timer) {
		List<Mandate> results;
		TypedQuery<Mandate> query = em.createQuery(
				"SELECT m FROM Mandate m where m.archived = false AND CURRENT_DATE = m.mandateId.dateFin",
				Mandate.class);
		try {
			results = query.getResultList();
			results.forEach(e -> {
				e.setArchived(true);
			});
		} catch (Exception e) {
		}
	}

	@Override
	public List<Mandate> getAllTypeMandate() {

		List<Mandate> results;
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.archived = true", Mandate.class);
		try {
			results = query.getResultList();
		} catch (Exception e) {
			results = new ArrayList<>();
		}
		return results;
	}

	@Override
	public boolean restore(int ressourceId, int projetId, Date startDate, Date endDate) {
		Mandate results;
		TypedQuery<Mandate> query = em
				.createQuery("SELECT m FROM Mandate m where m.mandateId = :m AND m.archived = false", Mandate.class);
		MandateId mandateId = new MandateId();
		mandateId.setDateDebut(startDate);
		mandateId.setDateFin(endDate);
		mandateId.setProjetId(projetId);
		mandateId.setRessourceId(ressourceId);
		query.setParameter("m", mandateId);
		TypedQuery<Resource> query1 = em.createQuery("SELECT r FROM Resource r where r.id=:rId", Resource.class);
		query1.setParameter("rId", ressourceId);
		Resource resourceGPS;
		try {
			resourceGPS = query1.getSingleResult();
			resourceGPS.setAvailability(AvailabilityType.available);
			results = query.getSingleResult();
			results.setGps(null);

		} catch (Exception e) {
			return false;
		}
		return true;

	}

	@Override
	public double ScoreSkill(Resource resource, List<Skill> skills) {
		double score = 0.0;
		for (ResourceSkill e : resource.getResourceSkills()) {
			if (skills.contains(e.getSkill()))
				score += e.getRateSkill();
		}
		return score;
	}

	@Override
	public double CostProject(int projectId) {
		double totalcost = 0.0;
		for (Mandate e : getByProject(projectId)) {
			totalcost = totalcost + calculateCost(e.getMandateId().getRessourceId(), e.getMandateId().getProjetId(),
					e.getMandateId().getDateDebut(), e.getMandateId().getDateFin());

		}

		return totalcost;
	}

	@Override
	public boolean notifSummon(String email, String date, int requestId, String link) {
		TypedQuery<Request> query = em.createQuery("SELECT r FROM Request r where r.id=:rId", Request.class);
		query.setParameter("rId", requestId);
		Request request;
		try {
			request = query.getSingleResult();
			mail.send(email, "New Summon", "You were Summoned by a  new Client",
					"following the acceptance of your profile by our client", request.getClient().getNameSociety(),
					"you may be  assigned to a new project", "Project Name : " + request.getProject().getProjectName(),
					"",
					"You need to be present at this Address : " + request.getProject().getAddress()
							+ " <br> by this day : " + date
							+ " To pass their test for more information you can contact our client at "
							+ request.getClient().getEmail(),
					link);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	@Override
	public List<Resource> getGps() {

		List<Resource> results;
		TypedQuery<Resource> query = em.createQuery("SELECT m FROM Resource m ", Resource.class);
		try {
			results = query.getResultList().stream().filter( e -> e.getAvailability().equals(AvailabilityType.available)).collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e);
			results = new ArrayList<>();
		}
		return results;
	}

	@Override
	public List<Mandate> getByClient(int clientId) {
		List<Mandate> results;
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.archived = false", Mandate.class);
		try {
			results = query.getResultList().stream().filter( e -> e.getProjet().getClient().getId()== clientId).collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println(e);
			results = new ArrayList<>();
		}
		return results;
	}

	@Override
	public void cancelRequest(int id) {
		em.find(Resource.class, em.find(Request.class, id).getSuggessedResource().getId()).setAvailability(AvailabilityType.available);
		em.find(Request.class, id).setSuggessedResource(null);
	}

	@Override
	public void traiter(int requestId) {
		
		em.find(Request.class, requestId).setTraiter(true);
		
	}

	@Override
	public List<Project> getprojects() {
		List<Project> results;
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m where m.archived = false", Mandate.class);
		try {
			results =query.getResultList().stream().map( e -> e.getProjet()).filter(distinctByKey(Project::getId)).collect(Collectors.toList());
			
		} catch (Exception e) {
			System.out.println(e);
			results = new ArrayList<Project>();
		}
		System.out.println("sqd"+results);
		return results;
		
	}
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	    Set<Object> seen = ConcurrentHashMap.newKeySet();
	    return t -> seen.add(keyExtractor.apply(t));
	}

	@Override
	public List<MapContent> getContent() {
		List<Mandate> results;
		List<MapContent> liste = new ArrayList<>();;
		int i =0;
		TypedQuery<Mandate> query = em.createQuery("SELECT m FROM Mandate m WHERE m.archived = false order by m.mandateId.projetId", Mandate.class);
		try {
			results = query.getResultList();
			 for(Mandate item : results)
	            {
	      
	                if (liste.size() == 0)
	                {
	                	MapContent k = new MapContent();
	                    k.setProjet(item.getProjet());
	                    k.getResources().add(item.getRessource());
	                    liste.add(k);
	                    i++;
	                }
	                else
	                {
	                    if (liste.get(i-1).getProjet().getId() == item.getMandateId().getProjetId())
	                    {
	                        liste.get(i-1).getResources().add(item.getRessource());
	                    }
	                    else
	                    {
	                    	MapContent k = new MapContent();
	                    	k.setProjet(item.getProjet());
		                    k.getResources().add(item.getRessource());
		                    liste.add(k);
		                    i++;
	                    }
	                }
	            }
			
			
			
			
		} catch (Exception e) {
			System.out.println(e);
			
		}
		return liste;
	}

	@Override
	public String addSuggestion(int resourceId, int requestId) {
		em.find(Request.class, requestId).setSuggessedResource(em.find(Resource.class, resourceId));
		em.find(Resource.class, resourceId).setAvailability(AvailabilityType.availableSoon);
		return "sucess";
	}

}
