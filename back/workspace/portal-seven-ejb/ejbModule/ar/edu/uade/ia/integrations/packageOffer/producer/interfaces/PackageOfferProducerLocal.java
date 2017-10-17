package ar.edu.uade.ia.integrations.packageOffer.producer.interfaces;

import javax.ejb.Local;

@Local
public interface PackageOfferProducerLocal {

	void sendTextMessage(String messageText);
}
