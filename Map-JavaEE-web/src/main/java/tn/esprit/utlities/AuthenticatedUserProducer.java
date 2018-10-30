package tn.esprit.utlities;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import tn.esprit.Map.interfaces.PersonServiceRemote;
import tn.esprit.Map.persistences.Person;


@RequestScoped
public class AuthenticatedUserProducer {
	@EJB
	PersonServiceRemote personManager;

    @Produces
    @RequestScoped
    @AuthenticatedUser
    private Person authenticatedUser;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUser String mail) {
        this.authenticatedUser = findUser(mail);
    }

    private Person findUser(String mail) {
        // Hit the the database or a service to find a user by its username and return it
        // Return the User instance
    	return personManager.findArtsukiByEmail(mail);
    }

}
