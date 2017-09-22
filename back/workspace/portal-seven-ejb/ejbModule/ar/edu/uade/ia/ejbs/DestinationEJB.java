package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.ejbs.entities.bussiness.Destination;

/**
 * Session Bean implementation class Destination
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
    
	@SuppressWarnings("unchecked")
	public List<Destination> autocompleteQuery(String value, Integer limit) {
		Query query = this.em.createQuery("FROM Destination WHERE lower(name) LIKE lower(:value)");
		query.setParameter("value", '%'+value+'%');
		query.setMaxResults(limit);
		return (List<Destination>) query.getResultList();
	}

}
