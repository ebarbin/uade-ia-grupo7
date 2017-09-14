package ar.edu.uade.ia.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.uade.ia.managers.interfaces.UserManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/user")
@Stateless
public class UserService {

	@EJB
	private UserManagerRemote userManager;

	@GET
	@Path("/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("userName") String userName) {
		try {
			return Response.ok(new PortalResponse(this.userManager.login(userName))).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
}
