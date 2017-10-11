package ar.edu.uade.ia.entities.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends PersistentObject {
	
	private static final long serialVersionUID = -8188943815874050762L;

	@Column(columnDefinition="Decimal(10,6)")
	private Float lat;
	
	@Column(columnDefinition="Decimal(10,6)")
	private Float lng;

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
