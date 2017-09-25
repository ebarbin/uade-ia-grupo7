package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.commons.dtos.HotelOfferDTO;
import ar.edu.uade.ia.commons.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.commons.dtos.requests.HotelOfferRequestDTO;

@Local
public interface HotelOfferManagerLocal {

	List<HotelOfferHeaderDTO>search(HotelOfferRequestDTO hotelOfferRequest) throws Exception;
	
	HotelOfferDTO getDetail(Integer id) throws Exception;
}
