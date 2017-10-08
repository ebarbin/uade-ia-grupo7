package ar.edu.uade.ia.integrations;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ManageBackOffice {

	@WebMethod
	public BackOfficeResponse autorizeHotelOffer(BackOfficeRequest request) {
		BackOfficeResponse response = new BackOfficeResponse();
		if (request.getId().equals(Integer.valueOf(1))) {
			response.setState("Authorize");
		} else {
			response.setState("Denied");
		}
		
		return response;
	}
}
