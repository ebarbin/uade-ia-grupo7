package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Quota extends PersistentObject {

	private static final long serialVersionUID = 7791774625364655871L;

	private Date quotaDate;
	
	@ManyToOne
	private HotelOffer offer;
	
	private Integer  availableQuota;

	public Date getQuotaDate() {
		return quotaDate;
	}

	public void setQuotaDate(Date quotaDate) {
		this.quotaDate = quotaDate;
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
