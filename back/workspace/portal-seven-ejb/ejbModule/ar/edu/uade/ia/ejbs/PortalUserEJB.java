package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.ejbs.entities.bussiness.PortalUser;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
@LocalBean
public class PortalUserEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;

	/**
	 * Default constructor.
	 */
	public PortalUserEJB() {
	}

	public PortalUser login(String userName) throws Exception {
		try {
			Query query = this.em.createQuery("FROM PortalUser WHERE userName = :userName");
			query.setParameter("userName", userName);
			return (PortalUser) query.getSingleResult();
		} catch (NoResultException nre) {
			throw new Exception("Usuario inexistente.");
		}
	}

	public PortalUser update(PortalUser user) throws Exception {
		return this.em.merge(user);
	}

	public PortalUser getById(Integer id) throws Exception {
		PortalUser user = this.em.find(PortalUser.class, id);
		if (user == null) {
			throw new Exception("Usuario inexistente.");
		}
		return user;
	}

}