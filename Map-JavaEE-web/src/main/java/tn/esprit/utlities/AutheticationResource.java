package tn.esprit.utlities;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.Map.interfaces.PersonServiceRemote;
import tn.esprit.Map.persistences.Person;

@Path("/authentication")
@RequestScoped
public class AutheticationResource {
	@Context
	private UriInfo uriInfo;

	@PersistenceContext
	private EntityManager em;
	
	@Context
	SecurityContext securityContext;

	@EJB
	private PersonServiceRemote PersonManager;
	
	@Inject
	@AuthenticatedUser
	Event<String> userAuthenticatedEvent;
	
	
	    @POST
	    @Produces(MediaType.TEXT_PLAIN)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response authenticateUser(Map<String,String> inputs) {

	        try {

	            // Authenticate the user using the credentials provided
	            authenticate(inputs.get("login"), inputs.get("password"));

	            // Issue a token for the user
	            String token = issueToken(inputs.get("email"));

	            // Return the token on the response
	            return Response.ok(token).build();

	        } catch (Exception e) {
	            return Response.status(Response.Status.FORBIDDEN).build();
	        }      
	    }

	    private void authenticate(String username, String password) throws Exception {
	        if(!PersonManager.login(username, password))
	        {
	        	throw new Exception("Invalid credentials");
	        }
	    	
	    }
		private Date toDate(LocalDateTime localDateTime) {
			return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}


	    private String issueToken(String username) {
	    	String keyString = "simplekey";
			Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
			System.out.println("the key is : " + key);

			String jwtToken = Jwts.builder().setSubject(username).
					setIssuer(uriInfo.getAbsolutePath().toString())
					.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
					.signWith(SignatureAlgorithm.HS512, key).compact();

			System.out.println("the returned token is : " + jwtToken);
			return jwtToken;
	    
	}

}
