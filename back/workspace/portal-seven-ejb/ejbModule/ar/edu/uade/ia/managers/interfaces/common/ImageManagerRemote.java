package ar.edu.uade.ia.managers.interfaces.common;

import javax.ejb.Remote;

import ar.edu.uade.ia.dtos.ImageDTO;

@Remote
public interface ImageManagerRemote {

	ImageDTO getById(Integer id) throws Exception;
	
	ImageDTO save(Integer userId, byte[] bytes) throws Exception;
}
