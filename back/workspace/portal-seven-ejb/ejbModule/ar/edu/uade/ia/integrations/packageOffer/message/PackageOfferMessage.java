package ar.edu.uade.ia.integrations.packageOffer.message;

import java.util.List;

public class PackageOfferMessage {

	private Integer codigo_prestador;
	private String destino;
	private String fecha_desde;
	private String fecha_hasta;
	private int cantidad_personas_paquete;
	private String foto_paquete;
	private String descripcion_paquete;
	private List<String> servicios_paquete;
	private float precio;
	private float latitud;
	private float longitud;
	private String politica_cancelacion;
	private List<Integer> medio_pago_paquete;
	private String mail_agencia;
	private int cupo_paquete;

	public Integer getCodigo_prestador() {
		return codigo_prestador;
	}

	public void setCodigo_prestador(Integer codigo_prestador) {
		this.codigo_prestador = codigo_prestador;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFecha_desde() {
		return fecha_desde;
	}

	public void setFecha_desde(String fecha_desde) {
		this.fecha_desde = fecha_desde;
	}

	public String getFecha_hasta() {
		return fecha_hasta;
	}

	public void setFecha_hasta(String fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}

	public int getCantidad_personas_paquete() {
		return cantidad_personas_paquete;
	}

	public void setCantidad_personas_paquete(int cantidad_personas_paquete) {
		this.cantidad_personas_paquete = cantidad_personas_paquete;
	}

	public String getFoto_paquete() {
		return foto_paquete;
	}

	public void setFoto_paquete(String foto_paquete) {
		this.foto_paquete = foto_paquete;
	}

	public String getDescripcion_paquete() {
		return descripcion_paquete;
	}

	public void setDescripcion_paquete(String descripcion_paquete) {
		this.descripcion_paquete = descripcion_paquete;
	}

	public List<String> getServicios_paquete() {
		return servicios_paquete;
	}

	public void setServicios_paquete(List<String> servicios_paquete) {
		this.servicios_paquete = servicios_paquete;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getPolitica_cancelacion() {
		return politica_cancelacion;
	}

	public void setPolitica_cancelacion(String politica_cancelacion) {
		this.politica_cancelacion = politica_cancelacion;
	}

	public List<Integer> getMedio_pago_paquete() {
		return medio_pago_paquete;
	}

	public void setMedio_pago_paquete(List<Integer> medio_pago_paquete) {
		this.medio_pago_paquete = medio_pago_paquete;
	}

	public String getMail_agencia() {
		return mail_agencia;
	}

	public void setMail_agencia(String mail_agencia) {
		this.mail_agencia = mail_agencia;
	}

	public int getCupo_paquete() {
		return cupo_paquete;
	}

	public void setCupo_paquete(int cupo_paquete) {
		this.cupo_paquete = cupo_paquete;
	}

}
