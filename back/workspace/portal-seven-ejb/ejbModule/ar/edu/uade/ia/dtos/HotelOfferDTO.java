package ar.edu.uade.ia.dtos;

import java.io.Serializable;

public class HotelOfferDTO implements Serializable {

	private static final long serialVersionUID = -5056225701663864983L;

	private Integer id;

	private HotelDTO hotel;
	
	private String cancellationPolicy;
	
	private RoomDTO room;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}

	public RoomDTO getRoom() {
		return room;
	}

	public void setRoom(RoomDTO room) {
		this.room = room;
	}
}
