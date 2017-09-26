package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;

public class HotelOfferDTO implements Serializable {

	private static final long serialVersionUID = -5056225701663864983L;

	private Integer id;

	private HotelDTO hotel;
	
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
}
