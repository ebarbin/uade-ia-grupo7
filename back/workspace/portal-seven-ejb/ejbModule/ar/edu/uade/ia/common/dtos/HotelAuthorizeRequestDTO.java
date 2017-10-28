package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;

public class HotelAuthorizeRequestDTO implements Serializable {

	private static final long serialVersionUID = 1102274007792859439L;
	
	private Integer roomQuantity;
	private Date fromDate;
	private Date toDate;
	private Float totalPrice;
	private PortalUserDTO portalUser;
	
	public Integer getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(Integer roomQuantity) {
		this.roomQuantity = roomQuantity;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public PortalUserDTO getPortalUser() {
		return portalUser;
	}

	public void setPortalUser(PortalUserDTO portalUser) {
		this.portalUser = portalUser;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
}
