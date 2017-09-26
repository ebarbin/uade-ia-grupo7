package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.commons.dtos.requests.HotelOfferRequestDTO;
import ar.edu.uade.ia.ejbs.entities.bussiness.HotelOffer;

/**
 * Session Bean implementation class HotelOfferEJB
 */
@Stateless
@LocalBean
public class HotelOfferEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public HotelOfferEJB() {}

	@SuppressWarnings("unchecked")
	public List<HotelOffer> search(HotelOfferRequestDTO hotelOfferRequest) {
		Query query = this.em.createQuery("FROM HotelOffer");
		return query.getResultList();
	}

	public HotelOffer getDetail(Integer id) {
		return this.em.find(HotelOffer.class, id);
	}

}
