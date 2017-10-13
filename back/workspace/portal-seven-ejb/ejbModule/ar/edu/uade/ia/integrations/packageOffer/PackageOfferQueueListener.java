package ar.edu.uade.ia.integrations.packageOffer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.common.jackson.JsonConverter;
import ar.edu.uade.ia.ejbs.PackageOfferEJB;
import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.integrations.common.AbstractQueueListener;
import ar.edu.uade.ia.integrations.packageOffer.message.PackageOfferMessage;

/**
 * Message-Driven Bean implementation class for: PackageOfferQueueListener
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/PackageOfferQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/queue/PackageOfferQueue")
public class PackageOfferQueueListener extends AbstractQueueListener implements MessageListener {

	private static Logger LOGGER = Logger.getLogger(PackageOfferQueueListener.class);

	@EJB
	private LoggingJMS loggingService;

	@EJB
	private PackageOfferEJB packageOfferEJB;
	
	/**
	 * Default constructor.
	 */
	public PackageOfferQueueListener() {

	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			String jsonString = ((TextMessage) message).getText();
			PackageOfferMessage pom = (PackageOfferMessage) JsonConverter.convertToObject(jsonString,
					PackageOfferMessage.class);
			
			// TODO VALIDAR EL MENSAJE COMPLETO!!
			String code = this.getProviderCode(pom.getCodigo_prestador());
			
			PackageOffer po = this.packageOfferEJB.getByCode(code);
			if (po != null) {
				//aca tenemos un asunto. porque a diferencia de la oferta de hoteles donde tenemos el hotel y la oferta.
				//aca tenemos todo junto. Un prestador no puede mandar mas de una oferta de paquetes?
			}
			
			this.loggingService.info(LoggingAction.HOTEL_OFFER_REGISTRATION);
		} catch (Exception e) {
			this.loggingService.error(e.getMessage());
			PackageOfferQueueListener.LOGGER.error(e.getMessage(), e);
		}
	}

}
