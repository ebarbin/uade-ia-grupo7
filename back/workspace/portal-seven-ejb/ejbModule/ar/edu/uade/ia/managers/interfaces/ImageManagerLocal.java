package ar.edu.uade.ia.managers.interfaces;

import javax.ejb.Local;

import ar.edu.uade.ia.commons.dtos.ImageDTO;

@Local
public interface ImageManagerLocal {

	ImageDTO getById(Integer id) throws Exception;
}
