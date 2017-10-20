package ar.edu.uade.ia.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.managers.FavoriteHotelOfferManager;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/favorite-hotel-offer")
@Stateless
public class FavoriteHotelOfferService {

	@EJB
	private FavoriteHotelOfferManager favoriteHotelOfferManager;
	
	private static Logger LOGGER = Logger.getLogger(FavoriteHotelOfferService.class);
	
	@PUT
	@Path("/{offerHotelId}/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response markFavorite(@PathParam("offerHotelId") Integer offerHotelId,
			@PathParam("portalUserId") Integer portalUserId) {
		try {
			Boolean result = this.favoriteHotelOfferManager.markFavorite(offerHotelId, portalUserId);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			FavoriteHotelOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}

	@GET
	@Path("/{offerHotelId}/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response isFavaorite(@PathParam("offerHotelId") Integer offerHotelId,
			@PathParam("portalUserId") Integer portalUserId) {
		try {
			return Response.ok(new PortalResponse()).build();
		} catch (Exception e) {
			FavoriteHotelOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
}
