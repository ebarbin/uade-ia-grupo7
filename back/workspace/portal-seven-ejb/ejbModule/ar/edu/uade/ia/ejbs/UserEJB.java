package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.ejbs.entities.bussiness.User;

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

	public User update(User user) throws Exception {
		return this.em.merge(user);
	}

	public User getById(Integer id) throws Exception {
		User user = this.em.find(User.class, id);
		if (user == null) {
			throw new Exception("Usuario inexistente.");
		}
		return user;
	}

}
