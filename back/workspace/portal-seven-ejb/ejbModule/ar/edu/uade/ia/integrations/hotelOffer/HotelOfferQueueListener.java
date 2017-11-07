package ar.edu.uade.ia.integrations.hotelOffer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.common.enums.PaymentMethodEnum;
import ar.edu.uade.ia.common.jackson.JsonConverter;
import ar.edu.uade.ia.ejbs.AddressEJB;
import ar.edu.uade.ia.ejbs.HotelEJB;
import ar.edu.uade.ia.ejbs.PaymentMethodEJB;
import ar.edu.uade.ia.ejbs.QuotaEJB;
import ar.edu.uade.ia.ejbs.ServiceHotelEJB;
import ar.edu.uade.ia.ejbs.ServiceRoomEJB;
import ar.edu.uade.ia.ejbs.common.ImageEJB;
import ar.edu.uade.ia.entities.business.Address;
import ar.edu.uade.ia.entities.business.Hotel;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PaymentMethod;
import ar.edu.uade.ia.entities.business.Quota;
import ar.edu.uade.ia.entities.business.Room;
import ar.edu.uade.ia.entities.business.ServiceHotel;
import ar.edu.uade.ia.entities.business.ServiceRoom;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.integrations.common.AbstractQueueListener;
import ar.edu.uade.ia.integrations.hotelOffer.message.HotelOfferMessage;

/**
 * Message-Driven Bean implementation class for: HotelOfferQueueListener
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/ofertahotelera"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:jboss/exported/jms/queue/ofertahotelera")
public class HotelOfferQueueListener extends AbstractQueueListener implements MessageListener  {

	private static Logger LOGGER = Logger.getLogger(HotelOfferQueueListener.class);

	@EJB
	private LoggingJMS loggingService;

	@EJB
	private HotelEJB hotelEJB;

	@EJB
	private ImageEJB imageEJB;


	@EJB
	private AddressEJB addressEJB;

	@EJB
	private ServiceHotelEJB serviceHotelEJB;
	
	@EJB
	private ServiceRoomEJB serviceRoomEJB;

	@EJB
	private PaymentMethodEJB paymentMethodEJB;

	@EJB
	private QuotaEJB quotaEJB;

	/**
	 * Default constructor.
	 */
	public HotelOfferQueueListener() {}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			String jsonString = ((TextMessage) message).getText();
			HotelOfferMessage hom = (HotelOfferMessage) JsonConverter.convertToObject(jsonString,
					HotelOfferMessage.class);

			// TODO VALIDAR EL MENSAJE COMPLETO!!
			String code = this.getProviderCode(hom.getCodigo_prestador());

			Hotel hotel = this.hotelEJB.getByCode(code);
			if (hotel == null) {

				hotel = new Hotel();
				hotel.setCode(code);
				hotel.setName(hom.getNombre());
				hotel.setDescription(hom.getDescripcion_hotel());
				hotel.setPoints(0);
				hotel.setVotes(0);
				
				this.addHotelImage(hotel, hom.getFoto_hotel());

				this.addHotelAddress(hotel, hom.getLatitud(), hom.getLongitud());

				this.addHotelPaymentMethods(hotel, hom.getMedio_pago_hotel());

				this.addHotelServices(hotel, hom.getLista_servicios());
			}

			Date startDate = HotelOfferQueueListener.DateFormatter.parse(hom.getFecha_desde());
			Date endDate = HotelOfferQueueListener.DateFormatter.parse(hom.getFecha_hasta());

			HotelOffer hotelOffer = new HotelOffer();
			hotelOffer.setCancellationPolicy(hom.getPolitica_cancelacion());
			hotelOffer.setHotel(hotel);
			hotelOffer.setPrice(hom.getPrecio_habitacion());
			hotelOffer.setOfferStart(startDate);
			hotelOffer.setOfferEnd(endDate);

			Room room = new Room();
			room.setCapacity(hom.getCantidad_personas());
			room.setDescription(hom.getDescripcion_habitacion());
			room.setType(hom.getCantidad_personas());

			this.addRoomImage(room, hom.getFoto_habitacion());

			this.addRoomServices(room, hom.getLista_servicios_habitacion());

			hotelOffer.setRoom(room);

			Calendar date = Calendar.getInstance();
			date.setTime(startDate);

			Quota quota;
			while (date.getTime().before(endDate) || date.getTime().equals(endDate)) {
				quota = new Quota();
				quota.setOffer(hotelOffer);
				quota.setAvailableQuota(hom.getCupo());
				quota.setQuotaDate(date.getTime());
				this.quotaEJB.add(quota);

				date.add(Calendar.DATE, 1);
			}

			this.loggingService.info(LoggingAction.HOTEL_OFFER_REGISTRATION);
		} catch (Exception e) {
			this.loggingService.error(e.getMessage());
			HotelOfferQueueListener.LOGGER.error(e.getMessage(), e);
		}
	}

	private void addHotelPaymentMethods(Hotel hotel, List<Integer> paymentMethods) throws Exception {
		hotel.setPaymentMethods(new ArrayList<PaymentMethod>());
		PaymentMethod pm;
		for (Integer pmId : paymentMethods) {
			pm = this.paymentMethodEJB.getByCode(pmId);
			if (pm == null) {
				pm = new PaymentMethod();
				pm.setName(PaymentMethodEnum.getById(pmId).getDescription());
			}
			hotel.getPaymentMethods().add(pm);
		}
	}

	private void addRoomServices(Room room, List<String> services) throws Exception {
		room.setServices(new ArrayList<ServiceRoom>());
		ServiceRoom service;
		for (String name : services) {
			service = this.serviceRoomEJB.getByName(name);
			if (service == null) {
				service = new ServiceRoom();
				service.setName(name);
			}
			room.getServices().add(service);
		}
	}

	private void addHotelServices(Hotel hotel, List<String> services) throws Exception {
		hotel.setServices(new ArrayList<ServiceHotel>());
		ServiceHotel service;
		for (String name : services) {
			service = this.serviceHotelEJB.getByName(name);
			if (service == null) {
				service = new ServiceHotel();
				service.setName(name);
			}
			hotel.getServices().add(service);
		}
	}

	private void addHotelAddress(Hotel hotel, float latitud, float longitud) throws Exception {
		Address address = this.addressEJB.getByLatAndLng(latitud, longitud);
		if (address == null) {
			address = new Address();
			address.setLat(latitud);
			address.setLng(longitud);
		}
		hotel.setAddress(address);
	}

	private void addRoomImage(Room room, String url) throws Exception {
		room.setImages(new ArrayList<Image>());
		Image image = this.imageEJB.getByUrl(url);
		if (image == null) {
			byte[] data = this.downloadImage(url);
			if (data != null) {
				image = new Image();
				image.setData(data);
				image.setUrl(url);
				image.setDescription("Room");
				room.getImages().add(image);
			}
		} else {
			room.getImages().add(image);
		}
	}

	private void addHotelImage(Hotel hotel, String url) throws Exception {
		hotel.setImages(new ArrayList<Image>());
		Image image = this.imageEJB.getByUrl(url);
		if (image == null) {
			byte[] data = this.downloadImage(url);
			if (data != null) {
				image = new Image();
				image.setData(data);
				image.setUrl(url);
				image.setDescription("Hotel");
				hotel.getImages().add(image);
			}
		} else {
			hotel.getImages().add(image);
		}
	}
}
