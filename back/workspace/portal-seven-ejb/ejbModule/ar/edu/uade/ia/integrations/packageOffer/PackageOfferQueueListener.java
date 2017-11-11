package ar.edu.uade.ia.integrations.packageOffer;

import java.util.ArrayList;
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
import ar.edu.uade.ia.ejbs.AgencyEJB;
import ar.edu.uade.ia.ejbs.DestinationEJB;
import ar.edu.uade.ia.ejbs.PackageOfferEJB;
import ar.edu.uade.ia.ejbs.PaymentMethodEJB;
import ar.edu.uade.ia.ejbs.ServicePackageEJB;
import ar.edu.uade.ia.ejbs.common.ImageEJB;
import ar.edu.uade.ia.entities.business.Address;
import ar.edu.uade.ia.entities.business.Agency;
import ar.edu.uade.ia.entities.business.Destination;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.entities.business.PaymentMethod;
import ar.edu.uade.ia.entities.business.ServicePackage;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;
import ar.edu.uade.ia.integrations.common.AbstractQueueListener;
import ar.edu.uade.ia.integrations.hotelOffer.HotelOfferQueueListener;
import ar.edu.uade.ia.integrations.packageOffer.message.PackageOfferMessage;

/**
 * Message-Driven Bean implementation class for: PackageOfferQueueListener
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/ofertapaquete"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:jboss/exported/jms/queue/ofertapaquete")
public class PackageOfferQueueListener extends AbstractQueueListener implements MessageListener {

	private static Logger LOGGER = Logger.getLogger(PackageOfferQueueListener.class);

	@EJB
	private LoggingJMS loggingService;

	@EJB
	private PackageOfferEJB packageOfferEJB;
	
	@EJB
	private AgencyEJB agencyEJB;
	
	@EJB
	private AddressEJB addressEJB;
	
	@EJB
	private PaymentMethodEJB paymentMethodEJB;
	
	@EJB
	private ServicePackageEJB servicePackageEJB;
	
	@EJB
	private DestinationEJB destinationEJB;
	
	@EJB
	private ImageEJB imageEJB;
	
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
			
			PackageOfferQueueListener.LOGGER.info(jsonString);
			
			PackageOfferMessage pom = (PackageOfferMessage) JsonConverter.convertToObject(jsonString,
					PackageOfferMessage.class);
			
			
			Agency agency = this.agencyEJB.getByProviderCode(pom.getCodigo_prestador());
			if (agency == null) {
				agency = new Agency();
				agency.setProviderCode(pom.getCodigo_prestador());
				agency.setName(null); //No Informado
				agency.setEmail(pom.getMail_agencia());
				agency.setVotes(Integer.valueOf(0));
				agency.setPoints(Integer.valueOf(0));
			}
			
			PackageOffer po = new PackageOffer();
			po.setAgency(agency);
			
			po.setAvailableQuota(pom.getCupo_paquete());
			po.setCancellationPolicy(pom.getPolitica_cancelacion());
			po.setDescription(pom.getDescripcion_paquete());
			
			po.setOfferStart(HotelOfferQueueListener.DateFormatter.parse(pom.getFecha_desde()));
			po.setOfferEnd(HotelOfferQueueListener.DateFormatter.parse(pom.getFecha_hasta()));
			
			this.addPackageImage(po, pom.getFoto_paquete());
			
			this.addPackagePaymentMethods(po, pom.getMedio_pago_paquete());
			this.addPackageServices(po, pom.getServicios_paquete());
			po.setPrice(pom.getPrecio());
			
			this.addPackageDestination(po, pom.getDestino(), pom.getLatitud(), pom.getLongitud());
			
			this.packageOfferEJB.add(po);
			
			this.loggingService.info(LoggingAction.PACKAGE_OFFER_REGISTRATION);
			
		} catch (Exception e) {
			this.loggingService.error("Error to recive package: " + e.getMessage());
			PackageOfferQueueListener.LOGGER.error(e.getMessage(), e);
		}
	}

	private void addPackageImage(PackageOffer po, String url) throws Exception {
		po.setImages(new ArrayList<Image>());
		Image image = this.imageEJB.getByUrl(url);
		if (image == null) {
			byte[] data = this.downloadImage(url);
			if (data != null) {
				image = new Image();
				image.setData(data);
				image.setUrl(url);
				image.setDescription("Hotel");
				po.getImages().add(image);
			}
		} else {
			po.getImages().add(image);
		}
	}
	
	private void addPackageDestination(PackageOffer po, String destino, float latitud, float longitud) throws Exception {
		Destination destination = this.destinationEJB.getByName(destino);
		if (destination == null) {
			Address address = this.addressEJB.getByLatAndLng(latitud, longitud);
			if (address == null) {
				address = new Address();
				address.setLat(latitud);
				address.setLng(longitud);
			}
			destination = new Destination();
			destination.setName(destino);
			destination.setAddress(address);
		}
		po.setDestination(destination);
	}

	private void addPackagePaymentMethods(PackageOffer packageOffer, List<Integer> paymentMethods) throws Exception {
		packageOffer.setPaymentMethods(new ArrayList<PaymentMethod>());
		PaymentMethod pm;
		for (Integer pmId : paymentMethods) {
			pm = this.paymentMethodEJB.getByCode(pmId);
			if (pm == null) {
				pm = new PaymentMethod();
				pm.setName(PaymentMethodEnum.getById(pmId).getDescription());
			}
			packageOffer.getPaymentMethods().add(pm);
		}
	}
	
	private void addPackageServices(PackageOffer packageOffer, List<String> services) throws Exception {
		packageOffer.setServices(new ArrayList<ServicePackage>());
		ServicePackage service;
		for (String name : services) {
			service = this.servicePackageEJB.getByName(name);
			if (service == null) {
				service = new ServicePackage();
				service.setName(name);
			}
			packageOffer.getServices().add(service);
		}
	}

}
