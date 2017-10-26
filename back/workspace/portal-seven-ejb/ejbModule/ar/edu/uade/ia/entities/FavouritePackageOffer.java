package ar.edu.uade.ia.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.entities.business.PersistentObject;

@Entity
@Table(name = "favourite_package_offer")
public class FavouritePackageOffer extends PersistentObject {

	private static final long serialVersionUID = 3778726195206481299L;

	@ManyToOne
	private PortalUser portalUser;
	
	@ManyToOne
	private PackageOffer packageOffer;
	
	private Integer quantityPeople;
	
	public PortalUser getPortalUser() {
		return portalUser;
	}

	public void setPortalUser(PortalUser portalUser) {
		this.portalUser = portalUser;
	}

	public PackageOffer getPackageOffer() {
		return packageOffer;
	}

	public void setPackageOffer(PackageOffer packageOffer) {
		this.packageOffer = packageOffer;
	}

	public Integer getQuantityPeople() {
		return quantityPeople;
	}

	public void setQuantityPeople(Integer quantityPeople) {
		this.quantityPeople = quantityPeople;
	}
}
