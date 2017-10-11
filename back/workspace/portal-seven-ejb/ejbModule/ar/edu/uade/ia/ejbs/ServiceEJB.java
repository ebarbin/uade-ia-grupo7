package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.Service;

/**
 * Session Bean implementation class ServiceEJB
 */
@Stateless
@LocalBean
public class ServiceEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ServiceEJB() {}
    
    public Service getByName(String name) throws Exception {
		try {
			Query query = this.em.createQuery("FROM Service WHERE name = :name");
			query.setParameter("name", name);
			return (Service) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }

}
