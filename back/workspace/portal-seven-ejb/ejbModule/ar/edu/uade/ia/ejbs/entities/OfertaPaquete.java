package ar.edu.uade.ia.ejbs.entities;

import java.util.Date;
import java.util.List;

public class OfertaPaquete {

	private List<Foto>fotos;
	
	private String descripcion;
	
	private List<Servicio>servicios;
	
	private Destino destino;
	
	private Date desde;
	
	private Date hasta;
	
	private Integer cupoDisponibles;
	
	private Float precioPersona;
	
	private String politicaCancelacion;
	
	private List<String>medioDePago;
	
	private Agencia agencia;

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

	public Integer getCupoDisponibles() {
		return cupoDisponibles;
	}

	public void setCupoDisponibles(Integer cupoDisponibles) {
		this.cupoDisponibles = cupoDisponibles;
	}

	public Float getPrecioPersona() {
		return precioPersona;
	}

	public void setPrecioPersona(Float precioPersona) {
		this.precioPersona = precioPersona;
	}

	public String getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(String politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
	}

	public List<String> getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(List<String> medioDePago) {
		this.medioDePago = medioDePago;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
}
