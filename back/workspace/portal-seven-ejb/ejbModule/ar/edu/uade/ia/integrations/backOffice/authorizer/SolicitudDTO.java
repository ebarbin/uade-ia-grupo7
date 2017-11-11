/**
 * SolicitudDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ar.edu.uade.ia.integrations.backOffice.authorizer;

public class SolicitudDTO  extends ar.edu.uade.ia.integrations.backOffice.authorizer.BaseDTO  implements java.io.Serializable {
    private java.lang.String detalle;

    private ar.edu.uade.ia.integrations.backOffice.authorizer.EstadoSolicitudEnum estado;

    private ar.edu.uade.ia.integrations.backOffice.authorizer.TipoSolicitudEnum tipo;

    public SolicitudDTO() {
    }

    public SolicitudDTO(
           java.lang.Long id,
           java.lang.String detalle,
           ar.edu.uade.ia.integrations.backOffice.authorizer.EstadoSolicitudEnum estado,
           ar.edu.uade.ia.integrations.backOffice.authorizer.TipoSolicitudEnum tipo) {
        super(
            id);
        this.detalle = detalle;
        this.estado = estado;
        this.tipo = tipo;
    }


    /**
     * Gets the detalle value for this SolicitudDTO.
     * 
     * @return detalle
     */
    public java.lang.String getDetalle() {
        return detalle;
    }


    /**
     * Sets the detalle value for this SolicitudDTO.
     * 
     * @param detalle
     */
    public void setDetalle(java.lang.String detalle) {
        this.detalle = detalle;
    }


    /**
     * Gets the estado value for this SolicitudDTO.
     * 
     * @return estado
     */
    public ar.edu.uade.ia.integrations.backOffice.authorizer.EstadoSolicitudEnum getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this SolicitudDTO.
     * 
     * @param estado
     */
    public void setEstado(ar.edu.uade.ia.integrations.backOffice.authorizer.EstadoSolicitudEnum estado) {
        this.estado = estado;
    }


    /**
     * Gets the tipo value for this SolicitudDTO.
     * 
     * @return tipo
     */
    public ar.edu.uade.ia.integrations.backOffice.authorizer.TipoSolicitudEnum getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this SolicitudDTO.
     * 
     * @param tipo
     */
    public void setTipo(ar.edu.uade.ia.integrations.backOffice.authorizer.TipoSolicitudEnum tipo) {
        this.tipo = tipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudDTO)) return false;
        SolicitudDTO other = (SolicitudDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.detalle==null && other.getDetalle()==null) || 
             (this.detalle!=null &&
              this.detalle.equals(other.getDetalle()))) &&
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDetalle() != null) {
            _hashCode += getDetalle().hashCode();
        }
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.webservice.integracion.uade.edu/", "solicitudDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("detalle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "detalle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.webservice.integracion.uade.edu/", "estadoSolicitudEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://soap.webservice.integracion.uade.edu/", "tipoSolicitudEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
