package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class HotelOffer extends PersistentObject {

	private static final long serialVersionUID = -5955498653697555495L;

	private Timestamp from;
	
	private Timestamp to;
	
	private Float price;
	
	private String cancellationPolicy;
	
	@ManyToOne
	private Hotel hotel;
		
	@ManyToOne
	private Room room;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Timestamp getFrom() {
		return from;
	}

	public void setFrom(Timestamp from) {
		this.from = from;
	}

	public Timestamp getTo() {
		return to;
	}

	public void setTo(Timestamp to) {
		this.to = to;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
}
