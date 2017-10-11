package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.Destination;

/**
 * Session Bean implementation class DestinationEJB
 */
@Stateless
@LocalBean
public class DestinationEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DestinationEJB() {}

    public Destination getDestinationByName(String name) throws Exception {
		try {
			Query query = this.em.createQuery("FROM Destination WHERE name = :name");
			query.setParameter("name", name);
			return (Destination) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }
}
