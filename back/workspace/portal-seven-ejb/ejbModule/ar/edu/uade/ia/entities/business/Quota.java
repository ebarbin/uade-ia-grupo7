package ar.edu.uade.ia.entities.business;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quota")
public class Quota extends PersistentObject {

	private static final long serialVersionUID = 7791774625364655871L;

	private Date quotaDate;

	@ManyToOne(cascade = CascadeType.ALL)
	private HotelOffer offer;

	private Integer availableQuota;

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
