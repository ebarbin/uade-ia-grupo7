package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.PaymentMethod;

/**
 * Session Bean implementation class PaymentMethodEJB
 */
@Stateless
@LocalBean
public class PaymentMethodEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PaymentMethodEJB() {}
    
    public PaymentMethod getByCode(Integer code) throws Exception {
		try {
			Query query = this.em.createQuery("FROM PaymentMethod WHERE code = :code");
			query.setParameter("code", code);
			return (PaymentMethod) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }

}
