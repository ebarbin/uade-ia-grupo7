package ar.edu.uade.ia.integrations.hotelOffer.message;

import java.util.List;

public class HotelOfferMessage {

	private String codigo_prestador;

	private String nombre;

	private String destino;

	private String fecha_desde;

	private String fecha_hasta;

	private int cantidad_personas;

	private String foto_hotel;

	private String descripcion_hotel;

	private List<String> lista_servicios;

	private float precio_habitacion;

	private String foto_habitacion;

	private String descripcion_habitacion;

	private List<String> lista_servicios_habitacion;

	private float latitud;

	private float longitud;

	private String politica_cancelacion;

	private List<Integer> medio_pago_hotel;

	private String email_hotel;

	private int cupo;

	public String getCodigo_prestador() {
		return codigo_prestador;
	}

	public void setCodigo_prestador(String codigo_prestador) {
		this.codigo_prestador = codigo_prestador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public int getCantidad_personas() {
		return cantidad_personas;
	}

	public void setCantidad_personas(int cantidad_personas) {
		this.cantidad_personas = cantidad_personas;
	}

	public String getFoto_hotel() {
		return foto_hotel;
	}

	public void setFoto_hotel(String foto_hotel) {
		this.foto_hotel = foto_hotel;
	}

	public String getDescripcion_hotel() {
		return descripcion_hotel;
	}

	public void setDescripcion_hotel(String descripcion_hotel) {
		this.descripcion_hotel = descripcion_hotel;
	}

	public List<String> getLista_servicios() {
		return lista_servicios;
	}

	public void setLista_servicios(List<String> lista_servicios) {
		this.lista_servicios = lista_servicios;
	}

	public float getPrecio_habitacion() {
		return precio_habitacion;
	}

	public void setPrecio_habitacion(float precio_habitacion) {
		this.precio_habitacion = precio_habitacion;
	}

	public String getFoto_habitacion() {
		return foto_habitacion;
	}

	public void setFoto_habitacion(String foto_habitacion) {
		this.foto_habitacion = foto_habitacion;
	}

	public String getDescripcion_habitacion() {
		return descripcion_habitacion;
	}

	public void setDescripcion_habitacion(String descripcion_habitacion) {
		this.descripcion_habitacion = descripcion_habitacion;
	}

	public List<String> getLista_servicios_habitacion() {
		return lista_servicios_habitacion;
	}

	public void setLista_servicios_habitacion(List<String> lista_servicios_habitacion) {
		this.lista_servicios_habitacion = lista_servicios_habitacion;
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

	public List<Integer> getMedio_pago_hotel() {
		return medio_pago_hotel;
	}

	public void setMedio_pago_hotel(List<Integer> medio_pago_hotel) {
		this.medio_pago_hotel = medio_pago_hotel;
	}

	public String getEmail_hotel() {
		return email_hotel;
	}

	public void setEmail_hotel(String email_hotel) {
		this.email_hotel = email_hotel;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
}
