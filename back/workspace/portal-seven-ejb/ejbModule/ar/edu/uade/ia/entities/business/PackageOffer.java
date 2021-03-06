package ar.edu.uade.ia.entities.business;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "package_offer")
public class PackageOffer extends PersistentObject {

	private static final long serialVersionUID = 6647993123948723384L;

	@Column(name="offer_start")
	private Date offerStart;
	
	@Column(name="offer_end")
	private Date offerEnd;
	
	private Float price;
	
	private String cancellationPolicy;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "package_offer_image", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private List<Image> images;

	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "package_offer_service_package", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "service_package_id", nullable = false, updatable = false) })
	private List<ServicePackage> services;

	@ManyToOne(cascade = CascadeType.ALL)
	private Destination destination;

	private Integer availableQuota;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "package_offer_payment_method", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "payment_method_id", nullable = false, updatable = false) })
	private List<PaymentMethod> paymentMethods;

	@ManyToOne(cascade = CascadeType.ALL)
	private Agency agency;
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ServicePackage> getServices() {
		return services;
	}

	public void setServices(List<ServicePackage> services) {
		this.services = services;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Integer getAvailableQuota() {
		return availableQuota;
	}

	public void setAvailableQuota(Integer availableQuota) {
		this.availableQuota = availableQuota;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
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
