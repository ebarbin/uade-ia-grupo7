package ar.edu.uade.ia.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.uade.ia.entities.business.HotelOffer;
import ar.edu.uade.ia.entities.business.PersistentObject;

@Entity
@Table(name = "favorite_hotel_offer")
public class FavoriteHotelOffer extends PersistentObject {

	private static final long serialVersionUID = -34052552514719046L;

	@ManyToOne
	private PortalUser portalUser;
	
	@ManyToOne
	private HotelOffer hotelOffer;

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
}
