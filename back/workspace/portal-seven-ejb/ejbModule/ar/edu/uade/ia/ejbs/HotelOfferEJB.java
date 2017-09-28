package ar.edu.uade.ia.ejbs;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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
		
		// Quota cuyo día este dentro del rango del filtro
		// Quota cuyo hotel de su oferta coincida con el hotel del filtro
		// Quota cuyo cupo diario supere o iguale la cantidad de habitaciones del filtro
		// Quota cuya capacidad de personas de habitación de la oferta sea superior o igual a cantidad de personas del filtro
		String sql = "select ofe "
				+ " from Quota as quo "
				+ " inner join quo.offer as ofe "
				+ " inner join ofe.hotel ho "
				+ " inner join ofe.room ro "
				+ " where ho.id = :idHotel"
				+ " and quo.quotaDate between :dateFrom and :dateTo"
				+ " and quo.availableQuota >= :roomQty"
				+ " and ro.capacity >= :peoplePerRoom";
		
		Integer idHotel = hotelOfferRequest.getHotel().getId();
		Date dateFrom = hotelOfferRequest.getFromDate();
		Date dateTo = hotelOfferRequest.getToDate();
		Integer roomQty = hotelOfferRequest.getRoomQuantity();
		Integer peoplePerRoom = hotelOfferRequest.getPeoplePerRoom();

		Query query = this.em.createQuery(sql); 
		query.setParameter("idHotel", idHotel);
		query. setParameter("roomQty", roomQty);
		query.setParameter("peoplePerRoom", peoplePerRoom);
		query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
		query.setParameter("dateTo", dateTo, TemporalType.DATE);
		
		return query.getResultList();
	}

	public HotelOffer getDetail(Integer id) {
		return this.em.find(HotelOffer.class, id);
	}

}
