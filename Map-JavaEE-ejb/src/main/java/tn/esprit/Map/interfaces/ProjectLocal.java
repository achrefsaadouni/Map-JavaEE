package tn.esprit.Map.interfaces;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.Map.persistences.Client;

@Local
public interface ProjectLocal {
	public String archiveProject() throws ParseException;
}
