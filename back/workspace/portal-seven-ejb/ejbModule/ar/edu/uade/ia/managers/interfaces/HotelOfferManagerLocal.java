package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.dtos.HotelOfferDTO;
import ar.edu.uade.ia.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.dtos.HotelOfferOtherRoomsRequestDTO;
import ar.edu.uade.ia.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.dtos.RoomDTO;

@Local
public interface HotelOfferManagerLocal {

	List<HotelOfferHeaderDTO>search(HotelOfferRequestDTO hotelOfferRequest) throws Exception;
	
	HotelOfferDTO getDetail(Integer id) throws Exception;
	
	List<RoomDTO> searchOtherRooms(HotelOfferOtherRoomsRequestDTO request) throws Exception;
}
