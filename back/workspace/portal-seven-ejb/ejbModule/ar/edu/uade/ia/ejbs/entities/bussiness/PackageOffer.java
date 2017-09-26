package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class PackageOffer extends PersistentObject {

	private static final long serialVersionUID = 6647993123948723384L;

	@Column(name="tfrom")
	private Timestamp from;
	
	@Column(name="tto")
	private Timestamp to;
	
	private Float price;
	
	private String cancellationPolicy;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Package_Offer_Image", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private List<Image> images;

	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Package_Offer_Service", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "service_id", nullable = false, updatable = false) })
	private List<Service> services;

	@ManyToOne(cascade = CascadeType.ALL)
	private Destination destination;

	private Integer availableQuota;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Package_Offer_Payment_Method", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "payment_method_id", nullable = false, updatable = false) })
	private List<PaymentMethod> paymentMethods;

	@ManyToOne
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

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
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

	public Timestamp getFrom() {
		return from;
	}

	public void setFrom(Timestamp from) {
		this.from = from;
	}

	public Timestamp getTo() {
		return to;
	}

	public void setTo(Timestamp to) {
		this.to = to;
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
