package ar.edu.uade.ia.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.uade.ia.commons.dtos.UserDTO;
import ar.edu.uade.ia.managers.interfaces.UserManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/user")
@Stateless
public class UserService {

	@EJB
	private UserManagerRemote userManagerRemote;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserDTO userDTO) {
		try {
			if (this.userManagerRemote.login(userDTO)) {
				return Response.ok(new PortalResponse()).build();
			} else {
				return Response.ok(new PortalResponse("Usuario/Contraseña inválidos.")).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		try {
			return Response.ok(new PortalResponse()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
}
