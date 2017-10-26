package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class PackageAuthorizeRequestDTO implements Serializable {

	private static final long serialVersionUID = -1570433486683694114L;
	
	private Integer quantityPeople;

	public Integer getQuantityPeople() {
		return quantityPeople;
	}

	public void setQuantityPeople(Integer quantityPeople) {
		this.quantityPeople = quantityPeople;
	}
}
