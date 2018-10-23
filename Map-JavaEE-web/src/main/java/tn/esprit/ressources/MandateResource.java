package tn.esprit.ressources;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.Map.interfaces.MandateServiceLocal;

@Path("mandate")
public class MandateResource {
	@EJB
	MandateServiceLocal mandateService;

	@GET
	@Produces("application/json")
	public Response getMandate(@QueryParam(value = "ressourceId") String ressourceId,
			@QueryParam(value = "projetId") String projetId, @QueryParam(value = "dateFin") String dateDebut,
			@QueryParam(value = "dateFin") String dateFin) {
		if ((ressourceId == null) && (projetId == null) && (dateDebut == null) && (dateFin == null)) {
			if (mandateService.getAll() == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (mandateService.getAll().size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

			else
				return Response.ok(mandateService.getAll(), MediaType.APPLICATION_JSON).build();

		} else if ((ressourceId != null) && (projetId == null) && (dateDebut == null) && (dateFin == null)) {
			
			if (mandateService.getByResource(Integer.parseInt(ressourceId)) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (mandateService.getByResource(Integer.parseInt(ressourceId)).size() == 0)
				return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

			else {
				return Response.ok(mandateService.getByResource(Integer.parseInt(ressourceId)), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();
			}

		
		} else
			return Response.status(Response.Status.BAD_REQUEST).entity("Requete eronnée").build();
	}
}
