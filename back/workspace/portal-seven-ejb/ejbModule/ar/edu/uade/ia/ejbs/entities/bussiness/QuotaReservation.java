package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.Date;
import java.util.List;

public class QuotaReservation extends PersistentObject {

	private static final long serialVersionUID = 4555465608403169617L;

	private List<Quota>quotas;
	
	private Date reservationStart;
	
	private Date reservationEnd;
	
	public List<Quota> getQuotas() {
		return quotas;
	}

	public void setQuotas(List<Quota> quotas) {
		this.quotas = quotas;
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
