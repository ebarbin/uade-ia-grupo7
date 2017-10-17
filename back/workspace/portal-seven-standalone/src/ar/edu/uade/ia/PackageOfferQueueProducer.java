package ar.edu.uade.ia;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import ar.edu.uade.ia.integrations.packageOffer.producer.interfaces.PackageOfferProducerRemote;

public class PackageOfferQueueProducer {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "portal-seven";
		final String ejbModuleName = "portal-seven-ejb";
		final String distinctName = "";
		final String ejbClassName = "PackageOfferProducer";
		final String fullInterfaceName = PackageOfferProducerRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		PackageOfferProducerRemote remoteProducer = (PackageOfferProducerRemote)

		context.lookup(lookupName);
		
		String hotelOffers = new String(Files.readAllBytes(Paths.get("package-offer.json")));
        
		remoteProducer.sendTextMessage(hotelOffers);
	}

}
