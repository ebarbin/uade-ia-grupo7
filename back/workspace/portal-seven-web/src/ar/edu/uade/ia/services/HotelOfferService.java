package ar.edu.uade.ia.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.uade.ia.commons.dtos.HotelOfferDTO;
import ar.edu.uade.ia.commons.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.commons.dtos.ImageDTO;
import ar.edu.uade.ia.commons.dtos.requests.HotelOfferRequestDTO;
import ar.edu.uade.ia.managers.interfaces.HotelOfferManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/hotel-offer")
@Stateless
public class HotelOfferService {

	@EJB
	private HotelOfferManagerRemote hotelOfferManager;

	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(HotelOfferRequestDTO request) {
		try {
			List<HotelOfferHeaderDTO> result = this.hotelOfferManager.search(request);
			this.clearImageDataField(result);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}

	@GET
	@Path("/detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetail(@PathParam("id") Integer id) {
		try {
			HotelOfferDTO dto = this.hotelOfferManager.getDetail(id);
			this.clearImageDataField(dto);
			return Response.ok(new PortalResponse(dto)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}

	private void clearImageDataField(List<HotelOfferHeaderDTO> result) {
		for (HotelOfferHeaderDTO header : result) {
			if (header.getImages() != null) {
				for (ImageDTO img : header.getImages()) {
					img.setData(null);
				}
			}
		}
	}
	
	private void clearImageDataField(HotelOfferDTO ho) {
		if (ho.getHotel() != null && ho.getHotel().getImages() != null) {
			for (ImageDTO img : ho.getHotel().getImages()) {
				img.setData(null);
			}
		}
		if (ho.getRoom() != null && ho.getRoom().getImages() != null) {
			for (ImageDTO img : ho.getRoom().getImages()) {
				img.setData(null);
			}
		}
	}
}
