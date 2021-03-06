package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;

public class HotelOfferRequestDTO implements Serializable {

	private static final long serialVersionUID = 5783638598859106720L;
	
	private SimpleNamedDTO hotel;
	private Date toDate;
	private Date fromDate;
	private Integer roomQuantity;
	private Integer peoplePerRoom;
	private Float minPrice;
	private Float maxPrice;
	
	public SimpleNamedDTO getHotel() {
		return hotel;
	}

	public void setHotel(SimpleNamedDTO hotel) {
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

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
}
