package ar.edu.uade.ia.integrations.backOffice.authorization;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.12
 * 2017-11-06T12:31:50.839-03:00
 * Generated source version: 3.1.12
 * 
 */
@WebService(targetNamespace = "http://soap.servicios/", name = "ServiciosBO/PrestadorAutorizado")
@XmlSeeAlso({ObjectFactory.class})
public interface ServiciosBO_002fPrestadorAutorizado {

    @WebMethod
    @RequestWrapper(localName = "getPrestadorAutorizado", targetNamespace = "http://soap.servicios/", className = "servicios.soap.GetPrestadorAutorizado")
    @ResponseWrapper(localName = "getPrestadorAutorizadoResponse", targetNamespace = "http://soap.servicios/", className = "servicios.soap.GetPrestadorAutorizadoResponse")
    @WebResult(name = "solicitud", targetNamespace = "")
    public SolicitudDTO getPrestadorAutorizado(
        @WebParam(name = "id", targetNamespace = "")
        int id
    );
}
