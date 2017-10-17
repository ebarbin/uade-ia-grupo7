package ar.edu.uade.ia.integrations.packageOffer.producer;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import ar.edu.uade.ia.integrations.packageOffer.producer.interfaces.PackageOfferProducerLocal;
import ar.edu.uade.ia.integrations.packageOffer.producer.interfaces.PackageOfferProducerRemote;

/**
 * Session Bean implementation class PackageOfferProducer
 */
@Stateless
@LocalBean
public class PackageOfferProducer implements PackageOfferProducerRemote, PackageOfferProducerLocal {

	@Resource(lookup = "java:/queue/PackageOfferQueue")
	private Queue queue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;
	
    /**
     * Default constructor. 
     */
    public PackageOfferProducer() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void sendTextMessage(String messageText) {
		TextMessage message = this.context.createTextMessage(messageText);
		this.context.createProducer().send(this.queue, message);
	}
}
