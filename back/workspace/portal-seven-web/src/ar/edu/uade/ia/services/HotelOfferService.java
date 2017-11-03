package ar.edu.uade.ia.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.dtos.AuthorizeStatusDTO;
import ar.edu.uade.ia.common.dtos.HotelAuthorizeRequestDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.RoomDTO;
import ar.edu.uade.ia.common.dtos.ValorationDTO;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.managers.HotelOfferManager;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/hotel-offer")
@Stateless
public class HotelOfferService {

	private static Logger LOGGER = Logger.getLogger(HotelOfferService.class);

	@EJB
	private HotelOfferManager hotelOfferManager;

	@EJB
	private LoggingJMS logging;

	@POST
	@Path("/search/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@PathParam("portalUserId") Integer portalUserId, HotelOfferRequestDTO request) {
		try {
			List<HotelOfferHeaderDTO> result = this.hotelOfferManager.search(portalUserId, request);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			this.logging.error(e.getMessage());
			HotelOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}

	@PUT
	@Path("/search/other-rooms/{roomId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchOtherRooms(@PathParam("roomId") Integer roomId, HotelOfferRequestDTO request) {
		try {
			List<RoomDTO> result = this.hotelOfferManager.searchOtherRooms(roomId, request);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			HotelOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
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
			HotelOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}

	@PUT
	@Path("/authorize/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorize(@PathParam("id") Integer id, HotelAuthorizeRequestDTO req) {
		try {
			AuthorizeStatusDTO statusDTO = this.hotelOfferManager.autorize(id, req);
			return Response.ok(new PortalResponse(statusDTO)).build();
		} catch (Exception e) {
			HotelOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@PUT
	@Path("/valoration/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response valoration(@PathParam("id") Integer id, ValorationDTO valoration) {
		try {
			Integer value = this.hotelOfferManager.valoration(id, valoration.getVote());
			return Response.ok(new PortalResponse(value)).build();
		} catch (Exception e) {
			HotelOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
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
