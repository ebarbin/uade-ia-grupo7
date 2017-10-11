package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.Address;

/**
 * Session Bean implementation class AddressEJB
 */
@Stateless
@LocalBean
public class AddressEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AddressEJB() {}
    
    public Address getByLatAndLng(float lat, float lng) throws Exception {
		try {
			Query query = this.em.createQuery("FROM Address WHERE lat = :lat AND lng = :lng");
			query.setParameter("lat", lat);
			query.setParameter("lng", lng);
			return (Address) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }

}
