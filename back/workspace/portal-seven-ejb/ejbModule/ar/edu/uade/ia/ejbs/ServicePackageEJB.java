package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.business.ServicePackage;

/**
 * Session Bean implementation class ServicePackageEJB
 */
@Stateless
@LocalBean
public class ServicePackageEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ServicePackageEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public ServicePackage getByName(String name) throws Exception {
		try {
			Query query = this.em.createQuery("FROM ServicePackage WHERE name = :name");
			query.setParameter("name", name);
			return (ServicePackage) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
    }

}
