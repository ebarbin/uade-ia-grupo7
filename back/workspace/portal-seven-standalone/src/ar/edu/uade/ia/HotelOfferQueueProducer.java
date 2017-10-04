package ar.edu.uade.ia;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import ar.edu.uade.ia.integrations.hotelOffer.producer.interfaces.HotelOfferProducerRemote;

public class HotelOfferQueueProducer {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "portal-seven";
		final String ejbModuleName = "portal-seven-ejb";
		final String distinctName = "";
		final String ejbClassName = "HotelOfferProducer";
		final String fullInterfaceName = HotelOfferProducerRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		HotelOfferProducerRemote mbr = (HotelOfferProducerRemote)

		context.lookup(lookupName);
		mbr.sendTextMessage("Mensaje de prueba");
	}

}
