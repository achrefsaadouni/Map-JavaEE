package tn.esprit.webservices;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Path;

import tn.esprit.Map.interfaces.StatSideCandidateInterfaceRemote;

@Path("StatCandidate")
@ManagedBean
public class StatCandidateWebService {
	@EJB
	StatSideCandidateInterfaceRemote StatCandidate;
}
