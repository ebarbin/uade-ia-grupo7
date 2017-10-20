package ar.edu.uade.ia.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.entities.business.PersistentObject;

@Entity
@Table(name = "favorite_package_offer")
public class FavoritePackageOffer extends PersistentObject {

	private static final long serialVersionUID = 3778726195206481299L;

	@ManyToOne
	private PortalUser portalUser;
	
	@ManyToOne
	private PackageOffer packageOffer;
	
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
}
