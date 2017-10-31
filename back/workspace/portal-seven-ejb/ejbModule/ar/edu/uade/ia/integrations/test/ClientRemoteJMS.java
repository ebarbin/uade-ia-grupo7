package ar.edu.uade.ia.integrations.test;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * Session Bean implementation class ServerRemoteJMS
 */
@Stateless
@LocalBean
public class ClientRemoteJMS implements ClientRemoteJMSRemote, ClientRemoteJMSLocal {

	@Resource(lookup = "java:jboss/exported/jms/queue/OfertaHotelera")
	private Queue queueOfertaHotelera;

	@Resource(lookup = "java:jboss/exported/jms/queue/OfertaPaquete")
	private Queue queueOfertaPaquete;
	
	@Inject
	@JMSConnectionFactory("java:/jms/ra-pw1")
	JMSContext context;
	
    /**
     * Default constructor. 
     */
    public ClientRemoteJMS() {}

	@Override
	public void execute() {
		TextMessage message = this.context.createTextMessage("bla bla bla bla blaaaaaaa");
		this.context.createProducer().send(this.queueOfertaHotelera, message);
		this.context.createProducer().send(this.queueOfertaPaquete, message);
	}

}
