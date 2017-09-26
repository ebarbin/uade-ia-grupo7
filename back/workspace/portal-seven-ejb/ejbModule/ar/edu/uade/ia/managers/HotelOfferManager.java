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
import ar.edu.uade.ia.commons.dtos.requests.HotelOfferRequestDTO;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.HotelOffer;
import ar.edu.uade.ia.managers.interfaces.HotelOfferManagerLocal;
import ar.edu.uade.ia.managers.interfaces.HotelOfferManagerRemote;

/**
 * Session Bean implementation class HotelOfferManager
 */
@Stateless
@LocalBean
public class HotelOfferManager implements HotelOfferManagerRemote, HotelOfferManagerLocal {

	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	//private ListMapperDecorator listMapper = new ListMapperDecorator(mapper);
	
	@EJB
	private HotelOfferEJB hotelOfferEJB;
	
    /**
     * Default constructor. 
     */
    public HotelOfferManager() {}

	@Override
	public List<HotelOfferHeaderDTO> search(HotelOfferRequestDTO hotelOfferRequest) throws Exception {
		List<HotelOffer> hotelOffers = this.hotelOfferEJB.search(hotelOfferRequest);
		List<HotelOfferHeaderDTO> results = new ArrayList<HotelOfferHeaderDTO>();
		for (HotelOffer hotelOffer : hotelOffers) {
			results.add(new HotelOfferHeaderDTO(hotelOffer));
		}
		return results;
	}

	@Override
	public HotelOfferDTO getDetail(Integer id) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(id);
		//TODO clear image data fields
		return this.mapper.map(ho, HotelOfferDTO.class);
	}

}
