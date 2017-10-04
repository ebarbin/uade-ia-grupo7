package ar.edu.uade.ia.integrations.hotelOffer.producer.interfaces;

import javax.ejb.Remote;

@Remote
public interface HotelOfferProducerRemote {

	void sendTextMessage(String messageText);
}
