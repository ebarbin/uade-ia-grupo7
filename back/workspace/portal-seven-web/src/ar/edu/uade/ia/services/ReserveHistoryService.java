package ar.edu.uade.ia.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.dtos.ReservationDTO;
import ar.edu.uade.ia.managers.ReserveHistoryManager;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/reserve-history")
@Stateless
public class ReserveHistoryService {

	private static Logger LOGGER = Logger.getLogger(ReserveHistoryService.class);
	
	@EJB
	private ReserveHistoryManager reserveHistoryManager;
	
	@GET
	@Path("/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservations(@PathParam("portalUserId") Integer portalUserId) {
		try {
			List<ReservationDTO> result = this.reserveHistoryManager.getReservations(portalUserId);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			ReserveHistoryService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
}
