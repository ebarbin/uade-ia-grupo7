package ar.edu.uade.ia.integrations.backOffice.logging;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.ConfigurationType;
import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.ejbs.ConfigurationEJB;
import ar.edu.uade.ia.entities.Configuration;

/**
 * Session Bean implementation class LoggingJMS
 */
@Stateless
public class LoggingJMS {

	private static Logger LOGGER = Logger.getLogger(LoggingJMS.class);

	@EJB
	private ConfigurationEJB configurationEJB;
	
	public LoggingJMS() {}

	public void error(String desc) {
		
		LoggingMessage logginMessage = new LoggingMessage();
		logginMessage.setServicio(LoggingAction.ERROR.getDescription());
		logginMessage.setObservacion(desc);
		this.sendMessage(logginMessage);
	}

	public void info(LoggingAction action) {
		
		LoggingMessage logginMessage = new LoggingMessage();
		logginMessage.setServicio(action.getDescription());
		this.sendMessage(logginMessage);
	}
	
	private void sendMessage(LoggingMessage message) {
		try {
			Configuration conf = this.configurationEJB.getByKeyType(ConfigurationType.LOGGING);
			if (conf != null) {
				LoggingRunneable myRunnable = new LoggingRunneable(message, conf.getValue()); 
				Thread myThread = new Thread(myRunnable);
				myThread.start();
			}
		} catch (Exception e) {
			LoggingJMS.LOGGER.error(e.getMessage(), e);
		}
	}
}
