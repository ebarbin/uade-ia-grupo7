package ar.edu.uade.ia.integrations.test;

import javax.ejb.Remote;

@Remote
public interface ClientRemoteJMSRemote {

	void execute();
}
