package ar.edu.uade.ia.managers.interfaces;

import javax.ejb.Local;

import ar.edu.uade.ia.commons.dtos.UserDTO;

@Local
public interface UserManagerLocal {
	
	UserDTO login(String userName) throws Exception;
}
