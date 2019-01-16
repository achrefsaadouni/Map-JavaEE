package tn.esprit.ressources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Map.interfaces.MandateServiceLocal;
import tn.esprit.Map.interfaces.RequestServiceRemote;
import tn.esprit.Map.persistences.Person;
import tn.esprit.Map.persistences.Role;
import tn.esprit.utlities.AuthenticatedUser;
import tn.esprit.utlities.Secured;

@Path("mandate")
@ManagedBean
public class MandateResource {

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@EJB
	MandateServiceLocal mandateService;

	@EJB
	RequestServiceRemote requestService;

	@Inject
	@AuthenticatedUser
	Person authenticatedUser;

	@POST
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMandate(Map<String, String> inputs) {

		String requestId = inputs.get("requestId");
		String resourceId = inputs.get("resourceId");
		Response reponse;
		if (requestId == null || resourceId == null || !requestId.chars().allMatch(Character::isDigit)
				|| !resourceId.chars().allMatch(Character::isDigit)) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		reponse = mandateService.addMandate(Integer.parseInt(requestId), Integer.parseInt(resourceId))
				? Response.status(Status.CREATED).build() : Response.status(Status.NOT_ACCEPTABLE).build();
		return reponse;

	}
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("client")
	public Response getClientMandate(@QueryParam(value = "id") String id) {
		int clientId = 0; 
		if (authenticatedUser.getRoleT() == Role.Client) {
			try {
				clientId = Integer.parseInt(id);
			if (mandateService.getByClient(clientId).size() == 0)
				return Response.status(Status.NO_CONTENT).build();
			else
				return Response.ok(mandateService.getByClient(clientId), MediaType.APPLICATION_JSON).build();
			}catch (Exception e) {
					return Response.status(Status.BAD_REQUEST).build();}}
		
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
		}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMandate(@QueryParam(value = "ressourceId") String ressourceId,
			@QueryParam(value = "projetId") String projetId, @QueryParam(value = "dateDebut") String dateDebut,
			@QueryParam(value = "dateFin") String dateFin, @QueryParam(value = "archive") String archive)
			throws ParseException {
		if ((ressourceId == null) && (projetId == null) && (dateDebut == null) && (dateFin == null)
				&& (archive == null)) {
			if (mandateService.getAll().size() == 0)
				return Response.status(Status.NO_CONTENT).build();
			else
				return Response.ok(mandateService.getAll(), MediaType.APPLICATION_JSON).build();
		} else if ((ressourceId == null) && (projetId == null) && (dateDebut == null) && (dateFin == null)
				&& (archive.equals("show"))) {
			if (mandateService.getAllTypeMandate().size() == 0)
				return Response.status(Status.NO_CONTENT).build();
			else
				return Response.ok(mandateService.getAllTypeMandate(), MediaType.APPLICATION_JSON).build();
		} else if ((ressourceId != null) && (projetId != null) && (dateDebut != null) && (dateFin != null)) {

			if (!ressourceId.chars().allMatch(Character::isDigit))
				return Response.status(Status.BAD_REQUEST).build();

			if (mandateService.getByResource(Integer.parseInt(ressourceId)).size() == 0)
				return Response.status(Status.NO_CONTENT).build();

			else {
				return Response.ok(
						mandateService.getMandate(Integer.parseInt(ressourceId), Integer.parseInt(projetId),
								simpleDateFormat.parse(dateDebut), simpleDateFormat.parse(dateFin)),
						MediaType.APPLICATION_JSON).build();
			}

		}

		else if ((ressourceId != null) && (projetId == null) && (dateDebut == null) && (dateFin == null)) {

			if (!ressourceId.chars().allMatch(Character::isDigit))
				return Response.status(Status.BAD_REQUEST).build();

			if (mandateService.getByResource(Integer.parseInt(ressourceId)).size() == 0)
				return Response.status(Status.NO_CONTENT).build();

			else {
				return Response
						.ok(mandateService.getByResource(Integer.parseInt(ressourceId)), MediaType.APPLICATION_JSON)
						.build();
			}

		}

		else if ((ressourceId == null) && (projetId != null) && (dateDebut == null) && (dateFin == null)) {

			if (!projetId.chars().allMatch(Character::isDigit))
				return Response.status(Status.BAD_REQUEST).build();

			if (mandateService.getByProject(Integer.parseInt(projetId)).isEmpty())
				return Response.status(Status.NO_CONTENT).build();

			else {
				return Response.ok(mandateService.getByProject(Integer.parseInt(projetId)), MediaType.APPLICATION_JSON)
						.build();
			}

		}

		else if ((ressourceId == null) && (projetId == null) && (dateDebut != null) && (dateFin == null)) {
			Date uDate;
			try {
				uDate = simpleDateFormat.parse(dateDebut);
			} catch (Exception e) {
				return Response.status(Status.BAD_REQUEST).build();
			}

			if (mandateService.getByStartDate(uDate).isEmpty())
				return Response.status(Status.NO_CONTENT).build();

			else {
				return Response.ok(mandateService.getByStartDate(uDate), MediaType.APPLICATION_JSON).build();
			}

		}

		else if ((ressourceId == null) && (projetId == null) && (dateDebut == null) && (dateFin != null)) {
			Date uDate;
			try {
				uDate = simpleDateFormat.parse(dateFin);
			} catch (Exception e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			if (mandateService.getByEndDate(uDate).isEmpty())
				return Response.status(Status.NO_CONTENT).build();

			else {
				return Response.ok(mandateService.getByEndDate(uDate), MediaType.APPLICATION_JSON).build();
			}

		}

		else if ((ressourceId == null) && (projetId == null) && (dateDebut != null) && (dateFin != null)) {
			Date fDate;
			Date dDate;
			try {
				fDate = simpleDateFormat.parse(dateFin);
				dDate = simpleDateFormat.parse(dateDebut);
			} catch (Exception e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			if (fDate.compareTo(dDate) < 1)
				return Response.status(Status.BAD_REQUEST).build();
			if (mandateService.getByPeriod(dDate, fDate).size() == 0)
				return Response.status(Status.NO_CONTENT).build();
			else {
				return Response.ok(mandateService.getByPeriod(dDate, fDate), MediaType.APPLICATION_JSON).build();
			}

		}

		else
			return Response.status(Response.Status.BAD_REQUEST).entity("Requete eronnée").build();
	}
	@Path("addGps")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	public Response addGps(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Admin) {
			Response response = Response.status(Status.BAD_REQUEST).build();
			int ressourceId = 0;
			int projetId = 0;
			Date dateDebut = null;
			Date dateFin = null;
			int gpsId = 0;
			try {
				
				ressourceId = Integer.parseInt(inputs.get("resourceId"));
				projetId = Integer.parseInt(inputs.get("projectId"));
				dateDebut = simpleDateFormat.parse(inputs.get("startDate"));
				dateFin = simpleDateFormat.parse(inputs.get("endDate"));
				gpsId = Integer.parseInt(inputs.get("gpsId"));
				response = mandateService.addGps(ressourceId, projetId, dateDebut, dateFin, gpsId)
						? Response.status(Status.OK).build() : Response.status(Status.FORBIDDEN).build();
				System.out.println("add gps");
			} catch (Exception e) {
			
				return Response.status(Status.NOT_ACCEPTABLE).build();
			}
			return response;
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}
	
	
	@Path("restoreGps")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	
	public Response restpreGps(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Admin) {
			Response response = Response.status(Status.BAD_REQUEST).build();
			int ressourceId = 0;
			int projetId = 0;
			Date dateDebut = null;
			Date dateFin = null;
				try {
					ressourceId = Integer.parseInt(inputs.get("resourceId"));
					projetId = Integer.parseInt(inputs.get("projectId"));
					dateDebut = simpleDateFormat.parse(inputs.get("startDate"));
					dateFin = simpleDateFormat.parse(inputs.get("endDate"));
					System.out.println("achref"+" "+ressourceId+" "+projetId+" "+dateDebut+" "+dateFin);
					response = mandateService.restore(ressourceId, projetId, dateDebut, dateFin)
							? Response.status(Status.OK).build() : Response.status(Status.BAD_REQUEST).build();
				} catch (Exception e2) {
					System.out.println("restore here");
					return Response.status(Status.NOT_ACCEPTABLE).build();
			}
			return response;
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}
	@Secured
	@Path("notify")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response notify(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Client) {
			int resourceId = Integer.parseInt(inputs.get("resourceId"));
			int requestId = Integer.parseInt(inputs.get("requestId"));
			String link = inputs.get("link");
			mandateService.notif(resourceId, requestId, link);
			return Response.status(Status.OK).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@Secured
	@Path("Summon")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response notifySummon(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Client) {
			String email = inputs.get("email");
			String date = inputs.get("date");
			int requestId = Integer.parseInt(inputs.get("requestId"));
			String link = inputs.get("link");
			mandateService.notifSummon(email, date, requestId, link);
			return Response.status(Status.OK).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}
	
	@Secured
	@Path("Summon1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response notifySummon1(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Client) {
			String email = inputs.get("email");
			String date = inputs.get("date");
			int requestId = Integer.parseInt(inputs.get("requestId"));
			String link = inputs.get("link");
			mandateService.notifSummon(email, date, requestId, link);
			mandateService.traiter(requestId);
			return Response.status(Status.OK).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}

	@Secured
	@Path("cost")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cost(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Admin) {
			int ressourceId = 0;
			int projetId = 0;
			Date dateDebut = null;
			Date dateFin = null;
			Response response;
			try {
				projetId = Integer.parseInt(inputs.get("projectId"));
				ressourceId = Integer.parseInt(inputs.get("resourceId"));
				dateDebut = simpleDateFormat.parse(inputs.get("startDate"));
				dateFin = simpleDateFormat.parse(inputs.get("endDate"));
				Double montant = mandateService.calculateCost(ressourceId, projetId, dateDebut, dateFin);
				return Response.status(Status.OK).build();

			} catch (Exception e) {
				try {
					Double montant = mandateService.CostProject(projetId);
					return Response.status(Status.OK).entity(montant).build();
				} catch (Exception e1) {
					return Response.status(Status.BAD_REQUEST).build();
				}
			}

		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}

	@Secured
	@Path("suggestion")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response suggestion(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Admin) {
			int requestId = 0;
			try {
				requestId = Integer.parseInt(inputs.get("requestId"));
				return Response.ok(mandateService.SearchResourceBySkill(requestId), MediaType.APPLICATION_JSON).build();
			} catch (Exception e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}
	@Secured
	@Path("Addsuggestion")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Addsuggestion(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Admin) {
			int requestId = 0;
			int resourceId = 0;
			try {
				requestId = Integer.parseInt(inputs.get("requestId"));
				resourceId = Integer.parseInt(inputs.get("resourceId"));
				return Response.ok(mandateService.addSuggestion(resourceId,requestId), MediaType.APPLICATION_JSON).build();
			} catch (Exception e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}
	

	@Secured
	@Path("request")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response request(@QueryParam(value = "id") String clientId) {
		if (authenticatedUser.getRoleT() == Role.Admin) {
			return Response.ok(requestService.sortByDate(), MediaType.APPLICATION_JSON).build();

		} else if (authenticatedUser.getRoleT() == Role.Client && clientId != null) {
			return Response.ok(requestService.sortByDateClient(Integer.parseInt(clientId)), MediaType.APPLICATION_JSON)
					.build();
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();

	}

	@GET
	@Secured
	@Path("gps")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGps() {
		if (authenticatedUser.getRoleT() == Role.Admin) {
		if (mandateService.getGps().size() == 0)
			return Response.status(Status.NO_CONTENT).build();
		else
			return Response.ok(mandateService.getGps(), MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}
	
	@GET
	@Secured
	@Path("project")
	@Produces(MediaType.APPLICATION_JSON)
	public Response project() {
		if (authenticatedUser.getRoleT() == Role.Admin) {
		if (mandateService.getprojects().size() == 0)
			return Response.status(Status.NO_CONTENT).build();
		else
			return Response.ok(mandateService.getprojects(), MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}
	
	@GET
	@Secured
	@Path("map")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMapContent() {
		if (authenticatedUser.getRoleT() == Role.Admin) {
		if (mandateService.getContent().size() == 0)
			return Response.status(Status.NO_CONTENT).build();
		else
			return Response.ok(mandateService.getContent(), MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}
	
	
	
	
	@Secured
	@Path("cancel")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cancelsuggession(Map<String, String> inputs) {
		if (authenticatedUser.getRoleT() == Role.Client) {
			int requestId = 0;
			try {
				requestId = Integer.parseInt(inputs.get("requestId"));
				mandateService.cancelRequest(requestId);
				return Response.status(Response.Status.OK).entity("accept").build();
			} catch (Exception e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
		return Response.status(Response.Status.FORBIDDEN).entity("Acces denied").build();
	}

}
