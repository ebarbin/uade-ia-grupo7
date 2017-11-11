/**
 * SolicitudSOAPResourceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ar.edu.uade.ia.integrations.backOffice.authorizer;

public class SolicitudSOAPResourceServiceLocator extends org.apache.axis.client.Service implements ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudSOAPResourceService {

    public SolicitudSOAPResourceServiceLocator() {
    }


    public SolicitudSOAPResourceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SolicitudSOAPResourceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SolicitudPort
    private java.lang.String SolicitudPort_address = "http://Equipo006500:8080/TPO_BO_EJB/ServiciosBO/PrestadorAutorizado";

    public java.lang.String getSolicitudPortAddress() {
        return SolicitudPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SolicitudPortWSDDServiceName = "SolicitudPort";

    public java.lang.String getSolicitudPortWSDDServiceName() {
        return SolicitudPortWSDDServiceName;
    }

    public void setSolicitudPortWSDDServiceName(java.lang.String name) {
        SolicitudPortWSDDServiceName = name;
    }

    public ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado getSolicitudPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SolicitudPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSolicitudPort(endpoint);
    }

    public ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado getSolicitudPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudSOAPResourceServiceSoapBindingStub _stub = new ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudSOAPResourceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSolicitudPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSolicitudPortEndpointAddress(java.lang.String address) {
        SolicitudPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado.class.isAssignableFrom(serviceEndpointInterface)) {
                ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudSOAPResourceServiceSoapBindingStub _stub = new ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudSOAPResourceServiceSoapBindingStub(new java.net.URL(SolicitudPort_address), this);
                _stub.setPortName(getSolicitudPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SolicitudPort".equals(inputPortName)) {
            return getSolicitudPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://soap.webservice.integracion.uade.edu/", "SolicitudSOAPResourceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://soap.webservice.integracion.uade.edu/", "SolicitudPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SolicitudPort".equals(portName)) {
            setSolicitudPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
