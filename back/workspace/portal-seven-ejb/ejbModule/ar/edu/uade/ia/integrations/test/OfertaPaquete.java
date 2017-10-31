package ar.edu.uade.ia.integrations.test;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: ServerRemoteJMS
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/OfertaPaquete"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class OfertaPaquete implements MessageListener {


    /**
     * Default constructor. 
     */
    public OfertaPaquete() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	String jsonString;
		try {
			jsonString = ((TextMessage) message).getText();
			System.out.println("PAQUETE " + jsonString);
		} catch (JMSException e) {
			e.printStackTrace();
		}
        
    }

}
