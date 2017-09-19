package ar.edu.uade.ia.ejbs.entities;

import java.util.List;

public class Hotel extends ObjetoPersistente {

	private static final long serialVersionUID = 564408788585026377L;

	private String nombre;
	
	private Destino destino;
	
	private String descripcion;
	
	private List<Foto> fotos;
	
	private List<Servicio>servicios;
	
	private List<Habitacion>habitaciones;
	
	private Direccion direccion;
	
	private List<String>mediosDepago;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<String> getMediosDepago() {
		return mediosDepago;
	}

	public void setMediosDepago(List<String> mediosDepago) {
		this.mediosDepago = mediosDepago;
	}
}
