package ar.edu.uade.ia.remote;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class ExecuteRestFromJava {

	public static void main(String[] args) throws Exception {

		HttpClient httpClient = HttpClientBuilder.create().build();

		HttpPost postRequest = new HttpPost("http://192.168.0.108:8080/TPO_BO_WEB/rest/ServiciosBO/RegistrarLog");
		postRequest.addHeader("Content-Type", "application/json");
		StringEntity entity = new StringEntity("{\"plataformaEnvia\":\"PW\", \"plataformaRecibe\":\"OH\", \"servicio\":\"Crear oferta hotelera\", \"observacion\":\"SUCCESS\"}");
		postRequest.setEntity(entity);
		HttpResponse response = httpClient.execute(postRequest);
		
		if(response.getStatusLine().getStatusCode() == 200) {
			
		}
		
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
