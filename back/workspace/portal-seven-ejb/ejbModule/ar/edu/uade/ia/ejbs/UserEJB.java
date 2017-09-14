package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	public UserEJB() {
	}

	public User login(String userName) throws Exception {
		try {
			Query query = this.em.createQuery("FROM User WHERE userName = :userName");
			query.setParameter("userName", userName);
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			throw new Exception("Usuario inexistente.");
		}
	}

}
