package ar.edu.uade.ia.dtos;

import java.io.Serializable;

public class AddressDTO implements Serializable {

	private static final long serialVersionUID = -5705300442073688958L;

	private Integer id;
	
	private String street;
	
	private Float lat;
	
	private Float lng;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
