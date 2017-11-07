package ar.edu.uade.ia.integrations.backOffice.logging;

import javax.ejb.Stateless;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.common.jackson.JsonConverter;

/**
 * Session Bean implementation class LoggingJMS
 */
@Stateless
public class LoggingJMS {

	private static Logger LOGGER = Logger.getLogger(LoggingJMS.class);

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
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost("http://192.168.0.108:8080/TPO_BO_WEB/rest/ServiciosBO/RegistrarLog");
			postRequest.addHeader("Content-Type", "application/json");

			StringEntity entity = new StringEntity(JsonConverter.convertToJson(message));
			postRequest.setEntity(entity);
			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				LoggingJMS.LOGGER.error(response.getStatusLine().getReasonPhrase());
			}
		} catch (Exception e) {
			LoggingJMS.LOGGER.error(e.getMessage(), e);
		}
	}
}
