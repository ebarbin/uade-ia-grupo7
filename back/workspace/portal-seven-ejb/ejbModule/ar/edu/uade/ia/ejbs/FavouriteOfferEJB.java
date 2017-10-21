package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.entities.FavouriteHotelOffer;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;

/**
 * Session Bean implementation class FavouriteOfferEJB
 */
@Stateless
@LocalBean
public class FavouriteOfferEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public FavouriteOfferEJB() {}

    public FavouriteHotelOffer getFavouriteHotelOffer(Integer hotelOfferId, Integer userId) throws Exception {
		try {
			Query query = this.em.createQuery("FROM FavouriteHotelOffer WHERE hotelOffer.id = :hoId AND portalUser.id = :userId");
			query.setParameter("hoId", hotelOfferId);
			query.setParameter("userId", userId);
			return (FavouriteHotelOffer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
    
    public Boolean isFavouriteHotel(Integer hotelOfferId, Integer userId) throws Exception {
    	return this.getFavouriteHotelOffer(hotelOfferId, userId) != null;
    }

	public void removeFavouriteHotel(FavouriteHotelOffer fho) {
		this.em.remove(fho);
	}

	public void markFavouriteHotel(HotelOffer ho, PortalUser user, HotelOfferRequestDTO filter) {
		FavouriteHotelOffer fho = new FavouriteHotelOffer();
		fho.setOfferEnd(filter.getToDate());
		fho.setOfferStart(filter.getFromDate());
		fho.setRoomQuantity(filter.getRoomQuantity());
		fho.setHotelOffer(ho);
		fho.setPortalUser(user);
		
		this.em.persist(fho);
	}

	@SuppressWarnings("unchecked")
	public List<FavouriteHotelOffer> getFavouriteHotelOffers(Integer portalUserId) throws Exception {
		Query query = this.em.createQuery("FROM FavouriteHotelOffer WHERE portalUser.id = :portalUserId");
		query.setParameter("portalUserId", portalUserId);
		return (List<FavouriteHotelOffer>) query.getResultList();
	}
}
