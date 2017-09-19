package ar.edu.uade.ia.ejbs.entities;

import java.util.List;

public class Habitacion extends ObjetoPersistente {

	private static final long serialVersionUID = 5243270004093839420L;

	private List<Foto>fotos;
	
	private String descripcion;
	
	private List<Servicio>servicios;
	
	private Integer capacidadPersonas;
	
	private String tipo;

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

	public Integer getCapacidadPersonas() {
		return capacidadPersonas;
	}

	public void setCapacidadPersonas(Integer capacidadPersonas) {
		this.capacidadPersonas = capacidadPersonas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
