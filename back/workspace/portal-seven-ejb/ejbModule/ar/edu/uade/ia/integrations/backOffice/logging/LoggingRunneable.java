package ar.edu.uade.ia.integrations.backOffice.logging;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.jackson.JsonConverter;

public class LoggingRunneable implements Runnable {

	private static Logger LOGGER = Logger.getLogger(LoggingRunneable.class);
	
	private HttpClient httpClient;
	private HttpPost postRequest;
	private LoggingMessage message;
	
	public LoggingRunneable(LoggingMessage message, String value) {
		
		this.httpClient = HttpClientBuilder.create().build();
		this.postRequest = new HttpPost(value);
		this.message = message;
	}


	@Override
	public void run() {
		HttpResponse response;
		try {
			this.postRequest.addHeader("Content-Type", "application/json");
			StringEntity entity = new StringEntity(JsonConverter.convertToJson(message));
			this.postRequest.setEntity(entity);
			response = this.httpClient.execute(this.postRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				LoggingRunneable.LOGGER.error(response.getStatusLine().getReasonPhrase());
			}
		} catch (Exception e) {
			LoggingRunneable.LOGGER.error("Error al enviar log: " + e.getMessage(), e);
		} 
	}

}
