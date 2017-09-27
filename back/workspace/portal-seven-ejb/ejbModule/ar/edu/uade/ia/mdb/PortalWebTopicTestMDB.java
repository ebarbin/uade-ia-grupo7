package ar.edu.uade.ia.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import ar.edu.uade.ia.ejbs.entities.bussiness.PortalUser;

/**
 * Message-Driven Bean implementation class for: PortalWebTopicTestMDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/topic/portalWebTopicTest"), 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "java:/jms/topic/portalWebTopicTest")
public class PortalWebTopicTestMDB implements MessageListener {

    /**
     * Default constructor. 
     */
    public PortalWebTopicTestMDB() {}
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	System.out.println("Se recibio un mensaje del topico");
  	  try {
          String messageText = null;
          PortalUser portalUser = null;
          if(message instanceof TextMessage){
              messageText = ((TextMessage)message).getText();
              System.out.println(messageText);
          } else if (message instanceof ObjectMessage) {
        	  portalUser = (PortalUser)((ObjectMessage)message).getObject();
        	  System.out.println(portalUser);
          }
      } catch (Exception e) {
      }
        
    }

}
