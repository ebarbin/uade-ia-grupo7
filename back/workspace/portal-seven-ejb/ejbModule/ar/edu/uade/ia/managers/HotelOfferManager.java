package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.dtos.HotelOfferDTO;
import ar.edu.uade.ia.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.dtos.ImageDTO;
import ar.edu.uade.ia.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.HotelOffer;
import ar.edu.uade.ia.ejbs.entities.bussiness.Image;
import ar.edu.uade.ia.ejbs.entities.bussiness.Service;
import ar.edu.uade.ia.managers.interfaces.HotelOfferManagerLocal;
import ar.edu.uade.ia.managers.interfaces.HotelOfferManagerRemote;

/**
 * Session Bean implementation class HotelOfferManager
 */
@Stateless
@LocalBean
public class HotelOfferManager implements HotelOfferManagerRemote, HotelOfferManagerLocal {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private HotelOfferEJB hotelOfferEJB;

	/**
	 * Default constructor.
	 */
	public HotelOfferManager() {}

	@Override
	public List<HotelOfferHeaderDTO> search(HotelOfferRequestDTO hotelOfferRequest) throws Exception {
		List<HotelOffer> hotelOffers = this.hotelOfferEJB.search(hotelOfferRequest);
		return this.convertToListOfHotelOfferHeaderDTO(hotelOffers);
	}

	@Override
	public HotelOfferDTO getDetail(Integer id) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(id);
		return HotelOfferManager.mapper.map(ho, HotelOfferDTO.class);
	}
	
	private List<HotelOfferHeaderDTO> convertToListOfHotelOfferHeaderDTO(List<HotelOffer> hotelOffers) {
		List<HotelOfferHeaderDTO> results = new ArrayList<HotelOfferHeaderDTO>();

		HotelOfferHeaderDTO headerDTO;
		SimpleNamedDTO namedDTO;
		ImageDTO imageDTO;

		for (HotelOffer hotelOffer : hotelOffers) {
			headerDTO = new HotelOfferHeaderDTO();

			headerDTO.setId(hotelOffer.getId());
			headerDTO.setName(hotelOffer.getHotel().getName());
			headerDTO.setDescription(hotelOffer.getHotel().getDescription());
			headerDTO.setServices(new ArrayList<SimpleNamedDTO>());

			for (Service service : hotelOffer.getHotel().getServices()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(service.getId());
				namedDTO.setName(service.getName());
				headerDTO.getServices().add(namedDTO);
			}
			headerDTO.setPrice(hotelOffer.getPrice());
			headerDTO.setRoomCapacity(hotelOffer.getRoom().getCapacity());
			headerDTO.setImages(new ArrayList<ImageDTO>());
			headerDTO.setOfferStart(hotelOffer.getOfferStart());
			headerDTO.setOfferEnd(hotelOffer.getOfferEnd());
			
			for (Image img : hotelOffer.getHotel().getImages()) {
				imageDTO = new ImageDTO();
				imageDTO.setId(img.getId());
				headerDTO.getImages().add(imageDTO);
			}
			results.add(headerDTO);
		}
		return results;
	}
}
