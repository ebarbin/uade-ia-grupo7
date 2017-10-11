package ar.edu.uade.ia.integrations.hotelOffer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import ar.edu.uade.ia.ejbs.DestinationEJB;
import ar.edu.uade.ia.ejbs.HotelEJB;
import ar.edu.uade.ia.ejbs.PaymentMethodEJB;
import ar.edu.uade.ia.ejbs.QuotaEJB;
import ar.edu.uade.ia.ejbs.ServiceEJB;
import ar.edu.uade.ia.ejbs.common.ImageEJB;
import ar.edu.uade.ia.entities.business.Address;
import ar.edu.uade.ia.entities.business.Hotel;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PaymentMethod;
import ar.edu.uade.ia.entities.business.Quota;
import ar.edu.uade.ia.entities.business.Room;
import ar.edu.uade.ia.entities.business.Service;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.integrations.hotelOffer.message.HotelOfferMessage;

/**
 * Message-Driven Bean implementation class for: HotelOfferQueueListener
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/HotelOfferQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/queue/HotelOfferQueue")
public class HotelOfferQueueListener implements MessageListener {

	private static Logger LOGGER = Logger.getLogger(HotelOfferQueueListener.class);

	private static SimpleDateFormat DateFormatter = new SimpleDateFormat("yyyyMMdd");

	@EJB
	private LoggingJMS logging;

	@EJB
	private HotelEJB hotelEJB;

	@EJB
	private ImageEJB imageEJB;

	@EJB
	private DestinationEJB destinationEJB;

	@EJB
	private AddressEJB addressEJB;

	@EJB
	private ServiceEJB serviceEJB;

	@EJB
	private PaymentMethodEJB paymentMethodEJB;

	@EJB
	private QuotaEJB quotaEJB;

	/**
	 * Default constructor.
	 */
	public HotelOfferQueueListener() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			String jsonString = ((TextMessage) message).getText();
			HotelOfferMessage hom = (HotelOfferMessage) JsonConverter.convertToObject(jsonString,
					HotelOfferMessage.class);

			// TODO VALIDAR EL MENSAJE COMPLETO!!
			String code = hom.getCodigo_prestador().split("_")[1] + hom.getCodigo_prestador().split("_")[2];

			Hotel hotel = this.hotelEJB.getHotelByCode(code);
			if (hotel == null) {

				hotel = new Hotel();
				hotel.setCode(code);
				hotel.setName(hom.getNombre());
				hotel.setDescription(hom.getDescripcion_hotel());

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
			hotelOffer.setPrice(hom.getPrecio_habitacion()); // Es Correcto?
			hotelOffer.setOfferStart(startDate);
			hotelOffer.setOfferEnd(endDate);

			// TODO Vuelve a crear una habitacion por cada oferta???
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

			// TODO Persiste todo en cascada?

			this.logging.info(LoggingAction.HOTEL_OFFER_REGISTRATION);
		} catch (Exception e) {
			this.logging.error(e.getMessage());
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
		room.setServices(new ArrayList<Service>());
		Service service;
		for (String name : services) {
			service = this.serviceEJB.getByName(name);
			if (service == null) {
				service = new Service();
				service.setName(name);
			}
			room.getServices().add(service);
		}
	}

	private void addHotelServices(Hotel hotel, List<String> services) throws Exception {
		hotel.setServices(new ArrayList<Service>());
		Service service;
		for (String name : services) {
			service = this.serviceEJB.getByName(name);
			if (service == null) {
				service = new Service();
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

	private byte[] downloadImage(String urlStr) throws Exception {
		try {
			URL url = new URL(urlStr);
			InputStream in = new BufferedInputStream(url.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf)))
				out.write(buf, 0, n);
			out.close();
			in.close();

			return out.toByteArray();
		} catch (ConnectException | FileNotFoundException e) {
			HotelOfferQueueListener.LOGGER.error("Error al descargar la imagen: " + e.getMessage(), e);
			return null;
		}
	}

}
