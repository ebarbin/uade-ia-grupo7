package ar.edu.uade.ia.ejbs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.entities.business.HotelOffer;

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
	public List<HotelOffer> search(HotelOfferRequestDTO request) throws Exception {
		this.fixDatesForQuery(request);
		
		Integer idHotel = request.getHotel() != null ? request.getHotel().getId() : null;
		Date dateFrom = request.getFromDate();
		Date dateTo = request.getToDate();
		Integer roomQty = request.getRoomQuantity();
		Integer peoplePerRoom = request.getPeoplePerRoom();
		Integer difDates = 0;
		
		// Quota cuyo dia este dentro del rango del filtro
		// Quota cuyo hotel de su oferta coincida con el hotel del filtro
		// Quota cuyo cupo diario supere o iguale la cantidad de habitaciones del filtro
		// Quota cuya capacidad de personas de habitacion de la oferta sea superior o
		// igual a cantidad de personas del filtro
		// Debe haber al menos 1 Quota por d�a en el per�odo del filtro
		
		StringBuffer queryBuilder = new StringBuffer("select ofe");
		queryBuilder.append(" from HotelOffer as ofe");
		queryBuilder.append(" inner join ofe.hotel ho");
		queryBuilder.append(" inner join ofe.room ro");
		queryBuilder.append(" where 1 = 1");
		queryBuilder.append(" and ( select count(quo)");
		queryBuilder.append("		from Quota as quo ");
		queryBuilder.append("		where quo.offer.id = ofe.id");
		
		if (dateFrom != null && dateTo != null) {
			queryBuilder.append(" 	and quo.quotaDate between :dateFrom and :dateTo");
//			queryBuilder.append(" 	and quo.quotaDate >= :dateFrom");
//			queryBuilder.append(" 	and quo.quotaDate <= :dateTo");
			difDates = (int) ((dateTo.getTime()-dateFrom.getTime())/86400000);
			difDates += 1;
		}
		if (roomQty != null)
			queryBuilder.append(" 	and quo.availableQuota >= :roomQty");
		
		queryBuilder.append("     ) = :difDates");
		
		if (idHotel != null)
			queryBuilder.append(" and ho.id = :idHotel");
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
		}
		query.setParameter("difDates", difDates.longValue());

		return query.getResultList();
	}

	private void fixDatesForQuery(HotelOfferRequestDTO hor) {
		Calendar from = Calendar.getInstance();
		from.setTime(hor.getFromDate());
		from.set(Calendar.HOUR_OF_DAY,0);
		from.set(Calendar.MINUTE,0);
		from.set(Calendar.SECOND,0);
		from.set(Calendar.MILLISECOND,0);
		hor.setFromDate(from.getTime());
		
		Calendar to = Calendar.getInstance();
		to.setTime(hor.getToDate());
		to.set(Calendar.HOUR_OF_DAY, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		to.set(Calendar.MILLISECOND, 999);
		hor.setToDate(to.getTime());
	}
	
	public Boolean hasQuota(Integer hotelOfferId, HotelOfferRequestDTO filter) throws Exception {
		
		Date dateFrom = filter.getFromDate();
		Date dateTo = filter.getToDate();
		Integer roomQty = filter.getRoomQuantity();
		Integer difDates = (int) ((dateTo.getTime()-dateFrom.getTime())/86400000);
		difDates += 1;
		int value = 0;
		
		StringBuffer queryBuilder = new StringBuffer("select ofe");
		queryBuilder.append(" from HotelOffer as ofe");
		queryBuilder.append(" where 1 = 1");
		queryBuilder.append(" and ( select count(quo)");
		queryBuilder.append("		from Quota as quo ");
		queryBuilder.append("		where quo.offer.id = ofe.id");		
		queryBuilder.append(" 		and quo.quotaDate between :dateFrom and :dateTo");
		queryBuilder.append(" 		and quo.availableQuota >= :roomQty");		
		queryBuilder.append("     ) = :difDates");
		queryBuilder.append(" and ofe.id = :hotelOfferId"); 
				
		Query query = this.em.createQuery(queryBuilder.toString());
		
		query.setParameter("hotelOfferId", hotelOfferId);
		query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
		query.setParameter("dateTo", dateTo, TemporalType.DATE);			
		query.setParameter("roomQty", roomQty);
		query.setParameter("difDates", difDates.longValue());

		value = query.getResultList().size();
		
		return (value >0);
	}
	
	public HotelOffer getDetail(Integer id) {
		return this.em.find(HotelOffer.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<HotelOffer> searchOtherRooms(Integer roomId, HotelOfferRequestDTO request) {
		Integer hotelId = request.getHotel().getId();
		Date dateFrom = request.getFromDate();
		Date dateTo = request.getToDate();
		
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
		queryBuilder.append(" and ho.id = :hotelId");
		queryBuilder.append(" and ro.id <> :roomId");
		
		if (dateFrom != null && dateTo != null)
			queryBuilder.append(" and quo.quotaDate between :dateFrom and :dateTo");
		if (dateFrom != null && dateTo == null)
			queryBuilder.append(" and quo.quotaDate >= :dateFrom");
		if (dateFrom == null && dateTo != null)
			queryBuilder.append(" and quo.quotaDate <= :dateTo");

		Query query = this.em.createQuery(queryBuilder.toString());
		if (hotelId != null)
			query.setParameter("hotelId", hotelId);
		if (roomId != null)
			query.setParameter("roomId", roomId);
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
