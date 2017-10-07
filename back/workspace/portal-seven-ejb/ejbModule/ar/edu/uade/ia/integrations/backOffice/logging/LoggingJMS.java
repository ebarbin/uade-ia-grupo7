package ar.edu.uade.ia.integrations.backOffice.logging;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.LoggingAction;

/**
 * Session Bean implementation class LoggingJMS
 */
@Stateless
public class LoggingJMS {

	private static Logger LOGGER = Logger.getLogger(LoggingJMS.class);

	// @Resource(lookup = "java:/jms/topic/portalWebTopicTest")
	private Queue queue;

	@Inject
	// @JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;

	public LoggingJMS() {
	}

	public void error(String desc) {
		try {
			if (this.queue == null) return;
			LoggingMessage lm = new LoggingMessage();
			lm.setAccion(LoggingAction.ERROR.getId());
			lm.setDescripcion(desc);
			ObjectMessage message = this.context.createObjectMessage(lm);
			this.context.createProducer().send(this.queue, message);
		} catch (Exception e) {
			LoggingJMS.LOGGER.error(e.getMessage(), e);
		}
	}

	public void info(LoggingAction action) {
		try {
			if (this.queue == null) return;
			LoggingMessage lm = new LoggingMessage();
			lm.setAccion(action.getId());
			ObjectMessage message = this.context.createObjectMessage(lm);
			this.context.createProducer().send(this.queue, message);
		} catch (Exception e) {
			LoggingJMS.LOGGER.error(e.getMessage(), e);
		}
	}
}
