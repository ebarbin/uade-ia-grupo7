package ar.edu.uade.ia.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.commons.ListMapperDecorator;
import ar.edu.uade.ia.commons.dtos.UserDTO;
import ar.edu.uade.ia.ejbs.UserEJB;
import ar.edu.uade.ia.ejbs.entities.User;
import ar.edu.uade.ia.managers.interfaces.UserManagerLocal;
import ar.edu.uade.ia.managers.interfaces.UserManagerRemote;

/**
 * Session Bean implementation class UserManager
 */
@Stateless
@LocalBean
public class UserManager implements UserManagerRemote, UserManagerLocal {

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	private ListMapperDecorator listMapper = new ListMapperDecorator(mapper);
	
	@EJB
	private UserEJB userEJB;
	
    /**
     * Default constructor. 
     */
    public UserManager() {}

	@Override
	public boolean login(UserDTO userDTO) throws Exception {
		User user = this.mapper.map(userDTO, User.class);
		return this.userEJB.login(user);
	}

}
