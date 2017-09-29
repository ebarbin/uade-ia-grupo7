package ar.edu.uade.ia.services.common;

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

import ar.edu.uade.ia.dtos.PortalUserDTO;
import ar.edu.uade.ia.managers.interfaces.common.PortalUserManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/portal-user")
@Stateless
public class PortalUserService {

	private static Logger LOGGER = Logger.getLogger(PortalUserService.class);
	
	@EJB
	private PortalUserManagerRemote portalUserManager;

	@GET
	@Path("/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("userName") String userName) {
		try {
			return Response.ok(new PortalResponse(this.portalUserManager.login(userName))).build();
		} catch (Exception e) {
			PortalUserService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, PortalUserDTO userDTO) {
		try {
			return Response.ok(new PortalResponse(this.portalUserManager.update(id, userDTO))).build();
		} catch (Exception e) {
			PortalUserService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
}
