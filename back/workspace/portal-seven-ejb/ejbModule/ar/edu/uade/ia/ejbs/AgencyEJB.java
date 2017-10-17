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

    public Agency getByCode(String code) throws Exception {
		try {
			Query query = this.em.createQuery("FROM Agency WHERE code = :code");
			query.setParameter("code", code);
			return (Agency) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }
}
