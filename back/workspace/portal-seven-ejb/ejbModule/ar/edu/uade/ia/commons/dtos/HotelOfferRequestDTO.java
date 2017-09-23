package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;
import java.util.Date;

public class HotelOfferRequestDTO implements Serializable {

	private static final long serialVersionUID = 5783638598859106720L;
	
	private AutocompleteResourceDTO hotel;
	private Date toDate;
	private Date fromDate;
	private Integer roomQuantity;
	private Integer peoplePerRoom;

	public AutocompleteResourceDTO getHotel() {
		return hotel;
	}

	public void setHotel(AutocompleteResourceDTO hotel) {
		this.hotel = hotel;
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

	public Integer getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(Integer roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public Integer getPeoplePerRoom() {
		return peoplePerRoom;
	}

	public void setPeoplePerRoom(Integer peoplePerRoom) {
		this.peoplePerRoom = peoplePerRoom;
	}
}
