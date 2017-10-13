package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.Hotel;

/**
 * Session Bean implementation class HotelEJB
 */
@Stateless
@LocalBean
public class HotelEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;

	/**
	 * Default constructor.
	 */
	public HotelEJB() { }

	public Hotel getByCode(String code) throws Exception {
		try {
			Query query = this.em.createQuery("FROM Hotel WHERE code = :code");
			query.setParameter("code", code);
			return (Hotel) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
