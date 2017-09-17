package ar.edu.uade.ia.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.commons.dtos.ImageDTO;
import ar.edu.uade.ia.ejbs.ImageEJB;
import ar.edu.uade.ia.ejbs.UserEJB;
import ar.edu.uade.ia.ejbs.entities.Image;
import ar.edu.uade.ia.ejbs.entities.User;
import ar.edu.uade.ia.managers.interfaces.ImageManagerLocal;
import ar.edu.uade.ia.managers.interfaces.ImageManagerRemote;

/**
 * Session Bean implementation class ImageManager
 */
@Stateless
@LocalBean
public class ImageManager implements ImageManagerRemote, ImageManagerLocal {

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private ImageEJB imageEJB;

	@EJB
	private UserEJB userEJB;

	/**
	 * Default constructor.
	 */
	public ImageManager() {
	}

	@Override
	public ImageDTO getById(Integer id) throws Exception {
		Image image = this.imageEJB.getById(id);
		return this.mapper.map(image, ImageDTO.class);
	}

	@Override
	public ImageDTO save(Integer userId, byte[] bytes) throws Exception {
		User user = this.userEJB.getById(userId);
		if (user.getImage() != null) {
			user.getImage().setData(bytes);
		} else {
			Image image = new Image();
			image.setData(bytes);
			user.setImage(image);
		}
		user = this.userEJB.update(user);
		return this.mapper.map(user.getImage(), ImageDTO.class);
	}

}
