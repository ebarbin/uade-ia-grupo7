
package ar.edu.uade.ia.integrations;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ar.edu.uade.ia.integrations package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AutorizeHotelOffer_QNAME = new QName("http://integrations.ia.uade.edu.ar/", "autorizeHotelOffer");
    private final static QName _AutorizeHotelOfferResponse_QNAME = new QName("http://integrations.ia.uade.edu.ar/", "autorizeHotelOfferResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ar.edu.uade.ia.integrations
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AutorizeHotelOffer }
     * 
     */
    public AutorizeHotelOffer createAutorizeHotelOffer() {
        return new AutorizeHotelOffer();
    }

    /**
     * Create an instance of {@link AutorizeHotelOfferResponse }
     * 
     */
    public AutorizeHotelOfferResponse createAutorizeHotelOfferResponse() {
        return new AutorizeHotelOfferResponse();
    }

    /**
     * Create an instance of {@link BackOfficeRequest }
     * 
     */
    public BackOfficeRequest createBackOfficeRequest() {
        return new BackOfficeRequest();
    }

    /**
     * Create an instance of {@link BackOfficeResponse }
     * 
     */
    public BackOfficeResponse createBackOfficeResponse() {
        return new BackOfficeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutorizeHotelOffer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integrations.ia.uade.edu.ar/", name = "autorizeHotelOffer")
    public JAXBElement<AutorizeHotelOffer> createAutorizeHotelOffer(AutorizeHotelOffer value) {
        return new JAXBElement<AutorizeHotelOffer>(_AutorizeHotelOffer_QNAME, AutorizeHotelOffer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutorizeHotelOfferResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://integrations.ia.uade.edu.ar/", name = "autorizeHotelOfferResponse")
    public JAXBElement<AutorizeHotelOfferResponse> createAutorizeHotelOfferResponse(AutorizeHotelOfferResponse value) {
        return new JAXBElement<AutorizeHotelOfferResponse>(_AutorizeHotelOfferResponse_QNAME, AutorizeHotelOfferResponse.class, null, value);
    }

}
