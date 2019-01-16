package tn.esprit.webservices;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.Map.interfaces.StatSideRhInterfaceRemote;

@Path("StatRh")
@ManagedBean
public class StatRhWebService {
	@EJB
	StatSideRhInterfaceRemote StatRh;
	
	@Path("/RhRankedByNote")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> RhRankedByNote() {
		return StatRh.RhRankedByNote();
	}
	
	@Path("/RhRankedByMaxDayOff/{rhid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public int RhRankedByMaxDayOff(@PathParam("rhid")int rhid)  {
		return StatRh.RhRankedByMaxDayOff(rhid) ;
	}
	
	@Path("/SkillsRecommended")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> SkillsRecommended() {
		return StatRh.SkillsRecommended();
	}
	
	@Path("/NbClientByRegion")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> NbClientByRegion() {
		return StatRh.NbClientByRegion();
	}
	@Path("/CountNbProjectByRegion")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object[]> CountNbProjectByRegion() {
		return StatRh.CountNbProjectByRegion();
	}
}
