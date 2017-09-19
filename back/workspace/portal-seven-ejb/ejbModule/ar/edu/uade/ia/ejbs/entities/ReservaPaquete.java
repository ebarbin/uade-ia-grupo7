package ar.edu.uade.ia.ejbs.entities;

public class ReservaPaquete {

	private OfertaPaquete paqueteReservado;
	
	private User usuario;
	
	private Integer cantidadCupos;
	
	private Float precioTotal;

	public OfertaPaquete getPaqueteReservado() {
		return paqueteReservado;
	}

	public void setPaqueteReservado(OfertaPaquete paqueteReservado) {
		this.paqueteReservado = paqueteReservado;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Integer getCantidadCupos() {
		return cantidadCupos;
	}

	public void setCantidadCupos(Integer cantidadCupos) {
		this.cantidadCupos = cantidadCupos;
	}

	public Float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Float precioTotal) {
		this.precioTotal = precioTotal;
	}
}
