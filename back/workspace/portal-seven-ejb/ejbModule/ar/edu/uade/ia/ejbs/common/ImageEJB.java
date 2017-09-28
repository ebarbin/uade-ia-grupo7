package ar.edu.uade.ia.ejbs.common;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import ar.edu.uade.ia.ejbs.entities.bussiness.Image;

/**
 * Session Bean implementation class ImageEJB
 */
@Stateless
@LocalBean
public class ImageEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;

	/**
	 * Default constructor.
	 */
	public ImageEJB() {
	}

	public Image getById(Integer id) throws Exception {
		try {
			Image image = this.em.find(Image.class, id);
			if (image == null) {
				throw new NoResultException();
			} else {
				return image;
			}
		} catch (NoResultException nre) {
			throw new Exception("Imagen inexistente.");
		}
	}

	public Image create(Image image) {
		this.em.persist(image);
		return image;
	}

}
