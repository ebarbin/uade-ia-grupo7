package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.common.dtos.AuthorizeStatusDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferOtherRoomsRequestDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.RoomDTO;

@Local
public interface HotelOfferManagerLocal {

	List<HotelOfferHeaderDTO>search(HotelOfferRequestDTO hotelOfferRequest) throws Exception;
	
	HotelOfferDTO getDetail(Integer id) throws Exception;
	
	List<RoomDTO> searchOtherRooms(HotelOfferOtherRoomsRequestDTO request) throws Exception;
	
	AuthorizeStatusDTO autorize(Integer id) throws Exception;
}
