package tn.esprit.Map.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.Map.persistences.Modules;

@Remote
public interface ModulesServices {
	public Modules AddModule(int idCategory , Modules Module);
	public List<Modules> ShowAll();
	public Boolean deleteModule(int id);
	public Modules UpdateModule( Modules Module);
	public List<Modules> ShowByCategory(int id );
}
