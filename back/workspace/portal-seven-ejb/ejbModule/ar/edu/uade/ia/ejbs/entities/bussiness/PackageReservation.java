package ar.edu.uade.ia.ejbs.entities.bussiness;

public class PackageReservation extends PersistentObject {

	private static final long serialVersionUID = 1322663302447235060L;

	private PackageOffer pckage;
	
	private Integer quotaQuantity;

	public PackageOffer getPckage() {
		return pckage;
	}

	public void setPckage(PackageOffer pckage) {
		this.pckage = pckage;
	}

	public Integer getQuotaQuantity() {
		return quotaQuantity;
	}

	public void setQuotaQuantity(Integer quotaQuantity) {
		this.quotaQuantity = quotaQuantity;
	}
}
