package ar.edu.uade.ia.integrations.hotelOffer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;

/**
 * Message-Driven Bean implementation class for: PackageOfferQueueListener
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/PackageOfferQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/queue/PackageOfferQueue")
public class PackageOfferQueueListener implements MessageListener {

	private static Logger LOGGER = Logger.getLogger(PackageOfferQueueListener.class);
	
	@EJB
	private LoggingJMS logging;
	
	/**
	 * Default constructor.
	 */
	public PackageOfferQueueListener() {}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			//TODO Parsear JSON
			String messageText = ((TextMessage) message).getText();
			System.out.println(messageText);
			this.logging.info(LoggingAction.HOTEL_OFFER_REGISTRATION);
		} catch (JMSException e) {
			this.logging.error();
			PackageOfferQueueListener.LOGGER.error(e.getMessage(), e);
		}
	}

}
