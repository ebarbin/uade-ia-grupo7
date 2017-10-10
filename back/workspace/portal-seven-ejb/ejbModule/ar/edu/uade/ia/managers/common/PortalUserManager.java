package ar.edu.uade.ia.managers.common;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.common.dtos.PortalUserDTO;
import ar.edu.uade.ia.ejbs.common.ImageEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PortalUser;

/**
 * Session Bean implementation class UserManager
 */
@Stateless
@LocalBean
public class PortalUserManager {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private PortalUserEJB userEJB;

	@EJB
	private ImageEJB imageEJB;

	/**
	 * Default constructor.
	 */
	public PortalUserManager() {
	}

	public PortalUserDTO login(String userName) throws Exception {
		PortalUser user = this.userEJB.login(userName);
		PortalUserDTO userDTO = PortalUserManager.mapper.map(user, PortalUserDTO.class);
		if (userDTO.getImage() != null)
			userDTO.getImage().setData(null);
		return userDTO;
	}

	public PortalUserDTO update(Integer id, PortalUserDTO portalUserDTO) throws Exception {
		PortalUser portalUser = this.userEJB.getById(id);
		portalUser.setEmail(portalUserDTO.getEmail());
		portalUser.setFirstName(portalUserDTO.getFirstName());
		portalUser.setSureName(portalUserDTO.getSureName());
		portalUser = this.userEJB.update(portalUser);

		return PortalUserManager.mapper.map(portalUser, PortalUserDTO.class);
	}

	public PortalUserDTO addImage(Integer userId, byte[] bytes) throws Exception {
		PortalUser user = this.userEJB.getById(userId);

		Image image = new Image();
		image.setData(bytes);
		this.imageEJB.create(image);
		user.setImage(image);
		user = this.userEJB.update(user);

		return PortalUserManager.mapper.map(user, PortalUserDTO.class);
	}

	public PortalUserDTO create(PortalUserDTO userDTO) throws Exception {
		if (this.userEJB.getByUsername(userDTO.getUserName()) == null) {
			PortalUser portalUser = PortalUserManager.mapper.map(userDTO, PortalUser.class);
			this.userEJB.create(portalUser);
			return PortalUserManager.mapper.map(portalUser, PortalUserDTO.class);
		} else {
			throw new Exception("Nombre de usuario ya existente.");
		}
	}

}
