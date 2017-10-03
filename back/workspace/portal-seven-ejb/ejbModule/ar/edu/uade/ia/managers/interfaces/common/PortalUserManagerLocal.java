package ar.edu.uade.ia.managers.interfaces.common;

import javax.ejb.Local;

import ar.edu.uade.ia.common.dtos.PortalUserDTO;

@Local
public interface PortalUserManagerLocal {
	
	PortalUserDTO login(String userName) throws Exception;
	
	PortalUserDTO update (Integer id, PortalUserDTO userDTO) throws Exception;

	PortalUserDTO addImage(Integer userId, byte[] bytes) throws Exception;
	
	PortalUserDTO create(PortalUserDTO userDTO) throws Exception;
}
