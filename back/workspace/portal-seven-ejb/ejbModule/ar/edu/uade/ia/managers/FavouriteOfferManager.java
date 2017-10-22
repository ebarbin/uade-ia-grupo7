package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.common.dtos.FavoriteOfferDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.ejbs.FavouriteOfferEJB;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.ejbs.PackageOfferEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.FavouriteHotelOffer;
import ar.edu.uade.ia.entities.FavouritePackageOffer;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.entities.business.PaymentMethod;
import ar.edu.uade.ia.entities.business.ServiceHotel;
import ar.edu.uade.ia.entities.business.ServicePackage;

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
	private PackageOfferEJB packageOfferEJB;

	@EJB
	private FavouriteOfferEJB favoriteOfferEJB;

	/**
	 * Default constructor.
	 */
	public FavouriteOfferManager() {
	}

	public Boolean markFavouriteHotel(Integer offerHotelId, Integer portalUserId, HotelOfferRequestDTO filter)
			throws Exception {
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

	public Boolean markFavouritePackage(Integer offerPackageId, Integer portalUserId, PackageOfferRequestDTO filter)
			throws Exception {
		FavouritePackageOffer pho = this.favoriteOfferEJB.getFavouritePackageOffer(offerPackageId, portalUserId);
		if (pho != null) {
			this.favoriteOfferEJB.removeFavouritePackage(pho);
			return Boolean.FALSE;
		} else {
			PackageOffer po = this.packageOfferEJB.getDetail(offerPackageId);
			PortalUser user = this.portalUserEJB.getById(portalUserId);
			this.favoriteOfferEJB.markFavouritePackage(po, user, filter);
			return Boolean.TRUE;
		}
	}

	public List<FavoriteOfferDTO> getFavouriteOffers(Integer portalUserId) throws Exception {
		List<FavouriteHotelOffer> favouritesHotel = this.favoriteOfferEJB.getFavouriteHotelOffers(portalUserId);
		List<FavouritePackageOffer> favouritesPackage = this.favoriteOfferEJB.getFavouritePackageOffers(portalUserId);

		List<FavoriteOfferDTO> results = new ArrayList<FavoriteOfferDTO>();
		FavoriteOfferDTO favouriteOffer;

		SimpleNamedDTO namedDTO;
		ImageDTO imageDTO;
		for (FavouriteHotelOffer favouriteHotel : favouritesHotel) {
			favouriteOffer = new FavoriteOfferDTO();
			favouriteOffer.setId(favouriteHotel.getHotelOffer().getId());
			favouriteOffer.setType("HOTEL");
			favouriteOffer.setName(favouriteHotel.getHotelOffer().getHotel().getName());
			favouriteOffer.setDescription(favouriteHotel.getHotelOffer().getHotel().getDescription());
			favouriteOffer.setQuantityCapacity(favouriteHotel.getRoomQuantity());
			favouriteOffer.setOfferStart(favouriteHotel.getOfferStart());
			favouriteOffer.setOfferEnd(favouriteHotel.getOfferEnd());
			favouriteOffer.setPrice(favouriteHotel.getHotelOffer().getPrice());
			
			favouriteOffer.setServices(new ArrayList<SimpleNamedDTO>());
			for (ServiceHotel service : favouriteHotel.getHotelOffer().getHotel().getServices()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(service.getId());
				namedDTO.setName(service.getName());
				favouriteOffer.getServices().add(namedDTO);
			}

			favouriteOffer.setImages(new ArrayList<ImageDTO>());
			for (Image img : favouriteHotel.getHotelOffer().getHotel().getImages()) {
				imageDTO = new ImageDTO();
				imageDTO.setId(img.getId());
				favouriteOffer.getImages().add(imageDTO);
			}
			
			favouriteOffer.setPaymentMethods(new ArrayList<SimpleNamedDTO>());
			for (PaymentMethod pm : favouriteHotel.getHotelOffer().getHotel().getPaymentMethods()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(pm.getId());
				favouriteOffer.getPaymentMethods().add(namedDTO);
			}
			
			results.add(favouriteOffer);
		}
		
		for (FavouritePackageOffer favouritePackage: favouritesPackage) {
			favouriteOffer = new FavoriteOfferDTO();
			favouriteOffer.setId(favouritePackage.getPackageOffer().getId());
			favouriteOffer.setType("PACKAGE");
			favouriteOffer.setDescription(favouritePackage.getPackageOffer().getDescription());
			favouriteOffer.setQuantityCapacity(favouritePackage.getQuantityPeople());
			favouriteOffer.setPrice(favouritePackage.getPackageOffer().getPrice());
			
			favouriteOffer.setServices(new ArrayList<SimpleNamedDTO>());
			for (ServicePackage service : favouritePackage.getPackageOffer().getServices()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(service.getId());
				namedDTO.setName(service.getName());
				favouriteOffer.getServices().add(namedDTO);
			}

			favouriteOffer.setImages(new ArrayList<ImageDTO>());
			for (Image img : favouritePackage.getPackageOffer().getImages()) {
				imageDTO = new ImageDTO();
				imageDTO.setId(img.getId());
				favouriteOffer.getImages().add(imageDTO);
			}
			
			results.add(favouriteOffer);
		}
		
		return results;
	}
}
