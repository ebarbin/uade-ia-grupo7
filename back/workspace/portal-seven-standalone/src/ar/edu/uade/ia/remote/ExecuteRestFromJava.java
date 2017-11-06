package ar.edu.uade.ia.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ExecuteRestFromJava {

	public static void main(String[] args) throws Exception {

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:8080/portal-seven-web/api/rest/favourite-offer/1");
		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String output;
		// Simply iterate through XML response and show on console.
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		HttpPost postRequest = new HttpPost("url");
		postRequest.addHeader("Content-Type", "application/json");
		StringEntity entity = new StringEntity("{\"key\":\"value\"}");
		postRequest.setEntity(entity);
		response = httpClient.execute(postRequest);
		
		/*
		 * URL url = new
		 * URL("http://localhost:8080/portal-seven-web/api/rest/favourite-offer/1");
		 * HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		 * conn.setRequestMethod("GET"); conn.setRequestProperty("Accept",
		 * "application/json");
		 * 
		 * if (conn.getResponseCode() != 200) { throw new
		 * RuntimeException("Failed : HTTP error code : " + conn.getResponseCode()); }
		 * 
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader((conn.getInputStream())));
		 * 
		 * String output;
		 * 
		 * System.out.println("Output from Server .... \n"); while ((output =
		 * br.readLine()) != null) { System.err.println(output); }
		 */
	}

}
