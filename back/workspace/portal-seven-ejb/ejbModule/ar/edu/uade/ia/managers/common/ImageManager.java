package ar.edu.uade.ia.managers.common;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.ejbs.common.ImageEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.Image;

/**
 * Session Bean implementation class ImageManager
 */
@Stateless
@LocalBean
public class ImageManager {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private ImageEJB imageEJB;

	@EJB
	private PortalUserEJB userEJB;

	/**
	 * Default constructor.
	 */
	public ImageManager() {
	}

	public ImageDTO getById(Integer id) throws Exception {
		Image image = this.imageEJB.getById(id);
		return ImageManager.mapper.map(image, ImageDTO.class);
	}

	public ImageDTO save(Integer userId, byte[] bytes) throws Exception {
		PortalUser user = this.userEJB.getById(userId);
		if (user.getImage() != null) {
			user.getImage().setData(bytes);
		} else {
			Image image = new Image();
			image.setData(bytes);
			user.setImage(image);
		}
		user = this.userEJB.update(user);
		return ImageManager.mapper.map(user.getImage(), ImageDTO.class);
	}

}
