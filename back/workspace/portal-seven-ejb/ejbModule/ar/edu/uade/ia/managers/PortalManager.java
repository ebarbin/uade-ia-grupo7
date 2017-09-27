package ar.edu.uade.ia.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.commons.dtos.PortalUserDTO;
import ar.edu.uade.ia.ejbs.ImageEJB;
import ar.edu.uade.ia.ejbs.PortalUserEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.Image;
import ar.edu.uade.ia.ejbs.entities.bussiness.PortalUser;
import ar.edu.uade.ia.managers.interfaces.PortalUserManagerLocal;
import ar.edu.uade.ia.managers.interfaces.PortalUserManagerRemote;

/**
 * Session Bean implementation class UserManager
 */
@Stateless
@LocalBean
public class PortalManager implements PortalUserManagerRemote, PortalUserManagerLocal {

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	// private ListMapperDecorator listMapper = new ListMapperDecorator(mapper);

	@EJB
	private PortalUserEJB userEJB;

	@EJB
	private ImageEJB imageEJB;

	/**
	 * Default constructor.
	 */
	public PortalManager() {
	}

	@Override
	public PortalUserDTO login(String userName) throws Exception {
		PortalUser user = this.userEJB.login(userName);
		PortalUserDTO userDTO = this.mapper.map(user, PortalUserDTO.class);
		if (userDTO.getImage() != null)
			userDTO.getImage().setData(null);
		return userDTO;
	}

	@Override
	public PortalUserDTO update(Integer id, PortalUserDTO userDTO) throws Exception {
		PortalUser user = this.userEJB.getById(id);
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setSureName(userDTO.getSureName());
		user = this.userEJB.update(user);
		return this.mapper.map(user, PortalUserDTO.class);
	}

	@Override
	public PortalUserDTO addImage(Integer userId, byte[] bytes) throws Exception {
		PortalUser user = this.userEJB.getById(userId);

		Image image = new Image();
		image.setData(bytes);
		this.imageEJB.create(image);
		user.setImage(image);
		user = this.userEJB.update(user);

		return this.mapper.map(user, PortalUserDTO.class);
	}

}
