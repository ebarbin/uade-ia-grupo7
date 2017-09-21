package ar.edu.uade.ia.ejbs.entities.bussiness;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends PersistentObject {
	
	private static final long serialVersionUID = -8188943815874050762L;

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
}
