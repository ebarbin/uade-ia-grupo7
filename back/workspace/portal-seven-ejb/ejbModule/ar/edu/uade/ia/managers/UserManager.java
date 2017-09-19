package ar.edu.uade.ia.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.commons.dtos.UserDTO;
import ar.edu.uade.ia.ejbs.UserEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.Image;
import ar.edu.uade.ia.ejbs.entities.bussiness.User;
import ar.edu.uade.ia.managers.interfaces.UserManagerLocal;
import ar.edu.uade.ia.managers.interfaces.UserManagerRemote;

/**
 * Session Bean implementation class UserManager
 */
@Stateless
@LocalBean
public class UserManager implements UserManagerRemote, UserManagerLocal {

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	// private ListMapperDecorator listMapper = new ListMapperDecorator(mapper);

	@EJB
	private UserEJB userEJB;

	/**
	 * Default constructor.
	 */
	public UserManager() {
	}

	@Override
	public UserDTO login(String userName) throws Exception {
		User user = this.userEJB.login(userName);
		UserDTO userDTO = this.mapper.map(user, UserDTO.class);
		if (userDTO.getImage() != null)
			userDTO.getImage().setData(null);
		return userDTO;
	}

	@Override
	public UserDTO update(Integer id, UserDTO userDTO) throws Exception {
		User user = this.userEJB.getById(id);
		user.setEmail(userDTO.getEmail());
		user.setFirstName(userDTO.getFirstName());
		user.setSureName(userDTO.getSureName());
		user = this.userEJB.update(user);
		return this.mapper.map(user, UserDTO.class);
	}

	@Override
	public UserDTO addImage(Integer userId, byte[] bytes) throws Exception {
		User user = this.userEJB.getById(userId);

		Image image = new Image();
		image.setData(bytes);
		user.setImage(image);

		user = this.userEJB.update(user);
		return this.mapper.map(user, UserDTO.class);
	}

}
