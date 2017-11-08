package ar.edu.uade.ia.managers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.common.dtos.AuthorizeStatusDTO;
import ar.edu.uade.ia.common.dtos.HotelAuthorizeRequestDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.HotelOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.RoomDTO;
import ar.edu.uade.ia.common.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.common.enums.ConfigurationType;
import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.ejbs.ConfigurationEJB;
import ar.edu.uade.ia.ejbs.FavouriteOfferEJB;
import ar.edu.uade.ia.ejbs.HotelOfferEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.Configuration;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.Room;
import ar.edu.uade.ia.entities.business.ServiceHotel;
import ar.edu.uade.ia.entities.business.ServiceRoom;
import ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizadoService;
import ar.edu.uade.ia.integrations.backOffice.authorizer.ProviderAuthorizationStatus;
import ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudDTO;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;

/**
 * Session Bean implementation class HotelOfferManager
 */
@Stateless
@LocalBean
public class HotelOfferManager {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private LoggingJMS loggingService;

	@EJB
	private HotelOfferEJB hotelOfferEJB;

	@EJB
	private FavouriteOfferEJB favouriteOfferEJB;

	@EJB
	private PortalUserEJB portalUserEJB;
	
	@EJB
	private ConfigurationEJB configurationEJB;

	/**
	 * Default constructor.
	 */
	public HotelOfferManager() {
	}

	public AuthorizeStatusDTO autorize(Integer offerId, HotelAuthorizeRequestDTO req)
			throws Exception {

		AuthorizeStatusDTO dto = new AuthorizeStatusDTO();
		if (this.hotelOfferEJB.hasQuota(offerId, req)) {

			ProviderAuthorizationStatus status = this.sendAuthorization(offerId); 
			
			if (ProviderAuthorizationStatus.APPROVED == status){
				PortalUser user = this.portalUserEJB.getById(req.getPortalUser().getId());
				this.hotelOfferEJB.reserve(offerId, req, user);	
				
				dto.setStatus(Boolean.TRUE);
				this.loggingService.info(LoggingAction.PACKAGE_RESERVATION);
				
			} else {
				dto.setStatus(Boolean.FALSE);
				
				if (ProviderAuthorizationStatus.PENDING == status) {
					dto.setDescription("El prestador esta pendiente de aprobaci�n. No es posible realizar la reserva.");
				} else {
					dto.setDescription("El prestador no est� habilitado para ofreser reservas. No es posible realidar la reserva.");
				}
			}

		} else {
			dto.setStatus(Boolean.FALSE);
			dto.setDescription("No hay hoteles disponibles.");
		}

		return dto;
	}

	private ProviderAuthorizationStatus sendAuthorization(Integer offerId) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(offerId);
		Configuration configuration = this.configurationEJB.getByKeyType(ConfigurationType.AUTHORIZE);
		
		URL wsdlUrl = new URL(configuration.getValue());
		PrestadorAutorizadoService backOfficeService = new PrestadorAutorizadoService(wsdlUrl);
		
		SolicitudDTO dto = backOfficeService.getServiciosBO_002fPrestadorAutorizadoPort().getPrestadorAutorizado(ho.getHotel().getProviderCode());
		if (ProviderAuthorizationStatus.APPROVED.getCode().equals(dto.getEstado())) {
			return ProviderAuthorizationStatus.APPROVED;
		} else if (ProviderAuthorizationStatus.PENDING.getCode().equals(dto.getEstado())) {
			return ProviderAuthorizationStatus.PENDING;
		} else {
			return ProviderAuthorizationStatus.REJECTED;
		}
	}
	
	public List<HotelOfferHeaderDTO> search(Integer portalUserId, HotelOfferRequestDTO request) throws Exception {
		List<HotelOffer> hotelOffers = this.hotelOfferEJB.search(request);
		return this.convertToListOfHotelOfferHeaderDTO(hotelOffers, portalUserId);
	}

	public List<RoomDTO> searchOtherRooms(Integer roomId, HotelOfferRequestDTO request) throws Exception {
		List<HotelOffer> hotelOffers = this.hotelOfferEJB.searchOtherRooms(roomId, request);
		return this.converToRoomDTOList(hotelOffers);
	}

	public HotelOfferDTO getDetail(Integer id) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(id);
		return HotelOfferManager.mapper.map(ho, HotelOfferDTO.class);
	}

	public Integer valoration(Integer id, Integer valoration) throws Exception {
		HotelOffer ho = this.hotelOfferEJB.getDetail(id);
		if (ho.getHotel().getVotes() == null)
			ho.getHotel().setVotes(0);
		ho.getHotel().setVotes(ho.getHotel().getVotes() + 1);
		if (ho.getHotel().getPoints() == null)
			ho.getHotel().setPoints(0);
		ho.getHotel().setPoints(ho.getHotel().getPoints() + valoration);
		this.hotelOfferEJB.update(ho);
		return ho.getHotel().getPoints() / ho.getHotel().getVotes();
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

	private List<HotelOfferHeaderDTO> convertToListOfHotelOfferHeaderDTO(List<HotelOffer> hotelOffers, Integer userId)
			throws Exception {
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
			headerDTO.setFavourite(this.favouriteOfferEJB.isFavouriteHotel(hotelOffer.getId(), userId));

			if (hotelOffer.getHotel().getPoints() != null && !hotelOffer.getHotel().getPoints().equals(0)) {
				headerDTO.setValoration(hotelOffer.getHotel().getPoints() / hotelOffer.getHotel().getVotes());
			} else {
				headerDTO.setValoration(0);
			}

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
