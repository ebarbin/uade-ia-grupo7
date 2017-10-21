package ar.edu.uade.ia.ejbs;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.entities.FavouriteHotelOffer;
import ar.edu.uade.ia.entities.FavouritePackageOffer;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.PackageOffer;

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

	public FavouritePackageOffer getFavouritePackageOffer(Integer offerPackageId, Integer portalUserId) throws Exception {
		try {
			Query query = this.em.createQuery("FROM FavouritePackageOffer WHERE packageOffer.id = :poId AND portalUser.id = :userId");
			query.setParameter("poId", offerPackageId);
			query.setParameter("userId", portalUserId);
			return (FavouritePackageOffer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public void removeFavouritePackage(FavouritePackageOffer pho) {
		this.em.remove(pho);
	}

	public void markFavouritePackage(PackageOffer po, PortalUser user, PackageOfferRequestDTO filter) {
		FavouritePackageOffer fho = new FavouritePackageOffer();
		fho.setQuantityPeople(filter.getQuantityPeople());
		fho.setPackageOffer(po);
		fho.setPortalUser(user);
		
		this.em.persist(fho);
	}

	public Boolean isFavouritePackage(Integer packageOfferId, Integer portalUserId) throws Exception {
		return this.getFavouritePackageOffer(packageOfferId, portalUserId) != null;
	}
}
