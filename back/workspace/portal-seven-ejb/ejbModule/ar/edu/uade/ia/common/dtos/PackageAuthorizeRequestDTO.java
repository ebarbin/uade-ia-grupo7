package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class PackageAuthorizeRequestDTO implements Serializable {

	private static final long serialVersionUID = -1570433486683694114L;
	
	private Integer quantityPeople;
	private Float totalPrice;
	private PortalUserDTO portalUser;

	public Integer getQuantityPeople() {
		return quantityPeople;
	}

	public void setQuantityPeople(Integer quantityPeople) {
		this.quantityPeople = quantityPeople;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PortalUserDTO getPortalUser() {
		return portalUser;
	}

	public void setPortalUser(PortalUserDTO portalUser) {
		this.portalUser = portalUser;
	}
}
