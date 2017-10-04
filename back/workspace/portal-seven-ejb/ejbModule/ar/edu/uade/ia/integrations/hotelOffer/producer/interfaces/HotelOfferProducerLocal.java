package ar.edu.uade.ia.integrations.hotelOffer.producer.interfaces;

import javax.ejb.Local;

@Local
public interface HotelOfferProducerLocal {

	void sendTextMessage(String messageText);

}
