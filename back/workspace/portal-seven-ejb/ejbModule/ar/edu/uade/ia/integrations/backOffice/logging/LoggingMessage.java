package ar.edu.uade.ia.integrations.backOffice.logging;

import java.io.Serializable;
import java.util.Date;

public class LoggingMessage implements Serializable {

	private static final long serialVersionUID = -590807841527676286L;

	private String modulo = "PW_7";
	private Integer accion;
	private Date fecha = new Date();
	private String descripcion;

	public String getModulo() {
		return modulo;
	}

	public Integer getAccion() {
		return accion;
	}

	public void setAccion(Integer accion) {
		this.accion = accion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
