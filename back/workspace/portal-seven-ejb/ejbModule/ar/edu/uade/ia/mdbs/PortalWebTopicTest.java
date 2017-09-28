package ar.edu.uade.ia.mdb;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;

import ar.edu.uade.ia.ejbs.entities.bussiness.PortalUser;

/**
 * Session Bean implementation class PortalWebTopicTest
 */
@Stateless
@LocalBean
public class PortalWebTopicTest {

	@Resource(lookup = "java:/jms/topic/portalWebTopicTest")
	private Topic topic;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;
	
    public PortalWebTopicTest() {}

	public void sendTextMessage(String messageText) {
		TextMessage message = this.context.createTextMessage(messageText);
		this.context.createProducer().send(this.topic, message);
	}

	public void sendObjectMessage(PortalUser portalUser) {
		ObjectMessage message = this.context.createObjectMessage(portalUser);
		this.context.createProducer().send(this.topic, message);
	}
}
