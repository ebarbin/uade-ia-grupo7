package ar.edu.uade.ia.managers.interfaces;

import javax.ejb.Remote;

import ar.edu.uade.ia.commons.dtos.UserDTO;

@Remote
public interface UserManagerRemote {

	boolean login(UserDTO userDTO) throws Exception;
}
