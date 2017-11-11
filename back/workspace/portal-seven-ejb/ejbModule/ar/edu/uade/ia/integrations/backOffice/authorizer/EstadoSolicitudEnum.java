/**
 * EstadoSolicitudEnum.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ar.edu.uade.ia.integrations.backOffice.authorizer;

public class EstadoSolicitudEnum implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected EstadoSolicitudEnum(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _APROBADO = "APROBADO";
    public static final java.lang.String _RECHAZADO = "RECHAZADO";
    public static final java.lang.String _PENDIENTE = "PENDIENTE";
    public static final EstadoSolicitudEnum APROBADO = new EstadoSolicitudEnum(_APROBADO);
    public static final EstadoSolicitudEnum RECHAZADO = new EstadoSolicitudEnum(_RECHAZADO);
    public static final EstadoSolicitudEnum PENDIENTE = new EstadoSolicitudEnum(_PENDIENTE);
    public java.lang.String getValue() { return _value_;}
    public static EstadoSolicitudEnum fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        EstadoSolicitudEnum enumeration = (EstadoSolicitudEnum)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static EstadoSolicitudEnum fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstadoSolicitudEnum.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://soap.webservice.integracion.uade.edu/", "estadoSolicitudEnum"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
