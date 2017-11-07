package ar.edu.uade.ia.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.dtos.ConfigurationDTO;
import ar.edu.uade.ia.managers.ConfigurationManager;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/configuration")
@Stateless
public class ConfigurationService {

	private static Logger LOGGER = Logger.getLogger(ConfigurationService.class);
	
	@EJB
	private ConfigurationManager configurationManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConfiguration() {
		try {
			ConfigurationDTO config = this.configurationManager.getConfiguration();
			return Response.ok(new PortalResponse(config)).build();
		} catch (Exception e) {
			ConfigurationService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveConfiguration(ConfigurationDTO config) {
		try {
			this.configurationManager.saveConfiguration(config);
			return Response.ok(new PortalResponse()).build();
		} catch (Exception e) {
			ConfigurationService.LOGGER.error(e.getMessage(), e);
			return Response.ok(new PortalResponse(e.getMessage())).build();
		}
	}
}
