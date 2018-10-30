package tn.esprit.Map.services;

import java.text.ParseException;
import java.util.Timer;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import tn.esprit.Map.interfaces.ProjectLocal;


@SuppressWarnings("serial")
public class StartUpService extends HttpServlet {
	@EJB
	ProjectServiceLocal projectLocal;

	public void init() throws ServletException {
//		Timer timerObj = new Timer(true);
//		timerObj.scheduleAtFixedRate(projectLocal, 0, 5000);
	}

}
