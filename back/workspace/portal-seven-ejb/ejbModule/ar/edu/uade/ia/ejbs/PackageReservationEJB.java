package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.PackageReservation;

/**
 * Session Bean implementation class PackageReservationEJB
 */
@Stateless
@LocalBean
public class PackageReservationEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PackageReservationEJB() {}

    @SuppressWarnings("unchecked")
	public List<PackageReservation> getReservations(Integer portalUserId) throws Exception {
		Query query = this.em.createQuery("FROM PackageReservation WHERE portalUser.id = :portalUserId");
		query.setParameter("portalUserId", portalUserId);
		return (List<PackageReservation>) query.getResultList();
	}
}
