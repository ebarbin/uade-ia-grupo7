package ar.edu.uade.ia.integrations.test;

import javax.ejb.Local;

@Local
public interface ClientRemoteJMSLocal {

	void execute();
}
