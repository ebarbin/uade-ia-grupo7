package ar.edu.uade.ia.managers.interfaces.common;

import javax.ejb.Local;

import ar.edu.uade.ia.dtos.ImageDTO;

@Local
public interface ImageManagerLocal {

	ImageDTO getById(Integer id) throws Exception;
	
	ImageDTO save(Integer userId, byte[] bytes) throws Exception;
}
