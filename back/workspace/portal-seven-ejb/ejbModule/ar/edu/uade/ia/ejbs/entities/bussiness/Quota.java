package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Quota extends PersistentObject {

	private static final long serialVersionUID = 7791774625364655871L;

	@Column(name="tdate")
	private Timestamp date;
	
	@ManyToOne
	private HotelOffer offer;
	
	private Integer  availableQuota;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public HotelOffer getOffer() {
		return offer;
	}

	public void setOffer(HotelOffer offer) {
		this.offer = offer;
	}

	public Integer getAvailableQuota() {
		return availableQuota;
	}

	public void setAvailableQuota(Integer availableQuota) {
		this.availableQuota = availableQuota;
	}
}
