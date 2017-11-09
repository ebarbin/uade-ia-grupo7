package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.QuotaReservation;

/**
 * Session Bean implementation class QuotaReservationEJB
 */
@Stateless
@LocalBean
public class QuotaReservationEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public QuotaReservationEJB() {}
    
	@SuppressWarnings("unchecked")
	public List<QuotaReservation> getReservations(Integer portalUserId) throws Exception {
		Query query = this.em.createQuery("FROM QuotaReservation WHERE portalUser.id = :portalUserId");
		query.setParameter("portalUserId", portalUserId);
		return (List<QuotaReservation>) query.getResultList();
	}

}
