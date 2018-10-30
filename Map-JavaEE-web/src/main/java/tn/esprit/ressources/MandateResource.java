package tn.esprit.ressources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.Map.interfaces.MandateServiceLocal;

@Path("mandate")
@ManagedBean
public class MandateResource {

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@EJB
	MandateServiceLocal mandateService;

	@POST
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
				? Response.status(Status.CREATED).build() : Response.status(Status.EXPECTATION_FAILED).build();
		return reponse;

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMandate(@QueryParam(value = "ressourceId") String ressourceId,
			@QueryParam(value = "projetId") String projetId, @QueryParam(value = "dateDebut") String dateDebut,
			@QueryParam(value = "dateFin") String dateFin) throws ParseException {
		if ((ressourceId == null) && (projetId == null) && (dateDebut == null) && (dateFin == null)) {
			if (mandateService.getAll().size() == 0)
				return Response.status(Status.NO_CONTENT).build();
			else
				return Response.ok(mandateService.getAll(), MediaType.APPLICATION_JSON).build();
		} else if ((ressourceId != null) && (projetId == null) && (dateDebut == null) && (dateFin == null)) {

			if (!ressourceId.chars().allMatch(Character::isDigit))
				return Response.status(Status.BAD_REQUEST).build();

			if (mandateService.getByResource(Integer.parseInt(ressourceId)).size() == 0)
				return Response.status(Status.NO_CONTENT).build();

			else {
				return Response
						.ok(mandateService.getByResource(Integer.parseInt(ressourceId)), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();
			}

		}

		else if ((ressourceId == null) && (projetId != null) && (dateDebut == null) && (dateFin == null)) {

			if (!projetId.chars().allMatch(Character::isDigit))
				return Response.status(Status.BAD_REQUEST).build();

			if (mandateService.getByProject(Integer.parseInt(projetId)).isEmpty())
				return Response.status(Status.NO_CONTENT).build();

			else {
				return Response.ok(mandateService.getByProject(Integer.parseInt(projetId)), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();
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
				return Response.ok(mandateService.getByStartDate(uDate), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();
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
				return Response.ok(mandateService.getByEndDate(uDate), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();
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
				return Response.ok(mandateService.getByPeriod(dDate, fDate), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();
			}

		}

		else
			return Response.status(Response.Status.BAD_REQUEST).entity("Requete eronnée").build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGps(Map<String, String> inputs) {
		Response response;
		int ressourceId = 0;
		int projetId = 0;
		Date dateDebut = null;
		Date dateFin = null;
		int gpsId = 0;
		try {
			ressourceId = Integer.parseInt(inputs.get("resourceId"));
			projetId = Integer.parseInt(inputs.get("projectId"));
			gpsId = Integer.parseInt(inputs.get("gpsId"));
			dateDebut = simpleDateFormat.parse(inputs.get("startDate"));
			dateFin = simpleDateFormat.parse(inputs.get("endDate"));
			response = mandateService.addGps(ressourceId, projetId, dateDebut, dateFin, gpsId)
					? Response.status(Status.OK).build() : Response.status(Status.FORBIDDEN).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return response;
	}
	@Path("notify")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void notify(Map<String, String> inputs){
	int resourceId = Integer.parseInt(inputs.get("resourceId"));
	int requestId = Integer.parseInt(inputs.get("requestId"));
	String link = inputs.get("link");
	mandateService.notif(resourceId,requestId,link);
	}
	
}
