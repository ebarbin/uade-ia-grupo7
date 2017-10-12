package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.ServiceRoom;

/**
 * Session Bean implementation class ServiceRoomEJB
 */
@Stateless
@LocalBean
public class ServiceRoomEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ServiceRoomEJB() {}

    public ServiceRoom getByName(String name) throws Exception {
		try {
			Query query = this.em.createQuery("FROM ServiceRoom WHERE name = :name");
			query.setParameter("name", name);
			return (ServiceRoom) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }
}
