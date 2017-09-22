package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.ejbs.entities.bussiness.Hotel;

/**
 * Session Bean implementation class HotelAutocompleteEJB
 */
@Stateless
@LocalBean
public class HotelEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public HotelEJB() {}

	public List<Hotel> autocompleteQuery(String value, Integer limit) {
		Query query = this.em.createQuery("FROM Hotel WHERE lower(name) LIKE lower(:value)");
		query.setParameter("value", '%'+value+'%');
		query.setMaxResults(limit);
		return (List<Hotel>) query.getResultList();
	}

}
