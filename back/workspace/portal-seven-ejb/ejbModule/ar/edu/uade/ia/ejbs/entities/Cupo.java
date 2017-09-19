package ar.edu.uade.ia.ejbs.entities;

import java.util.Date;

public class Cupo extends ObjetoPersistente {

	private static final long serialVersionUID = 7791774625364655871L;

	private Date fecha;
	
	private OfertaHotel oferta;
	
	private Integer cupoDisponibles;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public OfertaHotel getOferta() {
		return oferta;
	}

	public void setOferta(OfertaHotel oferta) {
		this.oferta = oferta;
	}

	public Integer getCupoDisponibles() {
		return cupoDisponibles;
	}

	public void setCupoDisponibles(Integer cupoDisponibles) {
		this.cupoDisponibles = cupoDisponibles;
	}
}
