package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.edu.uade.ia.entities.business.Quota;

/**
 * Session Bean implementation class QuotaEJB
 */
@Stateless
@LocalBean
public class QuotaEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public QuotaEJB() {
        // TODO Auto-generated constructor stub
    }

    public void add(Quota quota) throws Exception {
    	this.em.persist(quota);
    }
}
