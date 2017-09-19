package ar.edu.uade.ia.ejbs.entities;

public class Agencia extends ObjetoPersistente {

	private static final long serialVersionUID = 954537632748254118L;

	private String nombre;
	
	private Direccion direccion;
	
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
