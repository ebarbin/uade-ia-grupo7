package ar.edu.uade.ia.managers.interfaces;

import javax.ejb.Local;

import ar.edu.uade.ia.commons.dtos.PortalUserDTO;

@Local
public interface PortalUserManagerLocal {
	
	PortalUserDTO login(String userName) throws Exception;
	
	PortalUserDTO update (Integer id, PortalUserDTO userDTO) throws Exception;

	PortalUserDTO addImage(Integer userId, byte[] bytes) throws Exception;
}
