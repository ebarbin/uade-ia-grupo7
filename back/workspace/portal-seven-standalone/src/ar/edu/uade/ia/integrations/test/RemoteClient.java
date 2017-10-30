package ar.edu.uade.ia.integrations.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteClient {

	public static void main(String[] args) throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "portal-seven";
		final String ejbModuleName = "portal-seven-ejb";
		final String distinctName = "";
		final String ejbClassName = "ClientRemoteJMS";
		final String fullInterfaceName = ClientRemoteJMSRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		ClientRemoteJMSRemote remoteProducer = (ClientRemoteJMSRemote)

		context.lookup(lookupName);
		
		remoteProducer.execute();
	}

}
