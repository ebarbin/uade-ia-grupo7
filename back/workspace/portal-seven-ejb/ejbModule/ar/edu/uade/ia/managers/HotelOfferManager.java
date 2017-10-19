package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.common.dtos.AuthorizeStatusDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.RoomDTO;
import ar.edu.uade.ia.common.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.Room;
import ar.edu.uade.ia.entities.business.ServiceHotel;
import ar.edu.uade.ia.entities.business.ServiceRoom;

/**
 * Session Bean implementation class HotelOfferManager
 */
@Stateless
@LocalBean
public class HotelOfferManager {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private HotelOfferEJB hotelOfferEJB;

	/**
	 * Default constructor.
	 */
	public HotelOfferManager() {
	}

	public AuthorizeStatusDTO autorize(Integer id, HotelOfferRequestDTO filter) throws Exception {

		AuthorizeStatusDTO dto = new AuthorizeStatusDTO();
		if (this.hotelOfferEJB.hasQuota(id, filter)) {
			// TODO MANDAR A AUTORIZAR AL WEBSERVICE SOAP
			dto.setStatus(Boolean.TRUE);
		} else {
			dto.setStatus(Boolean.FALSE);
			dto.setDescription("No hay hoteles disponibles.");
		}
		
		return dto;
	}

	public List<HotelOfferHeaderDTO> search(HotelOfferRequestDTO hotelOfferRequest) throws Exception {
		List<HotelOffer> hotelOffers = this.hotelOfferEJB.search(hotelOfferRequest);
		return this.convertToListOfHotelOfferHeaderDTO(hotelOffers);
	}

	public List<RoomDTO> searchOtherRooms(Integer roomId, HotelOfferRequestDTO request) {
		List<HotelOffer> hotelOffers = this.hotelOfferEJB.searchOtherRooms(roomId, request);
		return this.converToRoomDTOList(hotelOffers);
	}

	public HotelOfferDTO getDetail(Integer id) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(id);
		return HotelOfferManager.mapper.map(ho, HotelOfferDTO.class);
	}
    
	private List<RoomDTO> converToRoomDTOList(List<HotelOffer> hotelOffers) {

		List<RoomDTO> results = new ArrayList<RoomDTO>();
		RoomDTO roomDTO;
		SimpleNamedDTO namedDTO;
		ImageDTO imageDTO;

		Room room;
		for (HotelOffer hotelOffer : hotelOffers) {
			room = hotelOffer.getRoom();
			roomDTO = new RoomDTO();

			roomDTO.setId(room.getId());
			roomDTO.setDescription(room.getDescription());
			roomDTO.setCapacity(room.getCapacity());
			roomDTO.setType(room.getType());

			roomDTO.setServices(new ArrayList<SimpleNamedDTO>());
			for (ServiceRoom service : room.getServices()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(service.getId());
				namedDTO.setName(service.getName());
				roomDTO.getServices().add(namedDTO);
			}

			roomDTO.setImages(new ArrayList<ImageDTO>());
			for (Image img : room.getImages()) {
				imageDTO = new ImageDTO();
				imageDTO.setId(img.getId());
				roomDTO.getImages().add(imageDTO);
			}

			results.add(roomDTO);
		}

		return results;
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

			for (ServiceHotel service : hotelOffer.getHotel().getServices()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(service.getId());
				namedDTO.setName(service.getName());
				headerDTO.getServices().add(namedDTO);
			}
			headerDTO.setPrice(hotelOffer.getPrice());
			headerDTO.setRoomCapacity(hotelOffer.getRoom().getCapacity());
			headerDTO.setOfferStart(hotelOffer.getOfferStart());
			headerDTO.setOfferEnd(hotelOffer.getOfferEnd());

			headerDTO.setImages(new ArrayList<ImageDTO>());
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
