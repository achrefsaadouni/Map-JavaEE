<<<<<<< HEAD
package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

@Remote
public interface ProjectRemote {
	//public String getProjectById(int projectId);
	public String test();
}
=======
package tn.esprit.Map.interfaces;

import javax.ejb.Remote;

@Remote
public interface ProjectRemote {
	public String getProjectById(int projectId);
}
>>>>>>> branch 'master' of https://github.com/saadouniachref/Map-JavaEE.git
