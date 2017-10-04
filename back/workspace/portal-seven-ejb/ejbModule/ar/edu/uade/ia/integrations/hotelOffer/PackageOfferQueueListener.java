package ar.edu.uade.ia.integrations.hotelOffer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.common.jackson.JsonConverter;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.integrations.hotelOffer.message.HotelOfferMessage;

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
			String jsonString = ((TextMessage) message).getText();
			HotelOfferMessage hom = (HotelOfferMessage) JsonConverter.convertToObject(jsonString, HotelOfferMessage.class);
			
			System.out.println(hom);
			this.logging.info(LoggingAction.HOTEL_OFFER_REGISTRATION);
		} catch (Exception e) {
			this.logging.error();
			PackageOfferQueueListener.LOGGER.error(e.getMessage(), e);
		}
	}

}
