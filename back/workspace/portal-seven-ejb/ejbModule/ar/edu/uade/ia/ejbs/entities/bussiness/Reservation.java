package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Reservation extends PersistentObject {

	private static final long serialVersionUID = 1256668889446290247L;

	private User user;
	
	private Date reservationDate;
	
	private Float totalPrice;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
