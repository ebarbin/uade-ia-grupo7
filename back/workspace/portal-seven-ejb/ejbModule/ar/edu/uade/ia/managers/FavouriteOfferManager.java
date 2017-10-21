package ar.edu.uade.ia.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.ejbs.FavouriteOfferEJB;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.FavouriteHotelOffer;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;

/**
 * Session Bean implementation class FavouriteOfferManager
 */
@Stateless
@LocalBean
public class FavouriteOfferManager {

	@EJB
	private HotelOfferEJB hotelOfferEJB;
	
	@EJB
	private PortalUserEJB portalUserEJB;
	
	@EJB
	private FavouriteOfferEJB favoriteOfferEJB;
	
    /**
     * Default constructor. 
     */
    public FavouriteOfferManager() {}
    
	public Boolean markHotelFavourite(Integer offerHotelId, Integer portalUserId, HotelOfferRequestDTO filter) throws Exception {
		FavouriteHotelOffer fho = this.favoriteOfferEJB.getFavouriteHotelOffer(offerHotelId, portalUserId);
		if (fho != null) {
			this.favoriteOfferEJB.removeFavouriteHotel(fho);
			return Boolean.FALSE;
		} else {
			HotelOffer ho = this.hotelOfferEJB.getDetail(offerHotelId);
			PortalUser user = this.portalUserEJB.getById(portalUserId);
			this.favoriteOfferEJB.markFavouriteHotel(ho, user, filter);
			return Boolean.TRUE;
		}
	}
}
