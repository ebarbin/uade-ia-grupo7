/**
 * SolicitudSOAPResourceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ar.edu.uade.ia.integrations.backOffice.authorizer;

public interface SolicitudSOAPResourceService extends javax.xml.rpc.Service {
    public java.lang.String getSolicitudPortAddress();

    public ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado getSolicitudPort() throws javax.xml.rpc.ServiceException;

    public ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado getSolicitudPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
