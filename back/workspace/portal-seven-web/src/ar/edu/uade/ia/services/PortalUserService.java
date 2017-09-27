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

import ar.edu.uade.ia.commons.dtos.PortalUserDTO;
import ar.edu.uade.ia.managers.interfaces.PortalUserManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/portal-user")
@Stateless
public class PortalUserService {

	@EJB
	private PortalUserManagerRemote portalUserManager;

	@GET
	@Path("/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("userName") String userName) {
		try {
			return Response.ok(new PortalResponse(this.portalUserManager.login(userName))).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, PortalUserDTO userDTO) {
		try {
			return Response.ok(new PortalResponse(this.portalUserManager.update(id, userDTO))).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
}
