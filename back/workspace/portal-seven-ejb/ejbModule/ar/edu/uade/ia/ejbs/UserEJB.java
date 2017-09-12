package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.ejbs.entities.User;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
@LocalBean
public class UserEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public UserEJB() {}
    
    public boolean login(User user) throws Exception {
    	Query query = this.em.createQuery("FROM User WHERE username = :username AND password = :password");
    	query.setParameter("username", user.getUsername());
    	query.setParameter("password", user.getPassword());
    	return query.getResultList().size() > 0;
    }

}
