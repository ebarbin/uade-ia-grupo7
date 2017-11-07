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
import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.PackageAuthorizeRequestDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.ValorationDTO;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.managers.PackageOfferManager;
import ar.edu.uade.ia.services.response.PortalResponse;

/**
 * Session Bean implementation class PackageOfferService
 */
@Path("/package-offer")
@Stateless
public class PackageOfferService {

	private static Logger LOGGER = Logger.getLogger(PackageOfferService.class);
	 
	@EJB
	private PackageOfferManager packageOfferManager;
	
	@EJB
	private LoggingJMS logging;
	
    /**
     * Default constructor. 
     */
    public PackageOfferService() {}
    
	@POST
	@Path("/search/{portalUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@PathParam("portalUserId") Integer portalUserId, PackageOfferRequestDTO request) {
		try {
			List<PackageOfferHeaderDTO> result = this.packageOfferManager.search(portalUserId, request);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			this.logging.error("Error to search package: " + e.getMessage());
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
			this.logging.error("Error to get detail from package: " + e.getMessage());
			PackageOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@PUT
	@Path("/search/other-packages/{packageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchOtherPackages(@PathParam("packageId") Integer packageId, PackageOfferRequestDTO request) {
		try {
			List<PackageOfferHeaderDTO> result = this.packageOfferManager.searchOtherPackages(packageId, request);
			return Response.ok(new PortalResponse(result)).build();
		} catch (Exception e) {
			this.logging.error("Error to search other package: " + e.getMessage());
			PackageOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@PUT
	@Path("/authorize/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorize(@PathParam("id") Integer id, PackageAuthorizeRequestDTO req) {
		try {
			AuthorizeStatusDTO statusDTO = this.packageOfferManager.autorize(id, req);
			return Response.ok(new PortalResponse(statusDTO)).build();
		} catch (Exception e) {
			this.logging.error("Error to authorize package: " + e.getMessage());
			PackageOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@PUT
	@Path("/valoration/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response valoration(@PathParam("id") Integer id, ValorationDTO valoration) {
		try {
			Integer value = this.packageOfferManager.valoration(id, valoration.getVote());
			return Response.ok(new PortalResponse(value)).build();
		} catch (Exception e) {
			this.logging.error("Error to valorate package: " + e.getMessage());
			PackageOfferService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	private void clearImageDataField(PackageOfferDTO dto) {
		for(ImageDTO img : dto.getImages()) {
			img.setData(null);
		}
	}
}
