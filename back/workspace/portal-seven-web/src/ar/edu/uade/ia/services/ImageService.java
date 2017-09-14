package ar.edu.uade.ia.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.uade.ia.commons.dtos.ImageDTO;
import ar.edu.uade.ia.managers.interfaces.ImageManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/image")
@Stateless
public class ImageService {

	@EJB
	private ImageManagerRemote photoManager;

	@GET
	@Path("/{id}")
	@Produces({ "image/jpg" })
	public Response get(@PathParam("id") Integer id) {
		try {
			ImageDTO photoDTO = this.photoManager.getById(id);
			return Response.ok(photoDTO.getData()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
}
