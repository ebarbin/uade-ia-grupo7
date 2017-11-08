package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.Agency;

/**
 * Session Bean implementation class AgencyEJB
 */
@Stateless
@LocalBean
public class AgencyEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AgencyEJB() { }

    public Agency getByProviderCode(Integer providerCode) throws Exception {
		try {
			Query query = this.em.createQuery("FROM Agency WHERE providerCode = :providerCode");
			query.setParameter("providerCode", providerCode);
			return (Agency) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }
}
