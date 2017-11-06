package ar.edu.uade.ia.remote;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Test {

    public static void main(String[] args) throws Exception {
        Context namingContext = null;
        JMSContext jmsContext = null;
        try {
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            env.put(Context.PROVIDER_URL, "http-remoting://192.168.0.100:8080"); // Cambiar por IP remota aca
            env.put(Context.SECURITY_PRINCIPAL, "hornetq");
            env.put(Context.SECURITY_CREDENTIALS, "hornetq");
            namingContext = new InitialContext(env);

            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup("jms/RemoteConnectionFactory");
            System.out.println("Got ConnectionFactory");

            Destination destination = (Destination) namingContext.lookup("jms/queue/ofertasHotel");
            System.out.println("Got JMS Endpoint");

            jmsContext = connectionFactory.createContext("hornetq", "hornetq");

            TextMessage message = jmsContext.createTextMessage("EMANUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEL");

            jmsContext.createProducer().send(destination, message);
            System.out.println("Sent message");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (namingContext != null) {
                namingContext.close();
            }
            if (jmsContext != null) {
                jmsContext.close();
            }
        }
    }
}