package ar.edu.uade.ia.ejbs.entities;

import java.util.Date;
import java.util.List;

public class ReservaCupo extends ObjetoPersistente {

	private static final long serialVersionUID = 4555465608403169617L;

	private List<Cupo>cuposReservados;
	
	private User usuario;
	
	private Date fecha;
	
	private Date ocupaDesde;
	
	private Date ocupaHasta;
	
	private Float precioTotal;

	public List<Cupo> getCuposReservados() {
		return cuposReservados;
	}

	public void setCuposReservados(List<Cupo> cuposReservados) {
		this.cuposReservados = cuposReservados;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getOcupaDesde() {
		return ocupaDesde;
	}

	public void setOcupaDesde(Date ocupaDesde) {
		this.ocupaDesde = ocupaDesde;
	}

	public Date getOcupaHasta() {
		return ocupaHasta;
	}

	public void setOcupaHasta(Date ocupaHasta) {
		this.ocupaHasta = ocupaHasta;
	}

	public Float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(Float precioTotal) {
		this.precioTotal = precioTotal;
	}
}
