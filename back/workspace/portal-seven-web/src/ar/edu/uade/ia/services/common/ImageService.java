package ar.edu.uade.ia.services.common;

import java.io.InputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import ar.edu.uade.ia.dtos.ImageDTO;
import ar.edu.uade.ia.dtos.PortalUserDTO;
import ar.edu.uade.ia.managers.interfaces.common.ImageManagerRemote;
import ar.edu.uade.ia.managers.interfaces.common.PortalUserManagerRemote;
import ar.edu.uade.ia.services.response.PortalResponse;

@Path("/image")
@Stateless
public class ImageService {

	@EJB
	private ImageManagerRemote imageManager;

	@EJB
	private PortalUserManagerRemote userManager;
	
	@GET
	@Path("/{id}")
	@Produces({ "image/jpg" })
	public Response get(@PathParam("id") Integer id) {
		try {
			ImageDTO imageDTO = this.imageManager.getById(id);
			return Response.ok(imageDTO.getData()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}

	@PUT
	@Path("/{userId}")
	@Consumes("multipart/form-data")
	public Response save(@PathParam("userId") Integer userId,
			MultipartFormDataInput input) {
		try {
			List<InputPart> inputParts = input.getFormDataMap().get("uploadFile");
			InputStream inputStream = inputParts.get(0).getBody(InputStream.class, null);
			byte[] bytes = IOUtils.toByteArray(inputStream);
			PortalUserDTO userDTO = this.userManager.addImage(userId, bytes);
			return Response.ok(new PortalResponse(userDTO)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(new PortalResponse(e.getMessage())).build();
		}
	}
}
