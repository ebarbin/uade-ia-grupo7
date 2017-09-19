package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Quota extends PersistentObject {

	private static final long serialVersionUID = 7791774625364655871L;

	private Date date;
	
	private HotelOffer offer;
	
	private Integer  availableQuota;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
