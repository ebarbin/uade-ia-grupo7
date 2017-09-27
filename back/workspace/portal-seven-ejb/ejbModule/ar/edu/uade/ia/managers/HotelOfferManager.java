package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.commons.dtos.HotelOfferDTO;
import ar.edu.uade.ia.commons.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.commons.dtos.ImageDTO;
import ar.edu.uade.ia.commons.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.commons.dtos.requests.HotelOfferRequestDTO;
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

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	// private ListMapperDecorator listMapper = new ListMapperDecorator(mapper);

	@EJB
	private HotelOfferEJB hotelOfferEJB;

	/**
	 * Default constructor.
	 */
	public HotelOfferManager() {
	}

	@Override
	public List<HotelOfferHeaderDTO> search(HotelOfferRequestDTO hotelOfferRequest) throws Exception {
		List<HotelOffer> hotelOffers = this.hotelOfferEJB.search(hotelOfferRequest);

		List<HotelOfferHeaderDTO> results = new ArrayList<HotelOfferHeaderDTO>();

		HotelOfferHeaderDTO hotelOfferHeaderDTO;
		SimpleNamedDTO simpleNamedDTO;
		ImageDTO imageDTO;

		for (HotelOffer hotelOffer : hotelOffers) {
			hotelOfferHeaderDTO = new HotelOfferHeaderDTO();

			hotelOfferHeaderDTO.setId(hotelOffer.getId());
			hotelOfferHeaderDTO.setName(hotelOffer.getHotel().getName());
			hotelOfferHeaderDTO.setDescription(hotelOffer.getHotel().getDescription());
			hotelOfferHeaderDTO.setServices(new ArrayList<SimpleNamedDTO>());

			for (Service service : hotelOffer.getHotel().getServices()) {
				simpleNamedDTO = new SimpleNamedDTO();
				simpleNamedDTO.setId(service.getId());
				simpleNamedDTO.setName(service.getName());
				hotelOfferHeaderDTO.getServices().add(simpleNamedDTO);
			}
			hotelOfferHeaderDTO.setPrice(hotelOffer.getPrice());

			hotelOfferHeaderDTO.setImages(new ArrayList<ImageDTO>());
			for (Image img : hotelOffer.getHotel().getImages()) {
				imageDTO = new ImageDTO();
				imageDTO.setId(img.getId());
				hotelOfferHeaderDTO.getImages().add(imageDTO);
			}

			results.add(hotelOfferHeaderDTO);
		}
		return results;
	}

	@Override
	public HotelOfferDTO getDetail(Integer id) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(id);
		// TODO clear image data fields
		return this.mapper.map(ho, HotelOfferDTO.class);
	}

}
