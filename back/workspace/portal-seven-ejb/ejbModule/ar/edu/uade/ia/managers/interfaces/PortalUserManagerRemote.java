package ar.edu.uade.ia.managers.interfaces;

import javax.ejb.Remote;

import ar.edu.uade.ia.commons.dtos.PortalUserDTO;

@Remote
public interface PortalUserManagerRemote {

	PortalUserDTO login(String userName) throws Exception;
	
	PortalUserDTO update (Integer id, PortalUserDTO userDTO) throws Exception;
	
	PortalUserDTO addImage(Integer userId, byte[] bytes) throws Exception;
}
