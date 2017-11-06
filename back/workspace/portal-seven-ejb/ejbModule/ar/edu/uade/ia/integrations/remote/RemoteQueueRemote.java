package ar.edu.uade.ia.integrations.remote;

import javax.ejb.Remote;

@Remote
public interface RemoteQueueRemote {

	public void sendMessage(String msg) throws Exception;
}
