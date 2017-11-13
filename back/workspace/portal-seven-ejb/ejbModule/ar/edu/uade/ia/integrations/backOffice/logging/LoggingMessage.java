package ar.edu.uade.ia.integrations.backOffice.logging;

import java.io.Serializable;

public class LoggingMessage implements Serializable {

	private static final long serialVersionUID = -590807841527676286L;

	private String plataformaEnvia = "Portal Web 7";
	private String plataformaRecibe = "Back Office 1";
	private String observacion;
	private String servicio;

	public String getPlataformaEnvia() {
		return plataformaEnvia;
	}

	public void setPlataformaEnvia(String plataformaEnvia) {
		this.plataformaEnvia = plataformaEnvia;
	}

	public String getPlataformaRecibe() {
		return plataformaRecibe;
	}

	public void setPlataformaRecibe(String plataformaRecibe) {
		this.plataformaRecibe = plataformaRecibe;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
}
