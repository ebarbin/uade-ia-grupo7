package ar.edu.uade.ia.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.commons.dtos.ImageDTO;
import ar.edu.uade.ia.ejbs.ImageEJB;
import ar.edu.uade.ia.ejbs.entities.Image;
import ar.edu.uade.ia.managers.interfaces.ImageManagerLocal;
import ar.edu.uade.ia.managers.interfaces.ImageManagerRemote;

/**
 * Session Bean implementation class ImageManager
 */
@Stateless
@LocalBean
public class ImageManager implements ImageManagerRemote, ImageManagerLocal {

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	@EJB
	private ImageEJB photoEJB;
	
    /**
     * Default constructor. 
     */
    public ImageManager() {}

	@Override
	public ImageDTO getById(Integer id) throws Exception {
		Image image = this.photoEJB.getById(id);
		return this.mapper.map(image, ImageDTO.class);
	}

}
