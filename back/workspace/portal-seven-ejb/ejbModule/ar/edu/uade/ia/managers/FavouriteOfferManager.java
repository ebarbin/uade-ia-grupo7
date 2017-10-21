package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.common.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.ejbs.FavouriteOfferEJB;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.FavouriteHotelOffer;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.ServiceHotel;

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

	public List<HotelOfferHeaderDTO> getFavouriteHotelOffers(Integer portalUserId) throws Exception {
		List<FavouriteHotelOffer> hotelOfferFavourites = this.favoriteOfferEJB.getFavouriteHotelOffers(portalUserId);
		return this.convertToListOfHotelOfferHeaderDTO(hotelOfferFavourites);
	}
	
	private List<HotelOfferHeaderDTO> convertToListOfHotelOfferHeaderDTO(List<FavouriteHotelOffer> favourites) throws Exception {
		List<HotelOfferHeaderDTO> results = new ArrayList<HotelOfferHeaderDTO>();

		HotelOfferHeaderDTO headerDTO;
		SimpleNamedDTO namedDTO;
		ImageDTO imageDTO;

		for (FavouriteHotelOffer favourite : favourites) {
			headerDTO = new HotelOfferHeaderDTO();
			
			headerDTO.setId(favourite.getHotelOffer().getId());
			headerDTO.setName(favourite.getHotelOffer().getHotel().getName());
			headerDTO.setDescription(favourite.getHotelOffer().getHotel().getDescription());
			headerDTO.setServices(new ArrayList<SimpleNamedDTO>());
			headerDTO.setFavourite(Boolean.TRUE);
			
			for (ServiceHotel service : favourite.getHotelOffer().getHotel().getServices()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(service.getId());
				namedDTO.setName(service.getName());
				headerDTO.getServices().add(namedDTO);
			}
			headerDTO.setPrice(favourite.getHotelOffer().getPrice());
			headerDTO.setRoomCapacity(favourite.getRoomQuantity());
			headerDTO.setOfferStart(favourite.getOfferStart());
			headerDTO.setOfferEnd(favourite.getOfferEnd());

			headerDTO.setImages(new ArrayList<ImageDTO>());
			for (Image img : favourite.getHotelOffer().getHotel().getImages()) {
				imageDTO = new ImageDTO();
				imageDTO.setId(img.getId());
				headerDTO.getImages().add(imageDTO);
			}
			results.add(headerDTO);
		}
		return results;
	}
}
