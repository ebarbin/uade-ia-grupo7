package ar.edu.uade.ia.ejbs.entities;

public class Destino extends ObjetoPersistente {

	private static final long serialVersionUID = 7201684177830379591L;

	private String nombre;
	
	private Direccion direccion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
