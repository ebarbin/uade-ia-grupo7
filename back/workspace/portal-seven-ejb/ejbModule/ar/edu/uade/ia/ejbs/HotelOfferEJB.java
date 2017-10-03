package ar.edu.uade.ia.ejbs;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import ar.edu.uade.ia.dtos.HotelOfferRequestDTO;
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
	public HotelOfferEJB() { }

	@SuppressWarnings("unchecked")
	public List<HotelOffer> search(HotelOfferRequestDTO request) {

		Integer idHotel = request.getHotel() != null ? request.getHotel().getId() : null;
		Date dateFrom = request.getFromDate();
		Date dateTo = request.getToDate();
		Integer roomQty = request.getRoomQuantity();
		Integer peoplePerRoom = request.getPeoplePerRoom();

		// Quota cuyo dia este dentro del rango del filtro
		// Quota cuyo hotel de su oferta coincida con el hotel del filtro
		// Quota cuyo cupo diario supere o iguale la cantidad de habitaciones del filtro
		// Quota cuya capacidad de personas de habitacion de la oferta sea superior o
		// igual a cantidad de personas del filtro
		StringBuffer queryBuilder = new StringBuffer("select ofe");
		queryBuilder.append(" from Quota as quo");
		queryBuilder.append(" inner join quo.offer as ofe");
		queryBuilder.append(" inner join ofe.hotel ho");
		queryBuilder.append(" inner join ofe.room ro");
		queryBuilder.append(" where 1 = 1");
		
		if (idHotel != null)
			queryBuilder.append(" and ho.id = :idHotel");
		if (dateFrom != null && dateTo != null)
			queryBuilder.append(" and quo.quotaDate between :dateFrom and :dateTo");
		if (dateFrom != null && dateTo == null)
			queryBuilder.append(" and quo.quotaDate >= :dateFrom");
		if (dateFrom == null && dateTo != null)
			queryBuilder.append(" and quo.quotaDate <= :dateTo");
		if (roomQty != null)
			queryBuilder.append(" and quo.availableQuota >= :roomQty");
		if (peoplePerRoom != null)
			queryBuilder.append(" and ro.capacity >= :peoplePerRoom");

		Query query = this.em.createQuery(queryBuilder.toString());
		if (idHotel != null)
			query.setParameter("idHotel", idHotel);
		if (roomQty != null)
			query.setParameter("roomQty", roomQty);
		if (peoplePerRoom != null)
			query.setParameter("peoplePerRoom", peoplePerRoom);
		if (dateFrom != null && dateTo != null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		} else if (dateFrom != null && dateTo == null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
		} else if (dateFrom == null && dateTo != null) {
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		}
		
		return query.getResultList();
	}

	public HotelOffer getDetail(Integer id) {
		return this.em.find(HotelOffer.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<HotelOffer> searchOtherRooms(Date dateFrom, Date dateTo, Integer idHotel, Integer idRoom) {

		
		// Quota cuyo dia este dentro del rango del filtro
		// Quota cuyo hotel de su oferta coincida con el hotel del filtro
		// Quota cuyo cupo diario supere o iguale la cantidad de habitaciones del filtro
		// Quota cuya capacidad de personas de habitacion de la oferta sea superior o
		// igual a cantidad de personas del filtro
		StringBuffer queryBuilder = new StringBuffer("select ofe");
		queryBuilder.append(" from Quota as quo");
		queryBuilder.append(" inner join quo.offer as ofe");
		queryBuilder.append(" inner join ofe.hotel ho");
		queryBuilder.append(" inner join ofe.room ro");
		queryBuilder.append(" where 1 = 1");
		queryBuilder.append(" and ho.id = :idHotel");
		queryBuilder.append(" and ro.id <> :idRoom");
		
		if (dateFrom != null && dateTo != null)
			queryBuilder.append(" and quo.quotaDate between :dateFrom and :dateTo");
		if (dateFrom != null && dateTo == null)
			queryBuilder.append(" and quo.quotaDate >= :dateFrom");
		if (dateFrom == null && dateTo != null)
			queryBuilder.append(" and quo.quotaDate <= :dateTo");

		Query query = this.em.createQuery(queryBuilder.toString());
		if (idHotel != null)
			query.setParameter("idHotel", idHotel);
		if (idRoom != null)
			query.setParameter("idRoom", idRoom);
		if (dateFrom != null && dateTo != null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		} else if (dateFrom != null && dateTo == null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
		} else if (dateFrom == null && dateTo != null) {
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		}

		return query.getResultList();
	}

}
