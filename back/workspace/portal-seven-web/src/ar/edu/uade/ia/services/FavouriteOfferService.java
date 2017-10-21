package ar.edu.uade.ia.services;

import java.util.List;

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

import ar.edu.uade.ia.common.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.managers.FavouriteOfferManager;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/favourite-offer")
@Stateless
public class FavouriteOfferService {

	@EJB
	private FavouriteOfferManager favouriteOfferManager;
	
	private static Logger LOGGER = Logger.getLogger(FavouriteOfferService.class);
	
	@PUT
	@Path("/hotel/{offerHotelId}/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response markFavouriteHotel(@PathParam("offerHotelId") Integer offerHotelId,
			@PathParam("portalUserId") Integer portalUserId, HotelOfferRequestDTO filter) {
		try {
			Boolean result = this.favouriteOfferManager.markFavouriteHotel(offerHotelId, portalUserId, filter);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			FavouriteOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@PUT
	@Path("/package/{offerPackageId}/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response markFavouritePackage(@PathParam("offerPackageId") Integer offerPackageId,
			@PathParam("portalUserId") Integer portalUserId, PackageOfferRequestDTO filter) {
		try {
			Boolean result = this.favouriteOfferManager.markFavouritePackage(offerPackageId, portalUserId, filter);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			FavouriteOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@GET
	@Path("/hotel/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHotelFavourite(@PathParam("portalUserId") Integer portalUserId) {
		try {
			List<HotelOfferHeaderDTO> result = this.favouriteOfferManager.getFavouriteHotelOffers(portalUserId);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			FavouriteOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
}
