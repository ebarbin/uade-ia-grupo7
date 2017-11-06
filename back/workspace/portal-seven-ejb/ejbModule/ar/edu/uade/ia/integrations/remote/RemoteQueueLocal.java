package ar.edu.uade.ia.integrations.remote;

import javax.ejb.Local;

@Local
public interface RemoteQueueLocal {

	public void sendMessage(String msg) throws Exception;
}
