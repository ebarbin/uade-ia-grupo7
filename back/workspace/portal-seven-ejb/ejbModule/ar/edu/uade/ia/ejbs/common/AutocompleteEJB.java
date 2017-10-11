package ar.edu.uade.ia.ejbs.common;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.Destination;
import ar.edu.uade.ia.entities.business.Hotel;

@Stateless
@LocalBean
public class AutocompleteEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Hotel> hotelQuery(String value, Integer limit) throws Exception {
		Query query = this.em.createQuery("FROM Hotel WHERE lower(name) LIKE lower(:value)");
		query.setParameter("value", '%'+value+'%');
		//	query.setMaxResults(limit);
		return (List<Hotel>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Destination> destinationQuery(String value, Integer limit) throws Exception {
		Query query = this.em.createQuery("FROM Destination WHERE lower(name) LIKE lower(:value)");
		query.setParameter("value", '%'+value+'%');
		//	query.setMaxResults(limit);
		return (List<Destination>) query.getResultList();
	}
}
