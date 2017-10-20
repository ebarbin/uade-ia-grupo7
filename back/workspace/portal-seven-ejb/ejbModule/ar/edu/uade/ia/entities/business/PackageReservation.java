package ar.edu.uade.ia.entities.business;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.uade.ia.entities.PortalUser;

@Entity
@Table(name = "package_reservation")
public class PackageReservation extends PersistentObject {

	private static final long serialVersionUID = 1322663302447235060L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="portal_user_id")
	private PortalUser portalUser;
	
	@Column(name="reservation_date")
	private Date reservationDate;
	
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
}
