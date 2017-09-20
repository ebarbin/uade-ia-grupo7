package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quota_reservation")
public class QuotaReservation extends PersistentObject {

	private static final long serialVersionUID = 4555465608403169617L;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	private Timestamp reservationDate;
	
	private Float totalPrice;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "quota_reservation_quota", joinColumns = {
			@JoinColumn(name = "quota_reservation_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "quota_id", nullable = false, updatable = false) })
	private List<Quota>quotas;
	
	private Date start;
	
	private Date end;
	
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
