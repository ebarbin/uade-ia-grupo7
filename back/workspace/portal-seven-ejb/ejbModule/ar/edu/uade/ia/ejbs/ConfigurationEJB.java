package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.common.enums.ConfigurationType;
import ar.edu.uade.ia.entities.Configuration;

/**
 * Session Bean implementation class ConfigurationEJB
 */
@Stateless
@LocalBean
public class ConfigurationEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ConfigurationEJB() {}

    @SuppressWarnings("unchecked")
	public List<Configuration> getAll() throws Exception {
		try {
			Query query = this.em.createQuery("FROM Configuration");
			return (List<Configuration>) query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
    }

	public Configuration getByKeyType(ConfigurationType type) throws Exception {
		try {
		Query query = this.em.createQuery("FROM Configuration WHERE type = :type");
		query.setParameter("type", type.name());
		return (Configuration) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	public void update(Configuration configuration) throws Exception {
		this.em.merge(configuration);
	}
	
	public void save(Configuration configuration) throws Exception {
		this.em.persist(configuration);
	}
}
