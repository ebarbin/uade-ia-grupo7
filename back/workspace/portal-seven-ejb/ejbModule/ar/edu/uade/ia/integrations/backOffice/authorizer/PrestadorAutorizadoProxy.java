package ar.edu.uade.ia.integrations.backOffice.authorizer;

public class PrestadorAutorizadoProxy implements ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado {
  private String _endpoint = null;
  private ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado prestadorAutorizado = null;
  
  public PrestadorAutorizadoProxy() {
    _initPrestadorAutorizadoProxy();
  }
  
  public PrestadorAutorizadoProxy(String endpoint) {
    _endpoint = endpoint;
    _initPrestadorAutorizadoProxy();
  }
  
  private void _initPrestadorAutorizadoProxy() {
    try {
      prestadorAutorizado = (new ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudSOAPResourceServiceLocator()).getSolicitudPort();
      if (prestadorAutorizado != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)prestadorAutorizado)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)prestadorAutorizado)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (prestadorAutorizado != null)
      ((javax.xml.rpc.Stub)prestadorAutorizado)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ar.edu.uade.ia.integrations.backOffice.authorizer.PrestadorAutorizado getPrestadorAutorizado() {
    if (prestadorAutorizado == null)
      _initPrestadorAutorizadoProxy();
    return prestadorAutorizado;
  }
  
  public ar.edu.uade.ia.integrations.backOffice.authorizer.SolicitudDTO getPrestadorAutorizado(java.lang.Long id) throws java.rmi.RemoteException{
    if (prestadorAutorizado == null)
      _initPrestadorAutorizadoProxy();
    return prestadorAutorizado.getPrestadorAutorizado(id);
  }
  
  
}