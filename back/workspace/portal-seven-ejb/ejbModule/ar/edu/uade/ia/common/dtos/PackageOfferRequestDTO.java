package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;

public class PackageOfferRequestDTO implements Serializable {

	private static final long serialVersionUID = -1856197548881118586L;

	private SimpleNamedDTO destination;
	private Date toDate;
	private Date fromDate;
	private Integer quantityPeople;

	public SimpleNamedDTO getDestination() {
		return destination;
	}

	public void setDestination(SimpleNamedDTO destination) {
		this.destination = destination;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Integer getQuantityPeople() {
		return quantityPeople;
	}

	public void setQuantityPeople(Integer quantityPeople) {
		this.quantityPeople = quantityPeople;
	}
}
