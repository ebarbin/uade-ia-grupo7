package ar.edu.uade.ia.ejbs.entities;

import java.util.Date;

public class OfertaHotel {

	private Integer id;

	private Hotel hotel;
		
	private Date disponibleDesde;
	
	private Date disponibleHasta;
	
	private Float precio;
	
	private Habitacion habitacion;
	
	private String politicaCancelacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDisponibleDesde() {
		return disponibleDesde;
	}

	public void setDisponibleDesde(Date disponibleDesde) {
		this.disponibleDesde = disponibleDesde;
	}

	public Date getDisponibleHasta() {
		return disponibleHasta;
	}

	public void setDisponibleHasta(Date disponibleHasta) {
		this.disponibleHasta = disponibleHasta;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public String getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(String politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
