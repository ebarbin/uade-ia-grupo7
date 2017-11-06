package ar.edu.uade.ia.integrations.remote;

import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Session Bean implementation class RemoteQueue
 */
@Stateless
@LocalBean
public class RemoteQueue implements RemoteQueueRemote, RemoteQueueLocal {
	
	/**
	 * Default constructor.
	 */
	public RemoteQueue() throws Exception {

	}

	public void sendMessage(String msg) throws Exception {

		Context namingContext = null;

		final Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "http-remoting://192.168.1.16:8080"); // Cambiar por IP remota aca
		env.put(Context.SECURITY_PRINCIPAL, "user");
		env.put(Context.SECURITY_CREDENTIALS, "user");
		namingContext = new InitialContext(env);

		ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup("java:jms/RemoteConnectionFactory");
		System.out.println("Got ConnectionFactory");

		Destination destination = (Destination) namingContext.lookup("jms/queue/ofertasHotel");
		System.out.println("Got JMS Endpoint");

		JMSContext jmsContext = connectionFactory.createContext("user", "user");

		TextMessage message = jmsContext.createTextMessage(msg);
		jmsContext.createProducer().send(destination, message);
	}

}
