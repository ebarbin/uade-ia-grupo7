package ar.edu.uade.ia.integrations.packageOffer.producer.interfaces;

import javax.ejb.Remote;

@Remote
public interface PackageOfferProducerRemote {

	void sendTextMessage(String messageText);
}
