package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.Date;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Offer extends PersistentObject {
	
	private static final long serialVersionUID = 5869158543020280203L;

	private Date from;
	
	private Date to;
	
	private Float price;
	
	private String cancellationPolicy;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
}
