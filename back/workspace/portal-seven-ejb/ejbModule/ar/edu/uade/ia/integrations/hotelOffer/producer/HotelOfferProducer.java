package ar.edu.uade.ia.integrations.hotelOffer.producer;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import ar.edu.uade.ia.integrations.hotelOffer.producer.interfaces.HotelOfferProducerLocal;
import ar.edu.uade.ia.integrations.hotelOffer.producer.interfaces.HotelOfferProducerRemote;

/**
 * Session Bean implementation class HotelOfferProducer
 */
@Stateless
@LocalBean
public class HotelOfferProducer implements HotelOfferProducerRemote, HotelOfferProducerLocal {

	@Resource(lookup = "java:/queue/HotelOfferQueue")
	private Queue queue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;
	
    /**
     * Default constructor. 
     */
    public HotelOfferProducer() {}

    @Override
	public void sendTextMessage(String messageText) {
		TextMessage message = this.context.createTextMessage(messageText);
		this.context.createProducer().send(this.queue, message);
	}
}
