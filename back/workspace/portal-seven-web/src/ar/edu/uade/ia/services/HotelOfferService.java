package ar.edu.uade.ia.services;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.uade.ia.commons.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.managers.interfaces.UserManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/hotel-offer")
@Stateless
public class HotelOfferService {

	@EJB
	private UserManagerRemote userManager;
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(HotelOfferRequestDTO request) {
		try {
			return Response.ok(new PortalResponse(new ArrayList<HotelOfferRequestDTO>())).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
}
