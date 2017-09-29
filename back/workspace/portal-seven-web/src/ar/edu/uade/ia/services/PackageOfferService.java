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

import org.jboss.logging.Logger;

import ar.edu.uade.ia.dtos.PackageOfferDTO;
import ar.edu.uade.ia.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.managers.interfaces.PackageOfferManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

/**
 * Session Bean implementation class PackageOfferService
 */
@Path("/package-offer")
@Stateless
public class PackageOfferService {

	private static Logger LOGGER = Logger.getLogger(PackageOfferService.class);
	 
	@EJB
	private PackageOfferManagerRemote packageOfferManager;
	
    /**
     * Default constructor. 
     */
    public PackageOfferService() {}
    
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(PackageOfferRequestDTO request) {
		try {
			List<PackageOfferHeaderDTO> result = this.packageOfferManager.search(request);
			this.clearImageDataField(result);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			PackageOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}

	@GET
	@Path("/detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDetail(@PathParam("id") Integer id) {
		try {
			PackageOfferDTO dto = this.packageOfferManager.getDetail(id);
			this.clearImageDataField(dto);
			return Response.ok(new PortalResponse(dto)).build();
		} catch (Exception e) {
			PackageOfferService.LOGGER.error(e.getMessage(), e);
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
	
	private void clearImageDataField(PackageOfferDTO dto) {
		// TODO Auto-generated method stub
	}

	private void clearImageDataField(List<PackageOfferHeaderDTO> result) {
		// TODO Auto-generated method stub
	}
}
