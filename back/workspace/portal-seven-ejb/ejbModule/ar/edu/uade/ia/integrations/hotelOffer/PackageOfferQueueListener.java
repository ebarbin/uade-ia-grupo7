package ar.edu.uade.ia.integrations.hotelOffer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

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
			
			//TODO Implementar:
			//1- Validar la informacion (caso erroneo, enviar log)
			//2- Persistir en nuestras tablas
			
			System.out.println(hom);
			
			this.logging.info(LoggingAction.HOTEL_OFFER_REGISTRATION);
		} catch (Exception e) {
			this.logging.error(e.getMessage());
			PackageOfferQueueListener.LOGGER.error(e.getMessage(), e);
		}
	}
	
	@SuppressWarnings("unused")
	private void exampleDownloadImage(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf))) out.write(buf, 0, n);
		out.close();
		in.close();
		
		//Dato a persitir!
		byte[] data = out.toByteArray();
	}

}
