package tn.esprit.ressources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
