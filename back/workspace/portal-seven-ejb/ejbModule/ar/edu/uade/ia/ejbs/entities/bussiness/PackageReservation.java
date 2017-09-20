package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PackageReservation extends PersistentObject {

	private static final long serialVersionUID = 1322663302447235060L;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	private Timestamp reservationDate;
	
	private Float totalPrice;
	
	@ManyToOne
	private PackageOffer pckage;
	
	private Integer quotaQuantity;

	public PackageOffer getPckage() {
		return pckage;
	}

	public void setPckage(PackageOffer pckage) {
		this.pckage = pckage;
	}

	public Integer getQuotaQuantity() {
		return quotaQuantity;
	}

	public void setQuotaQuantity(Integer quotaQuantity) {
		this.quotaQuantity = quotaQuantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Timestamp reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
