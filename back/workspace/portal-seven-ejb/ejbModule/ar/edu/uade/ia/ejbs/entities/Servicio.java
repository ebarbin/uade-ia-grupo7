package ar.edu.uade.ia.ejbs.entities;

public class Servicio extends ObjetoPersistente {

	private static final long serialVersionUID = -1978696476715748487L;

	private String descripcion;
	
	private String tipo;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
