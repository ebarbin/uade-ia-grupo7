package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class QuotaReservation extends PersistentObject {

	private static final long serialVersionUID = 4555465608403169617L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="uuser")
	private User user;
	
	private Timestamp reservationDate;
	
	private Float totalPrice;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Quota_Reservation_Quota", joinColumns = {
			@JoinColumn(name = "quota_reservation_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "quota_id", nullable = false, updatable = false) })
	private List<Quota>quotas;
	
	private Timestamp reservationStart;
	
	private Timestamp reservationEnd;
	
	public List<Quota> getQuotas() {
		return quotas;
	}

	public void setQuotas(List<Quota> quotas) {
		this.quotas = quotas;
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

	public Timestamp getReservationStart() {
		return reservationStart;
	}

	public void setReservationStart(Timestamp reservationStart) {
		this.reservationStart = reservationStart;
	}

	public Timestamp getReservationEnd() {
		return reservationEnd;
	}

	public void setReservationEnd(Timestamp reservationEnd) {
		this.reservationEnd = reservationEnd;
	}
}
