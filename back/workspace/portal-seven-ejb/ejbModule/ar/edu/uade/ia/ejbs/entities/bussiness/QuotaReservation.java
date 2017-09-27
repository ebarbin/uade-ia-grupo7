package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@JoinColumn(name="portal_user_id")
	private PortalUser portalUser;
	
	@Column(name="reservation_date")
	private Date reservationDate;
	
	private Float totalPrice;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "quota_reservation_quota", joinColumns = {
			@JoinColumn(name = "quota_reservation_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "quota_id", nullable = false, updatable = false) })
	private List<Quota>quotas;
	
	@Column(name="reservation_start")
	private Date reservationStart;
	
	@Column(name="reservation_end")
	private Date reservationEnd;
	
	public List<Quota> getQuotas() {
		return quotas;
	}

	public void setQuotas(List<Quota> quotas) {
		this.quotas = quotas;
	}

	public PortalUser getPortalUser() {
		return portalUser;
	}

	public void setPortalUser(PortalUser portalUser) {
		this.portalUser = portalUser;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getReservationStart() {
		return reservationStart;
	}

	public void setReservationStart(Date reservationStart) {
		this.reservationStart = reservationStart;
	}

	public Date getReservationEnd() {
		return reservationEnd;
	}

	public void setReservationEnd(Date reservationEnd) {
		this.reservationEnd = reservationEnd;
	}
}
