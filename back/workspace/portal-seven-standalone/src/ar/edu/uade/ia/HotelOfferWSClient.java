package ar.edu.uade.ia;

import ar.edu.uade.ia.integrations.BackOfficeRequest;
import ar.edu.uade.ia.integrations.BackOfficeResponse;
import ar.edu.uade.ia.integrations.ManageBackOfficeService;

public class HotelOfferWSClient {

	public static void main(String[] args) {
		ManageBackOfficeService service = new ManageBackOfficeService();
		BackOfficeRequest request = new BackOfficeRequest();
		request.setId(2);
		BackOfficeResponse response = service.getManageBackOfficePort().autorizeHotelOffer(request);
		System.out.println("State: " + response.getState());
	}
}
