package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ar.edu.uade.ia.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.entities.business.PackageOffer;

/**
 * Session Bean implementation class PackageOfferEJB
 */
@Stateless
@LocalBean
public class PackageOfferEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PackageOfferEJB() {}
    
	@SuppressWarnings("unchecked")
	public List<PackageOffer> search(PackageOfferRequestDTO request) {
		return this.em.createQuery("from PackageOffer").getResultList();
	}
	
	public PackageOffer getDetail(Integer id) {
		return this.em.find(PackageOffer.class, id);
	}
}
