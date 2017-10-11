package ar.edu.uade.ia.integrations.hotelOffer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.common.jackson.JsonConverter;
import ar.edu.uade.ia.entities.business.Address;
import ar.edu.uade.ia.entities.business.Destination;
import ar.edu.uade.ia.entities.business.Hotel;
import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PaymentMethod;
import ar.edu.uade.ia.entities.business.Room;
import ar.edu.uade.ia.entities.business.Service;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.integrations.hotelOffer.message.HotelOfferMessage;

/**
 * Message-Driven Bean implementation class for: PackageOfferQueueListener
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/PackageOfferQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/queue/PackageOfferQueue")
public class PackageOfferQueueListener implements MessageListener {

	private static Logger LOGGER = Logger.getLogger(PackageOfferQueueListener.class);

	private static SimpleDateFormat DateFormatter = new SimpleDateFormat("YYYYMMDD");

	@EJB
	private LoggingJMS logging;

	/**
	 * Default constructor.
	 */
	public PackageOfferQueueListener() {}

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
			Hotel hotel = new Hotel();
			hotel.setCode(code);
			hotel.setName(hom.getNombre());
			hotel.setDescription(hom.getDescripcion_hotel());
			
			hotel.setImages(new ArrayList<Image>());
			Image image = new Image();
			byte[] data = this.downloadImage(hom.getFoto_hotel());
			image.setData(data);
			image.setUrl(hom.getFoto_hotel());
			image.setDescription("Hotel");
			hotel.getImages().add(image);

			Destination destination = new Destination();
			destination.setName(hom.getDestino());
			Address address = new Address();
			address.setLat(hom.getLatitud());
			address.setLng(hom.getLongitud());
			address.setStreet("No informado"); //TODO no viene, lo quitamos?
			destination.setAddress(address); //TODO quitamos el address en el destination??
			hotel.setDestination(destination);
			hotel.setAddress(address);
			
			hotel.setPaymentMethods(new ArrayList<PaymentMethod>());
			PaymentMethod pm;
			for(Integer pmId : hom.getMedio_pago_hotel()) {
				ar.edu.uade.ia.common.enums.PaymentMethod pmEnum = ar.edu.uade.ia.common.enums.PaymentMethod.getById(pmId);
				pm = new PaymentMethod();
				pm.setName(pmEnum.getDescription());
				hotel.getPaymentMethods().add(pm);
			}
			
			hotel.setServices(new ArrayList<Service>());
			Service service;
			for(String srv : hom.getLista_servicios()) {
				service = new Service();
				service.setName(srv);
				hotel.getServices().add(service);
			}
			
			HotelOffer hotelOffer = new HotelOffer();
			hotelOffer.setCancellationPolicy(hom.getPolitica_cancelacion());
			hotelOffer.setHotel(hotel);
			hotelOffer.setPrice(hom.getPrecio_habitacion()); //Es Correcto?
			hotelOffer.setOfferEnd(DateFormatter.parse(hom.getFecha_hasta()));
			hotelOffer.setOfferStart(DateFormatter.parse(hom.getFecha_desde()));
			
			Room room = new Room();
			room.setCapacity(hom.getCantidad_personas());
			room.setDescription(hom.getDescripcion_habitacion());
			room.setType(String.valueOf(hom.getCantidad_personas()));
			room.setImages(new ArrayList<Image>());
			image = new Image();
			data = this.downloadImage(hom.getFoto_habitacion());
			image.setData(data);
			image.setUrl(hom.getFoto_hotel());
			image.setDescription("Room");
			room.getImages().add(image);
			
			room.setServices(new ArrayList<Service>());
			for(String srv : hom.getLista_servicios_habitacion()) {
				service = new Service();
				service.setName(srv);
				room.getServices().add(service);
			}
			hotelOffer.setRoom(room);
			
			// TODO hotel.setRooms(rooms); lo usamos?
			// TODO hotel.setState(state); lo usamos?

			// TODO Implementar:
			// 1- Validar la informacion (caso erroneo, enviar log)
			// 2- Persistir en nuestras tablas

			System.out.println(hom);

			this.logging.info(LoggingAction.HOTEL_OFFER_REGISTRATION);
		} catch (Exception e) {
			this.logging.error(e.getMessage());
			PackageOfferQueueListener.LOGGER.error(e.getMessage(), e);
		}
	}

	private byte[] downloadImage(String urlStr) throws Exception {
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
	}

}
