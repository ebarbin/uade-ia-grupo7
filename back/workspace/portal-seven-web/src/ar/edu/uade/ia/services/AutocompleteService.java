package ar.edu.uade.ia.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.edu.uade.ia.managers.interfaces.AutocompleteManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/autocomplete")
@Stateless
public class AutocompleteService {

	@EJB
	private AutocompleteManagerRemote autocompleteManager;
	
	@GET
	@Path("/hotel/{value}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("value") String value, @PathParam("limit") Integer limit) {
		try {
		    return Response.ok(new PortalResponse(this.autocompleteManager.queryHotels(value, limit))).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
}
