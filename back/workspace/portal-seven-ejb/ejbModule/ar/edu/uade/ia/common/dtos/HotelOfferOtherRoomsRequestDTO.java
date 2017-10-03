package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;

public class HotelOfferOtherRoomsRequestDTO implements Serializable {

	private static final long serialVersionUID = 9036322440093071484L;

	private HotelDTO hotel;
	private RoomDTO room;
	private Date toDate;
	private Date fromDate;

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public RoomDTO getRoom() {
		return room;
	}

	public void setRoom(RoomDTO room) {
		this.room = room;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
}
