package ar.edu.uade.ia.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.PersistentObject;

@Entity
@Table(name = "favourite_hotel_offer")
public class FavouriteHotelOffer extends PersistentObject {

	private static final long serialVersionUID = -34052552514719046L;

	@ManyToOne
	private PortalUser portalUser;
	
	@ManyToOne
	private HotelOffer hotelOffer;
	
	@Column(name = "offer_start")
	private Date offerStart;

	@Column(name = "offer_end")
	private Date offerEnd;
	
	@Column(name = "room_quantity")
	private Integer roomQuantity;

	public PortalUser getPortalUser() {
		return portalUser;
	}

	public void setPortalUser(PortalUser portalUser) {
		this.portalUser = portalUser;
	}

	public HotelOffer getHotelOffer() {
		return hotelOffer;
	}

	public void setHotelOffer(HotelOffer hotelOffer) {
		this.hotelOffer = hotelOffer;
	}

	public Date getOfferStart() {
		return offerStart;
	}

	public void setOfferStart(Date offerStart) {
		this.offerStart = offerStart;
	}

	public Date getOfferEnd() {
		return offerEnd;
	}

	public void setOfferEnd(Date offerEnd) {
		this.offerEnd = offerEnd;
	}

	public Integer getRoomQuantity() {
		return roomQuantity;
	}

	public void setRoomQuantity(Integer roomQuantity) {
		this.roomQuantity = roomQuantity;
	}
}
