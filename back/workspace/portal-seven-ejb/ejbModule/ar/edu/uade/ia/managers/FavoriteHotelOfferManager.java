package ar.edu.uade.ia.managers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.ejbs.FavoriteHotelOfferEJB;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.FavoriteHotelOffer;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;

/**
 * Session Bean implementation class FavoriteHotelOfferManager
 */
@Stateless
@LocalBean
public class FavoriteHotelOfferManager {

	@EJB
	private HotelOfferEJB hotelOfferEJB;
	
	@EJB
	private PortalUserEJB portalUserEJB;
	
	@EJB
	private FavoriteHotelOfferEJB favoriteHotelOfferEJB;
	
    /**
     * Default constructor. 
     */
    public FavoriteHotelOfferManager() {}
    
	public Boolean markFavorite(Integer offerHotelId, Integer portalUserId) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(offerHotelId);
		PortalUser user = this.portalUserEJB.getById(portalUserId);
		FavoriteHotelOffer fho = this.favoriteHotelOfferEJB.getFavoriteHotelOffer(ho, user);
		if (fho != null) {
			this.favoriteHotelOfferEJB.removeFavorite(fho);
			return Boolean.FALSE;
		} else {
			this.favoriteHotelOfferEJB.markFavorite(ho, user);
			return Boolean.TRUE;
		}
	}

}
