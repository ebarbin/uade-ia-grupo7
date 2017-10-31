package ar.edu.uade.ia.integrations.test;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/OfertaHotelera"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class OfertaHotelera implements MessageListener {

	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	String jsonString;
		try {
			jsonString = ((TextMessage) message).getText();
			System.out.println("HOTEL " + jsonString);
		} catch (JMSException e) {
			e.printStackTrace();
		}
        
    }

}
