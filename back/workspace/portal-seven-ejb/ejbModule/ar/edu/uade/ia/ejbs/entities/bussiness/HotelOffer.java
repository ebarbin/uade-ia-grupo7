package ar.edu.uade.ia.ejbs.entities.bussiness;

import javax.persistence.Entity;

@Entity
public class HotelOffer extends Offer {

	private static final long serialVersionUID = -5955498653697555495L;

	private Hotel hotel;
		
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
}
