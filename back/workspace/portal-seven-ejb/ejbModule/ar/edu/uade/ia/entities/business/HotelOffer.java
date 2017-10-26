package ar.edu.uade.ia.entities.business;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotel_offer")
public class HotelOffer extends PersistentObject {

	private static final long serialVersionUID = -5955498653697555495L;

	@Column(name = "offer_start")
	private Date offerStart;

	@Column(name = "offer_end")
	private Date offerEnd;

	private Float price;

	private String cancellationPolicy;

	@ManyToOne(cascade = CascadeType.ALL)
	private Hotel hotel;

	@ManyToOne(cascade = CascadeType.ALL)
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

	public Date getOfferStart() {
		return offerStart;
	}

	public void setOfferStart(Date offerStart) {
		this.offerStart = offerStart;
	}

	public Date getOfferEnd() {
		return offerEnd;
	}

	public void setOfferEnd(Date offerEnd) {
		this.offerEnd = offerEnd;
	}
}
