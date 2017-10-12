package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.ServiceHotel;

/**
 * Session Bean implementation class ServiceEJB
 */
@Stateless
@LocalBean
public class ServiceHotelEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ServiceHotelEJB() {}
    
    public ServiceHotel getByName(String name) throws Exception {
		try {
			Query query = this.em.createQuery("FROM ServiceHotel WHERE name = :name");
			query.setParameter("name", name);
			return (ServiceHotel) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }

}
