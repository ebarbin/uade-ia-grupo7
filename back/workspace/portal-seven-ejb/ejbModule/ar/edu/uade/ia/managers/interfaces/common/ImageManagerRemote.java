package ar.edu.uade.ia.managers.interfaces;

import javax.ejb.Remote;

import ar.edu.uade.ia.commons.dtos.ImageDTO;

@Remote
public interface ImageManagerRemote {

	ImageDTO getById(Integer id) throws Exception;
	
	ImageDTO save(Integer userId, byte[] bytes) throws Exception;
}
