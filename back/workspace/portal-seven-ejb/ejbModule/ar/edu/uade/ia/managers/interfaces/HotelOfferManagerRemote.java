package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import ar.edu.uade.ia.dtos.HotelOfferDTO;
import ar.edu.uade.ia.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.dtos.HotelOfferRequestDTO;

@Remote
public interface HotelOfferManagerRemote {

	List<HotelOfferHeaderDTO>search(HotelOfferRequestDTO hotelOfferRequest) throws Exception;

	HotelOfferDTO getDetail(Integer id) throws Exception;
}
